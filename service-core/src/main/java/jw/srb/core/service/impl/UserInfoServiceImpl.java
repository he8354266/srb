package jw.srb.core.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2316:30
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jw.srb.common.exception.Assert;
import jw.srb.common.result.ResponseEnum;
import jw.srb.common.util.MD5;
import jw.srb.core.mapper.UserAccountMapper;
import jw.srb.core.mapper.UserInfoMapper;
import jw.srb.core.mapper.UserLoginRecordMapper;
import jw.srb.core.pojo.entity.UserAccount;
import jw.srb.core.pojo.entity.UserInfo;
import jw.srb.core.pojo.query.UserInfoQuery;
import jw.srb.core.pojo.vo.LoginVO;
import jw.srb.core.pojo.vo.RegisterVO;
import jw.srb.core.pojo.vo.UserIndexVO;
import jw.srb.core.pojo.vo.UserInfoVO;
import jw.srb.core.service.UserInfoService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/23 16:30
 * @updateDate 2021/9/23 16:30
 **/
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Resource
    private UserAccountMapper userAccountMapper = null;

    @Resource
    private UserLoginRecordMapper userLoginRecordMapper = null;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterVO registerVO) {
        //判断用户是否已被注册
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("mobile", registerVO.getMobile());
        Long count = baseMapper.selectCount(userInfoQueryWrapper);
        Assert.isTrue(count == 0, ResponseEnum.MOBILE_EXIST_ERROR);

        //插入用户信息记录：user_info
        UserInfo userInfo = new UserInfo();
        userInfo.setUserType(registerVO.getUserType());
        userInfo.setNickName(registerVO.getMobile());
        userInfo.setName(registerVO.getMobile());
        userInfo.setMobile(registerVO.getMobile());
        userInfo.setPassword(MD5.encrypt(registerVO.getPassword()));
        userInfo.setStatus(UserInfo.STATUS_NORMAL);
        userInfo.setHeadImg(UserInfo.USER_AVATAR);
        baseMapper.insert(userInfo);

        //插入用户账户记录：user_account
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userInfo.getId());
        userAccountMapper.insert(userAccount);

    }

    @Override
    public UserInfoVO login(LoginVO loginVO, String ip) {
        return null;
    }

    @Override
    public boolean checkMobile(String mobile) {
        return false;
    }

    @Override
    public IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery) {
        return null;
    }

    @Override
    public void lock(Long id, Integer status) {

    }

    @Override
    public UserIndexVO getIndexUserInfo(Long userId) {
        return null;
    }
}
