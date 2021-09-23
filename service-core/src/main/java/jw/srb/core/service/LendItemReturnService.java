package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/239:28
 */

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.LendItemReturn;

import java.util.List;
import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description 标的出借回款记录表 服务类
 * @updateUser
 * @createDate 2021/9/23 9:28
 * @updateDate 2021/9/23 9:28
 **/
public interface LendItemReturnService extends IService<LendItemReturn> {
    List<LendItemReturn> selectByLendId(Long lendId, Long userId);

    List<Map<String, Object>> addReturnDetail(Long lendReturnId);

    List<LendItemReturn> selectLendItemReturnList(Long id);
}
