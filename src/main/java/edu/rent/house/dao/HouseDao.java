package edu.rent.house.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HouseDao {


    @Select("select * from t_house")
    List<Map> getHouseList();

    @Select("select * from t_facility where id=#{fid}")
    Map selectFacilityById(String fid);

    @Select("select * from t_house where id=#{houseId}")
    Map getHouseById(Integer houseId);
}
