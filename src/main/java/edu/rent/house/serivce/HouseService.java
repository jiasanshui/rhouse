package edu.rent.house.serivce;

import edu.rent.house.config.Response;
import edu.rent.house.dao.HouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HouseService {

    @Autowired
    private HouseDao houseDao;

    public Response getHouseList(){
        List<Map> houseList = houseDao.getHouseList();
        for (Map house : houseList) {
            String facilityStr = String.valueOf(house.get("house_facility"));
            String[] fas = facilityStr.split(",");
            List<Map> tempList = new ArrayList<>();
            for (String fid : fas) {
                Map facility = houseDao.selectFacilityById(fid);
                tempList.add(facility);
            }
            house.put("facilityList",tempList);
        }
        return new Response(200,"查询成功",houseList);
    }

    public Response getHouseById(Integer houseId) {
        Map house = houseDao.getHouseById(houseId);
        String facilityStr = String.valueOf(house.get("house_facility"));
        String[] fas = facilityStr.split(",");
        List<Map> tempList = new ArrayList<>();
        for (String fid : fas) {
            Map facility = houseDao.selectFacilityById(fid);
            tempList.add(facility);
        }
        house.put("facilityList",tempList);
        return new Response(200,"查询成功",house);
    }
}
