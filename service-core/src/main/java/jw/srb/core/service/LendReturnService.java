package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2611:19
 */

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.LendReturn;

import java.util.List;
import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/26 11:19
 * @updateDate 2021/9/26 11:19
 **/
public interface LendReturnService extends IService<LendReturn> {
    List<LendReturn> selectByLendId(Long lendId);

    String commitReturn(Long lendReturnId, Long userId);

    void notify(Map<String, Object> paramMap);
}
