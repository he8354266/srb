package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2311:15
 */

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.UserAccount;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/23 11:15
 * @updateDate 2021/9/23 11:15
 **/
public interface UserAccountService extends IService<UserAccount> {

    String commitCharge(BigDecimal chargeAmt, Long userId);

    String notify(Map<String, Object> paramMap);

    BigDecimal getAccount(Long userId);

    String commitWithdraw(BigDecimal fetchAmt, Long userId);

    void notifyWithdraw(Map<String, Object> paramMap);
}
