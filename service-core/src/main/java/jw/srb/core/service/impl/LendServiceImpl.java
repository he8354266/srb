package jw.srb.core.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/1314:02
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jw.srb.core.mapper.LendMapper;
import jw.srb.core.pojo.entity.BorrowInfo;
import jw.srb.core.pojo.entity.Lend;
import jw.srb.core.pojo.vo.BorrowInfoApprovalVO;
import jw.srb.core.service.LendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/13 14:02
 * @updateDate 2021/9/13 14:02
 **/
@Slf4j
@Service
public class LendServiceImpl extends ServiceImpl<LendMapper, Lend> implements LendService {
    @Override
    public void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo) {

    }

    @Override
    public List<Lend> selectList() {
        return null;
    }

    @Override
    public Map<String, Object> getLendDetail(Long id) {
        return null;
    }

    @Override
    public BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, Integer totalmonth, Integer returnMethod) {
        return null;
    }

    @Override
    public void makeLoan(Long id) {

    }
}
