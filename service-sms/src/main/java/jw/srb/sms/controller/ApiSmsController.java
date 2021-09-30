package jw.srb.sms.controller;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2021/9/309:43
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jw.srb.common.exception.Assert;
import jw.srb.common.result.R;
import jw.srb.common.result.ResponseEnum;
import jw.srb.common.util.RandomUtils;
import jw.srb.common.util.RegexValidateUtils;
import jw.srb.sms.client.CoreUserInfoClient;
import jw.srb.sms.service.SmsService;
import jw.srb.sms.util.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2021/9/30 9:43
 * @updateDate 2021/9/30 9:43
 **/
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
//@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {
    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(@ApiParam(value = "手机号", required = true)
                  @PathVariable String mobile) {


        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);


        boolean result = coreUserInfoClient.checkMobile(mobile);
        log.info("result=>{}", result);
        Assert.isTrue(result == false, ResponseEnum.MOBILE_EXIST_ERROR);

        String code = RandomUtils.getFourBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);

        redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }
}
