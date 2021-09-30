package jw.srb.sms;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/309:12
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/30 9:12
 * @updateDate 2021/9/30 9:12
 **/
@SpringBootApplication
@ComponentScan({"jw.srb"})
@EnableFeignClients
public class ServiceSmsApplication {
    public static void main(String[] args) {
        try{
            SpringApplication.run(ServiceSmsApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
