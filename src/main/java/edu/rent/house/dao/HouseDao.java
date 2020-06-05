package edu.rent.house.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HouseDao {


    @Select("select h.*,u.username from t_house h left join t_user u on h.user_id=u.id " +
            "where h.addr like concat('%',#{addr},'%')")
    List<Map> getHouseList(Map map);

    @Select("select * from t_facility where id=#{fid}")
    Map selectFacilityById(String fid);

    @Select("select * from t_facility")
    List<Map> getFacilityList();

    @Select("select h.*,u.username from t_house h left join t_user u on h.user_id=u.id where h.id=#{houseId}")
    Map getHouseById(Integer houseId);

    @Insert("insert into t_house(img,room_num,bed_num,area,people,house_des,house_facility,addr," +
            "addr_info,house_price,user_id) values(#{img},#{room_num},#{bed_num},#{area},#{people}," +
            "#{house_des},#{house_facility},#{addr},#{addr_info},#{house_price},#{userId})")
    Integer saveHouse(Map map);

    @Update("update t_house set img=#{img},room_num=#{room_num},bed_num=#{bed_num}," +
            "area=#{area},people=#{people},house_des=#{house_des},house_facility=#{house_facility}," +
            "addr=#{addr},addr_info=#{addr_info},house_price=#{house_price} where id=#{id}")
    Integer updateHouse(Map map);

    @Select("select h.*,u.username from t_house h left join t_user u on h.user_id=u.id where user_id=#{userId}")
    List<Map> getMyHouse(Integer userId);

    @Delete("delete from t_house where id=#{id}")
    Integer deleteHouse(Integer id);

    @Select("<script>select h.*,u.username from t_house h left join t_user u on h.user_id=u.id " +
            "where 1=1 " +
            "<if test=\"addr!=null and addr!=''\">and h.addr=#{addr}</if>" +
            "<if test=\"username!=null and username!=''\">and u.username=#{username}</if></script>")
    List<Map> getAdminHouseList(Map map);



}
