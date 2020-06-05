package edu.rent.house.controller;

import edu.rent.house.config.Response;
import edu.rent.house.serivce.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("house")
public class HouseController{

    @Autowired
    private HouseService houseService;

    /**
     * 获取房间列表
     * @return
     */
    @RequestMapping("getHouseList")
    public Response getHouseList(){
        return houseService.getHouseList();
    }

    /**
     * 根据id获取房间
     * @param houseId
     * @return
     */
    @RequestMapping("getHouseById")
    public Response getHouseById(Integer houseId,Integer userId){
        return new Response(200,"查询成功",houseService.getHouseById(houseId,userId));
    }

    /**
     * 新增房间信息
     * @param map
     * @return
     */
    @RequestMapping("saveHouse")
    public Response saveHouse(@RequestParam Map map){
        return houseService.saveHouse(map);
    }

    /**
     * 房主的房间
     * @param userId
     * @return
     */
    @RequestMapping("getMyHouse")
    public Response getMyHouse(Integer userId){
        return houseService.getMyHouse(userId);
    }

    /**
     * 删除房间
     * @param id
     * @return
     */
    @RequestMapping("deleteHouse")
    public Response deleteHouse(Integer id){
        return houseService.deleteHouse(id);
    }

    /**
     * 获取房间设施列表
     * @return
     */
    @RequestMapping("getFacilityList")
    public Response getFacilityList(){
        return houseService.getFacilityList();
    }
}
