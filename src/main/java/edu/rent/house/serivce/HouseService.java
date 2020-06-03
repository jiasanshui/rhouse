package edu.rent.house.serivce;

import edu.rent.house.config.Response;
import edu.rent.house.dao.HouseDao;
import edu.rent.house.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HouseService {

    @Autowired
    private HouseDao houseDao;
    @Autowired
    private UserDao userDao;

    public Response getHouseList(){
        List<Map> houseList = houseDao.getHouseList();
        for (Map house : houseList) {
            String facilityStr = String.valueOf(house.get("house_facility"));
            this.getFacilityList(house, facilityStr);
        }
        return new Response(200,"查询成功",houseList);
    }

    public Map getHouseById(Integer houseId) {
        Map house = houseDao.getHouseById(houseId);
        String facilityStr = String.valueOf(house.get("house_facility"));
        this.getFacilityList(house, facilityStr);
        return house;
    }

    public Response saveHouse(Map map){
        Integer saveInt = houseDao.saveHouse(map);
        if(saveInt>0){
            //新增成功，普通用户成为房主身份
            userDao.updateUserToHouseMaster(Integer.valueOf(String.valueOf(map.get("userId"))));
            return new Response(200,"新增成功",null);
        }
        return new Response(501,"新增失败",null);
    }

    public Response getMyHouse(Integer userId){
        List<Map> houseList = houseDao.getMyHouse(userId);
        for (Map house : houseList) {
            this.getFacilityList(house,String.valueOf(house.get("house_facility")));
        }
        return new Response(200,"查询成功",houseList);
    }

    public void getFacilityList(Map house,String facilityStr){
        if(!StringUtils.isEmpty(facilityStr)){
            String[] fas = facilityStr.split(",");
            List<Map> tempList = new ArrayList<>();
            for (String fid : fas) {
                Map facility = houseDao.selectFacilityById(fid);
                tempList.add(facility);
            }
            house.put("facilityList",tempList);
        }else{
            house.put("facilityList","暂无设施信息");
        }
    }

    public Response deleteHouse(Integer id){
        Integer delInt = houseDao.deleteHouse(id);
        if(delInt>0){
            return new Response(200,"删除成功",null);
        }
        return new Response(501,"删除失败",null);
    }
}
