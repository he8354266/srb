package jw.srb.core.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2311:12
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jw.srb.core.mapper.LendItemMapper;
import jw.srb.core.mapper.LendMapper;
import jw.srb.core.mapper.UserAccountMapper;
import jw.srb.core.pojo.entity.LendItem;
import jw.srb.core.pojo.vo.InvestVO;
import jw.srb.core.service.LendItemService;
import jw.srb.core.service.LendService;
import jw.srb.core.service.UserBindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2021/9/23 11:12
 * @updateDate 2021/9/23 11:12     
 * @version 1.0
 **/
@Service
public class LendItemServiceImpl extends ServiceImpl<LendItemMapper, LendItem> implements LendItemService {
    @Resource
    private LendMapper lendMapper;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserBindService userBindService;

    @Resource
    private LendService lendService;

    @Resource
    private TransFlowService transFlowService;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public List<LendItem> selectByLendId(Long lendId, Integer status) {
        return null;
    }

    @Override
    public List<LendItem> selectByLendId(Long lendId) {
        return null;
    }

    @Override
    public String commitInvest(InvestVO investVO) {
        return null;
    }

    @Override
    public void notify(Map<String, Object> paramMap) {

    }
}
