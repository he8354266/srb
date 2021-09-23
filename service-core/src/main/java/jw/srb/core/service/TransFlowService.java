package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2311:21
 */

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.bo.TransFlowBO;
import jw.srb.core.pojo.entity.TransFlow;

import java.util.List;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/23 11:21
 * @updateDate 2021/9/23 11:21
 **/
public interface TransFlowService extends IService<TransFlow> {
    void saveTransFlow(TransFlowBO investTransFlowBO);

    boolean isSaveTransFlow(String agentBillNo);

    List<TransFlow> selectByUserId(Long userId);
}
