package jw.srb.core.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2614:11
 */

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jw.srb.common.exception.Assert;
import jw.srb.common.result.ResponseEnum;
import jw.srb.core.enums.LendStatusEnum;
import jw.srb.core.enums.TransTypeEnum;
import jw.srb.core.hfb.FormHelper;
import jw.srb.core.hfb.HfbConst;
import jw.srb.core.hfb.RequestHelper;
import jw.srb.core.mapper.*;
import jw.srb.core.pojo.bo.TransFlowBO;
import jw.srb.core.pojo.entity.Lend;
import jw.srb.core.pojo.entity.LendItem;
import jw.srb.core.pojo.entity.LendItemReturn;
import jw.srb.core.pojo.entity.LendReturn;
import jw.srb.core.service.*;
import jw.srb.core.util.LendNoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/26 14:11
 * @updateDate 2021/9/26 14:11
 **/
@Slf4j
@Service
public class LendReturnServiceImpl extends ServiceImpl<LendReturnMapper, LendReturn> implements LendReturnService {
    @Resource
    private LendMapper lendMapper;

    @Resource
    private UserBindService userBindService;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private LendItemReturnService lendItemReturnService;

    @Resource
    private TransFlowService transFlowService;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private LendItemReturnMapper lendItemReturnMapper;

    @Resource
    private LendItemMapper lendItemMapper;

    @Override
    public List<LendReturn> selectByLendId(Long lendId) {
        QueryWrapper<LendReturn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lend_id", lendId);
        List<LendReturn> lendReturns = baseMapper.selectList(queryWrapper);
        return lendReturns;
    }

    @Override
    public String commitReturn(Long lendReturnId, Long userId) {
        //????????????
        LendReturn lendReturn = baseMapper.selectById(lendReturnId);

        //??????????????????
        BigDecimal amount = userAccountService.getAccount(userId);
        Assert.isTrue(amount.doubleValue() >= lendReturn.getTotal().doubleValue(),
                ResponseEnum.NOT_SUFFICIENT_FUNDS_ERROR);
        //????????????
        Lend lend = lendMapper.selectById(lendReturn.getLendId());
        //?????????????????????????????????
        String bindCode = userBindService.getBindCodeByUserId(userId);

        //????????????
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("agentId", HfbConst.AGENT_ID);
        //??????????????????
        paramMap.put("agentGoodsName", lend.getTitle());
        //?????????
        paramMap.put("agentBatchNo", lendReturn.getReturnNo());
        //????????????????????????
        paramMap.put("fromBindCode", bindCode);
        //????????????
        paramMap.put("totalAmt", lendReturn.getTotal());
        paramMap.put("note", "");
        //????????????
        List<Map<String, Object>> lendItemReturnDetailList = lendItemReturnService.addReturnDetail(lendReturnId);
        paramMap.put("data", JSONObject.toJSONString(lendItemReturnDetailList));
        paramMap.put("voteFeeAmt", new BigDecimal(0));
        paramMap.put("notifyUrl", HfbConst.BORROW_RETURN_NOTIFY_URL);
        paramMap.put("returnUrl", HfbConst.BORROW_RETURN_RETURN_URL);
        paramMap.put("timestamp", RequestHelper.getTimestamp());
        String sign = RequestHelper.getSign(paramMap);
        paramMap.put("sign", sign);

        //????????????????????????
        String formStr = FormHelper.buildForm(HfbConst.BORROW_RETURN_URL, paramMap);
        return formStr;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void notify(Map<String, Object> paramMap) {
        log.info("????????????");

        //????????????
        String agentBatchNo = (String) paramMap.get("agentBatchNo");

        boolean result = transFlowService.isSaveTransFlow(agentBatchNo);

        if (result) {
            log.warn("???????????????");
            return;
        }

        //??????????????????
        QueryWrapper<LendReturn> lendReturnQueryWrapper = new QueryWrapper<>();
        lendReturnQueryWrapper.eq("return_no", agentBatchNo);
        LendReturn lendReturn = baseMapper.selectOne(lendReturnQueryWrapper);


        //??????????????????
        String voteFeeAmt = (String) paramMap.get("voteFeeAmt");
        lendReturn.setStatus(1);
        lendReturn.setFee(new BigDecimal(voteFeeAmt));
        lendReturn.setRealReturnTime(LocalDateTime.now());
        baseMapper.updateById(lendReturn);


        //??????????????????
        Lend lend = lendMapper.selectById(lendReturn.getLendId());
        //?????????????????????????????????????????????????????????
        if (lendReturn.getLast()) {
            lend.setStatus(LendStatusEnum.PAY_OK.getStatus());
            lendMapper.updateById(lend);
        }

        //????????????????????????
        BigDecimal totalAmt = new BigDecimal((String) paramMap.get("totalAmt"));
        String bindCode = userBindService.getBindCodeByUserId(lendReturn.getUserId());
        userAccountMapper.updateAccount(bindCode, totalAmt.negate(), new BigDecimal(0));

        //????????????
        TransFlowBO transFlowBO = new TransFlowBO(agentBatchNo,
                bindCode,
                totalAmt,
                TransTypeEnum.RETURN_DOWN,
                "???????????????????????????????????????" + lend.getLendNo() + "??????????????????" + lend.getTitle());
        transFlowService.saveTransFlow(transFlowBO);

        //?????????????????????
        List<LendItemReturn> lendItemReturnList = lendItemReturnService.selectLendItemReturnList(lendReturn.getId());
        lendItemReturnList.forEach(item -> {
            //??????????????????
            item.setStatus(1);
            item.setRealReturnTime(LocalDateTime.now());
            lendItemReturnMapper.updateById(item);

            //??????????????????
            LendItem lendItem = lendItemMapper.selectById(item.getLendItemId());
            lendItem.setRealAmount(lendItem.getRealAmount().add(item.getInterest()));//?????????????????????
            lendItemMapper.updateById(lendItem);

            //????????????????????????
            String investBindCode = userBindService.getBindCodeByUserId(item.getInvestUserId());
            userAccountMapper.updateAccount(investBindCode, item.getTotal(), new BigDecimal(0));


            //????????????
            TransFlowBO investTransFlowBO = new TransFlowBO(
                    LendNoUtils.getReturnItemNo(),
                    investBindCode,
                    item.getTotal(),
                    TransTypeEnum.INVEST_BACK,
                    "??????????????????????????????" + lend.getLendNo() + "??????????????????" + lend.getTitle()
            );
            transFlowService.saveTransFlow(investTransFlowBO);
        });
    }
}
