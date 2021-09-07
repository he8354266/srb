package jw.srb.core.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/716:58
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jw.srb.core.pojo.entity.Borrower;
import jw.srb.core.pojo.vo.BorrowerApprovalVO;
import jw.srb.core.pojo.vo.BorrowerDetailVO;
import jw.srb.core.pojo.vo.BorrowerVO;

/**
 * @author zkjy
 * @version 1.0
 * @description 借款人 服务类
 * @updateUser
 * @createDate 2021/9/7 16:58
 * @updateDate 2021/9/7 16:58
 **/
public interface BorrowerService extends IService<Borrower> {
    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(Long userId);

    IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword);

    BorrowerDetailVO getBorrowerDetailVOById(Long id);

    void approval(BorrowerApprovalVO borrowerApprovalVO);
}
