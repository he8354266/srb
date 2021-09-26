package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2316:24
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.UserInfo;
import jw.srb.core.pojo.query.UserInfoQuery;
import jw.srb.core.pojo.vo.LoginVO;
import jw.srb.core.pojo.vo.RegisterVO;
import jw.srb.core.pojo.vo.UserIndexVO;
import jw.srb.core.pojo.vo.UserInfoVO;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/23 16:24
 * @updateDate 2021/9/23 16:24
 **/
public interface UserInfoService extends IService<UserInfo> {
    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);

    boolean checkMobile(String mobile);

    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id, Integer status);

    UserIndexVO getIndexUserInfo(Long userId);
}
