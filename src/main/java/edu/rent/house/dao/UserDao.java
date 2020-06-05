package edu.rent.house.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    @Select("<script>select * from t_user where 1=1 " +
            "<if test=\"account!=null and account!=''\">and account=#{account}</if>" +
            "<if test=\"username!=null and username!=''\">and username=#{username}</if></script>")
    List<Map> getUserList(Map map);

    //添加用户
    @Insert("insert into t_user(account,username,password,email,identity,status) " +
            "values(#{mobile},#{username},#{password},#{email},1,1)")
    int saveUser(Map map);

    @Select("select * from t_user where account=#{account}")
    Map selectUserByAccount(String account);

    @Select("select * from t_user where id=#{id}")
    Map getUserById(Integer id);

    @Delete("delete from t_user where id=#{id}")
    int deleteUser(Integer id);

    //用户登录
    @Select("select * from t_user where username=#{username} and password=#{password}")
    Map selectUserByLogin(Map map);

    //获取原密码
    @Select("select password from t_user where id=#{id}")
    String getPassWordById(int id);

    //修改密码
    @Update("update t_user set password=#{newPassword} where id=#{id}")
    int updatePass(Map map);

    //修改用户
    @Update("update t_user set username=#{username}," +
            "email=#{email} where id=#{id}")
    int updateUser(Map map);

    @Update("update t_user set identity=2 where id=#{userId}")
    int updateUserToHouseMaster(Integer userId);

    @Select("select * from t_user where id=#{id}")
    Map selectUserById(Integer id);

    @Select("select * from t_system_user where account=#{account} and password=#{password}")
    Map sysLogin(Map map);
}
