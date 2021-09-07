package jw.srb.core.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/717:06
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jw.srb.core.mapper.BorrowerAttachMapper;
import jw.srb.core.mapper.BorrowerMapper;
import jw.srb.core.mapper.UserInfoMapper;
import jw.srb.core.mapper.UserIntegralMapper;
import jw.srb.core.pojo.entity.Borrower;
import jw.srb.core.pojo.entity.UserInfo;
import jw.srb.core.pojo.vo.BorrowerApprovalVO;
import jw.srb.core.pojo.vo.BorrowerDetailVO;
import jw.srb.core.pojo.vo.BorrowerVO;
import jw.srb.core.service.BorrowerAttachService;
import jw.srb.core.service.BorrowerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/7 17:06
 * @updateDate 2021/9/7 17:06
 **/
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, Borrower> implements BorrowerService {

    @Resource
    private UserInfoMapper userInfoMapper = null;

    @Resource
    private BorrowerAttachMapper borrowerAttachMapper = null;

    @Resource
    private BorrowerAttachService borrowerAttachService = null;

//    @Resource
//    private DictService dictService=null;

    @Resource
    private UserIntegralMapper userIntegralMapper = null;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId) {
        //获取用户基本信息
        UserInfo userInfo = userInfoMapper.selectById(userId);

        //保存借款人信息
        Borrower borrower = new Borrower();

    }

    @Override
    public Integer getStatusByUserId(Long userId) {
        return null;
    }

    @Override
    public IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword) {
        return null;
    }

    @Override
    public BorrowerDetailVO getBorrowerDetailVOById(Long id) {
        return null;
    }

    @Override
    public void approval(BorrowerApprovalVO borrowerApprovalVO) {

    }
}
