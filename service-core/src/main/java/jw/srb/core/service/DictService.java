package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/915:11
 */

import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.Dict;

import java.io.InputStream;
import java.util.List;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/9 15:11
 * @updateDate 2021/9/9 15:11
 **/
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);

    List listDictData();

    List<Dict> listByParentId(Long parentId);

    List<Dict> findByDictCode(String dictCode);

    String getNameByParentDictCodeAndValue(String education, Integer education1);
}
