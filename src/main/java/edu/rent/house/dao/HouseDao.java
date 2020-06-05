package edu.rent.house.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HouseDao {


    @Select("select h.*,u.username from t_house h left join t_user u on h.user_id=u.id ")
    List<Map> getHouseList();

    @Select("select * from t_facility where id=#{fid}")
    Map selectFacilityById(String fid);

    @Select("select * from t_facility")
    List<Map> getFacilityList();

    @Select("select h.*,u.username from t_house h left join t_user u on h.user_id=u.id where h.id=#{houseId}")
    Map getHouseById(Integer houseId);

    @Insert("insert into t_house(img,room_num,bed_num,area,people,house_des,house_facility,addr," +
            "addr_info,house_price,user_id) values(#{img},#{roomNum},#{bedNum},#{area},#{people}," +
            "#{houseDes},#{houseFacility},#{addr},#{addrInfo},#{housePrice},#{userId}")
    Integer saveHouse(Map map);

    @Select("select h.*,u.username from t_house h left join t_user u on h.user_id=u.id where user_id=#{userId}")
    List<Map> getMyHouse(Integer userId);

    @Delete("delete from t_house where id=#{id}")
    Integer deleteHouse(Integer id);
}
