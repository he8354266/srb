package jw.srb.sms.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/309:18
 */

import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/30 9:18
 * @updateDate 2021/9/30 9:18
 **/
public interface SmsService {
    void send(String mobile, String templateCode, Map<String, Object> param);
}
