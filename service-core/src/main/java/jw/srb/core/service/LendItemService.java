package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2311:08
 */

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.LendItem;
import jw.srb.core.pojo.vo.InvestVO;

import java.util.List;
import java.util.Map;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/23 11:08
 * @updateDate 2021/9/23 11:08
 **/
public interface LendItemService extends IService<LendItem> {
    List<LendItem> selectByLendId(Long lendId, Integer status);

    List<LendItem> selectByLendId(Long lendId);

    String commitInvest(InvestVO investVO);

    void notify(Map<String, Object> paramMap);
}
