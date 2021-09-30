package jw.srb.oss;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/2916:39
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/29 16:39
 * @updateDate 2021/9/29 16:39
 **/
@SpringBootApplication
@ComponentScan({"jw.srb"})
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }
}
