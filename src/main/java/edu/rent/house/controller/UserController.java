package edu.rent.house.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import edu.rent.house.config.Response;
import edu.rent.house.serivce.UserService;
import edu.rent.house.util.BaseUtil;
import edu.rent.house.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param map
     * @return
     */
    @RequestMapping("register")
    public Response register(@RequestParam Map map){
        return userService.register(map);
    }

    /**
     * 发送注册验证码
     * @param telephone
     * @return
     */
    @RequestMapping("sendSms")
    public Response sendSms(String telephone){
        String smsCode = BaseUtil.getSmsCode();
        try {
            SendSmsResponse sms = SmsUtil.sendLoginSms(telephone, smsCode);
            if("OK".equals(sms.getCode())){
                return new Response(200,"发送短信成功",smsCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(401,"发送短信失败",null);
    }

    /**
     * 用户登录
     * @param map
     * @return
     */
    @RequestMapping("login")
    public Response login(@RequestParam Map map) throws Exception{
        return userService.login(map);
    }

    /**
     * 获取登录验证码
     * @return
     */
    @RequestMapping("loginCheckCode")
    public Response loginCheckCode(String mobile) throws Exception{
        String code = BaseUtil.getSmsCode();
        Map user = userService.selectUserByAccount(mobile);
        Map resultMap = new HashMap();
        if(user!=null){
            resultMap.put("code",code);
            resultMap.put("user",user);
            SmsUtil.sendLoginSms(mobile,code);
            return new Response(200,"账号存在，发送验证码",resultMap);
        }
        return new Response(401,"账号不存在，无法使用短信登录",null);
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @RequestMapping("updatePass")
    public Response updatePass(@RequestParam Map map){
        return userService.updatePass(map);
    }


    /**
     * 修改个人信息
     * @param map
     * @return
     */
    @RequestMapping("updateUser")
    public Response updateUser(@RequestParam Map map){
        return userService.updateUser(map);
    }

    /**
     * 获取用户列表
     * @param map
     * @return
     */
    @RequestMapping("getUserList")
    public Response getUserList(@RequestParam Map map){
        return userService.getUserList(map);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("deleteUser")
    public Response deleteUser(Integer id){
        return userService.deleteUser(id);
    }

    /**
     * 系统登录
     * @param map
     * @return
     */
    @RequestMapping("sysLogin")
    public Response sysLogin(@RequestParam Map map){
        return userService.sysLogin(map);
    }
}
