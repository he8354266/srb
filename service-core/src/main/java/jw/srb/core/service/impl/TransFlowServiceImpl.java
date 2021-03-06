package jw.srb.core.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2311:24
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jw.srb.core.mapper.TransFlowMapper;
import jw.srb.core.mapper.UserInfoMapper;
import jw.srb.core.pojo.bo.TransFlowBO;
import jw.srb.core.pojo.entity.TransFlow;
import jw.srb.core.pojo.entity.UserInfo;
import jw.srb.core.service.TransFlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/23 11:24
 * @updateDate 2021/9/23 11:24
 **/
@Service
public class TransFlowServiceImpl extends ServiceImpl<TransFlowMapper, TransFlow> implements TransFlowService {
    @Resource
    private UserInfoMapper userInfoMapper = null;

    @Override
    public void saveTransFlow(TransFlowBO transFlowBO) {
        String bindCode = transFlowBO.getBindCode();
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("bind_code", bindCode);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoQueryWrapper);

        TransFlow transFlow = new TransFlow();
        transFlow.setTransAmount(transFlowBO.getAmount());
        transFlow.setMemo(transFlowBO.getMemo());
        transFlow.setTransTypeName(transFlowBO.getTransTypeEnum().getTransTypeName());
        transFlow.setTransType(transFlowBO.getTransTypeEnum().getTransType());
        transFlow.setTransNo(transFlowBO.getAgentBillNo());//流水号
        transFlow.setUserId(userInfo.getId());
        transFlow.setUserName(userInfo.getName());
        baseMapper.insert(transFlow);
    }

    @Override
    public boolean isSaveTransFlow(String agentBillNo) {
        QueryWrapper<TransFlow> transFlowQueryWrapper = new QueryWrapper<>();
        transFlowQueryWrapper.eq("trans_no", agentBillNo);
        Long count = baseMapper.selectCount(transFlowQueryWrapper);
        return count > 0;
    }

    @Override
    public List<TransFlow> selectByUserId(Long userId) {
        QueryWrapper<TransFlow> transFlowQueryWrapper = new QueryWrapper<>();
        transFlowQueryWrapper.eq("user_id", userId).orderByDesc("id");
        return baseMapper.selectList(transFlowQueryWrapper);
    }
}
