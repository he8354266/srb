package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/1315:54
 */

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.UserBind;
import jw.srb.core.pojo.vo.UserBindVO;

import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/13 15:54
 * @updateDate 2021/9/13 15:54
 **/
public interface UserBindService extends IService<UserBind> {
    String commitBindUser(UserBindVO userBindVO, Long userId);

    void notify(Map<String, Object> paramMap);

    String getBindCodeByUserId(Long userId);
}
