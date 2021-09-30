package jw.srb.oss.util;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/308:41
 */

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/30 8:41
 * @updateDate 2021/9/30 8:41
 **/
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties implements InitializingBean {
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;

    public static String ENDPOINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    
    //当私有成员被赋值后，此方法自动被调用，从而初始化常量
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
    }


}
