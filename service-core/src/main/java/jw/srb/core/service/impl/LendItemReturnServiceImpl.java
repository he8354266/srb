package jw.srb.core.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/239:59
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jw.srb.core.mapper.LendItemMapper;
import jw.srb.core.mapper.LendItemReturnMapper;
import jw.srb.core.mapper.LendMapper;
import jw.srb.core.mapper.LendReturnMapper;
import jw.srb.core.pojo.entity.Lend;
import jw.srb.core.pojo.entity.LendItem;
import jw.srb.core.pojo.entity.LendItemReturn;
import jw.srb.core.pojo.entity.LendReturn;
import jw.srb.core.service.LendItemReturnService;
import jw.srb.core.service.UserBindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/23 9:59
 * @updateDate 2021/9/23 9:59
 **/
@Service
public class LendItemReturnServiceImpl extends ServiceImpl<LendItemReturnMapper, LendItemReturn> implements LendItemReturnService {
    @Resource
    private LendMapper lendMapper = null;

    @Resource
    private LendReturnMapper lendReturnMapper = null;

    @Resource
    private LendItemMapper lendItemMapper = null;

    @Resource
    private UserBindService userBindService = null;

    @Override
    public List<LendItemReturn> selectByLendId(Long lendId, Long userId) {
        QueryWrapper<LendItemReturn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lend_id", lendId)
                .eq("invest_user_id", userId)
                .orderByAsc("current_period");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 通过还款计划的id，找到对应的回款计划数据，组装data参数中需要的List<Map>
     *
     * @param lendReturnId
     * @return
     */
    @Override
    public List<Map<String, Object>> addReturnDetail(Long lendReturnId) {
        //还款记录
        LendReturn lendReturn = lendReturnMapper.selectById(lendReturnId);

        //获取标的
        Lend lend = lendMapper.selectById(lendReturn.getLendId());
        List<LendItemReturn> lendItemReturnList = this.selectLendItemReturnList(lendReturnId);
        List<Map<String, Object>> lendItemReturnDetailList = new ArrayList<>();
        for (LendItemReturn lendItemReturn : lendItemReturnList) {
            //获取投资记录
            Long lendItemId = lendItemReturn.getLendItemId();
            LendItem lendItem = lendItemMapper.selectById(lendItemId);

            //获取投资人id
            Long investUserId = lendItem.getInvestUserId();
            String bindCode = userBindService.getBindCodeByUserId(investUserId);

            Map<String, Object> map = new HashMap<>();
            map.put("agentProjectCode", lend.getLendNo());//项目编号
            map.put("voteBillNo", lendItem.getLendItemNo());//投资编号
            map.put("toBindCode", bindCode); //投资人bindCode
            map.put("transitAmt", lendItemReturn.getTotal());//还款总额
            map.put("baseAmt", lendItemReturn.getPrincipal());//本金
            map.put("benifitAmt", lendItemReturn.getInterest());//利息
            map.put("feeAmt", new BigDecimal(0));
            lendItemReturnDetailList.add(map);
        }
        return lendItemReturnDetailList;
    }

    @Override
    public List<LendItemReturn> selectLendItemReturnList(Long id) {
        QueryWrapper<LendItemReturn> lendItemReturnQueryWrapper = new QueryWrapper<>();
        lendItemReturnQueryWrapper.eq("lendItemReturnQueryWrapper", id);
        return baseMapper.selectList(lendItemReturnQueryWrapper);
    }
}