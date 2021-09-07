package jw.srb.core;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/215:58
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/2 15:58
 * @updateDate 2021/9/2 15:58
 **/
@SpringBootApplication
@ComponentScan({"jw.srb"})
public class ServiceCoreApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ServiceCoreApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
