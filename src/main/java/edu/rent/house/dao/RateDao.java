package edu.rent.house.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RateDao {

    @Insert("insert into t_rate(order_id,house_id,user_id,score,content) " +
            "values(#{orderId},#{houseId},#{userId},#{score},#{content})")
    Integer saveRate(Map map);

    @Delete("delete from t_rate where id=#{id}")
    Integer deleteRate(Integer id);

    @Select("select r.*,u.username from t_rate r " +
            "left join t_user u on r.user_id=u.id where r.house_id=#{houseId}")
    List<Map> getRateListByHouse(Integer houseId);

    @Select("<script>select r.*,u.username,o.order_num from t_rate r " +
            "left join t_user u on r.user_id=u.id " +
            "left join t_order o on r.order_id=o.id where 1=1" +
            "<if test=\"username!=null and username!=''\">and u.username=#{username}</if>" +
            "<if test=\"order_num!=null and order_num!=''\">and o.order_num=#{order_num}</if></script> ")
    List<Map> getRateList(Map map);
}
