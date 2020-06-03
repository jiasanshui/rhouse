package edu.rent.house.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface RateDao {

    @Insert("insert into t_rate(order_id,house_id,user_id,score,content) " +
            "values(#{orderId},#{houseId},#{userId},#{score},#{content})")
    Integer saveRate(Map map);

    @Delete("delete from t_rate where id=#{id}")
    Integer deleteRate(Integer id);
}
