package edu.rent.house.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CollectDao {

    @Select("select * from t_collect where user_id=#{userId}")
    List<Map> getCollectList(Integer userId);

    @Insert("insert into t_collect(user_id,house_id) values(userId,houseId)")
    Integer saveCollect(Map map);

    @Delete("delete from t_collect where id=#{id}")
    Integer deleteCollect(Integer id);
}
