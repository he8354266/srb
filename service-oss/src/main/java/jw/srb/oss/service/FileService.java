package jw.srb.oss.service;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/308:45
 */

import java.io.InputStream;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/30 8:45
 * @updateDate 2021/9/30 8:45
 **/
public interface FileService {
    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);

    void removeFile(String url);
}
