package jw.srb.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.UserLoginRecord;

import java.util.List;

/**
 * <p>
 * 用户登录记录表 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface UserLoginRecordService extends IService<UserLoginRecord> {

    List<UserLoginRecord> listTop50(Long userId);
}
