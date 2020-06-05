package edu.rent.house.serivce;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.rent.house.config.Response;
import edu.rent.house.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Response getUserList(Map map){
        List<Map> userList = userDao.getUserList(map);
        for (Map user : userList) {
            int status = Integer.parseInt(user.get("status") + "");
            if(status==0){
                user.put("status","黑户");
            }else if(status==1){
                user.put("status","正常");
            }
        }
        return new Response(200,"查询成功",userList);
    }

    public int saveUser(Map map) {
        Map user = userDao.selectUserByAccount(map.get("mobile")+"");
        if(user!=null){
            return 2;
        }
        return userDao.saveUser(map);
    }

    public Map getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public Response deleteUser(Integer id) {
        int i = userDao.deleteUser(id);
        if(i>0){
            return new Response(200,"删除成功",null);
        }
        return new Response(501,"删除失败",null);
    }

    public Response login(Map map) throws Exception{
        Integer loginType = Integer.valueOf(String.valueOf(map.get("login_type")));
        String mobile = String.valueOf(map.get("mobile"));
        Map user = null;
        if(loginType==1){ //账号密码登录
            user = userDao.selectUserByLogin(map);
            if(user==null){
                return new Response(401,"账号或者密码错误",null);
            }
        }else if(loginType==2){ //手机号登录
            user = userDao.selectUserByAccount(mobile);
        }
        return new Response(200,"登录成功",user);
    }

    public Response updatePass(Map map) {
        //先判断原密码是否正确
        String oldPassword = map.get("oldPassword") + "";
        String password = userDao.getPassWordById(Integer.parseInt(map.get("id") + ""));
        if(!oldPassword.equals(password)){
            return new Response(401,"原密码不正确",null);
        }
        int updateInt = userDao.updatePass(map);
        if(updateInt>0){
            return new Response(200,"修改成功",null);
        }
        return new Response(501,"修改失败",null);
    }

    public Response register(Map map) {
        //先判断该用户是否已经被注册
        String mobile = map.get("mobile") + "";
        Map user = userDao.selectUserByAccount(mobile);
        if(user!=null&&!"".equals(user)){
            return new Response(401,"当前账号已存在",null);
        }
        int saveInt = userDao.saveUser(map);
        if(saveInt>0){
            return new Response(200,"注册成功",null);
        }
        return new Response(501,"注册失败",null);
    }

    public Map selectUserByAccount(String mobile) {
        Map user = userDao.selectUserByAccount(mobile);
        return user;
    }

    public Response updateUser(Map map) {
        int i = userDao.updateUser(map);
        if(i>0){
            Map user = userDao.selectUserById(Integer.valueOf(String.valueOf(map.get("id"))));
            return new Response(200,"修改成功",user);
        }
        return new Response(501,"修改失败",null);
    }

    public Response sysLogin(Map map) {
        Map user = userDao.sysLogin(map);
        if(user!=null){
            return new Response(200,"登录成功",user);
        }
        return new Response(501,"登录失败",null);
    }
}
