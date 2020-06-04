package edu.rent.house.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDao {

    @Insert("insert into t_order(order_num,user_id,house_id,start_time,end_time,days,price,status) " +
            "values(#{orderNum},#{userId},#{houseId},#{startTime},#{endTime},#{days},#{price},1)")
    Integer saveOrder(Map map);

    @Update("update t_order set status=0 where id=#{id}")
    Integer cancelOrder(Integer id);

    @Delete("delete from t_order where id=#{id}")
    Integer deleteOrder(Integer id);

    @Update("update t_order set status=2 where id=#{id}")
    Integer confirmOrder(Integer id);

    @Update("update t_order set status=3 where id=#{id}")
    Integer finishOrder(Integer id);

    @Update("update t_order set status=4 where id=#{id}")
    Integer rateOrder(Integer id);

    @Select("select o.*,u.username,h.img,h.addr,h.people,h.room_num from t_order o " +
            "left join t_user u on o.user_id=u.id " +
            "left join t_house h on o.house_id=h.id where o.user_id=#{userId}")
    List<Map> getOrderList(Integer userId);
}
