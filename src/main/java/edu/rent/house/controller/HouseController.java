package edu.rent.house.controller;

import edu.rent.house.config.Response;
import edu.rent.house.serivce.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Response getHouseById(Integer houseId){
        return houseService.getHouseById(houseId);
    }
}
