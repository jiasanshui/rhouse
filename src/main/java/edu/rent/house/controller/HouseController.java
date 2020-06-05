package edu.rent.house.controller;

import edu.rent.house.config.Response;
import edu.rent.house.serivce.HouseService;
import edu.rent.house.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("house")
public class HouseController{

    @Autowired
    private HouseService houseService;
    @Autowired
    private FtpUtil ftpUtil = new FtpUtil();

    /**
     * 获取房间列表
     * @return
     */
    @RequestMapping("getHouseList")
    public Response getHouseList(@RequestParam Map map){
        return houseService.getHouseList(map);
    }

    /**
     * 根据id获取房间
     * @param houseId
     * @return
     */
    @RequestMapping("getHouseById")
    public Response getHouseById(Integer houseId){
        return new Response(200,"查询成功",houseService.getHouseById(houseId));
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
     * 更新房间信息
     * @param map
     * @return
     */
    @RequestMapping("updateHouse")
    public Response updateHouse(@RequestParam Map map){
        return houseService.updateHouse(map);
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

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("uploadImg")
    public Response uploadImg(@RequestParam("file") MultipartFile file){
        String upload = ftpUtil.upload(file);
        if("'upload_fail'".equals(upload)){
            return new Response(501,"上传失败",null);
        }
        return new Response(200,"上传成功",upload);
    }

    /**
     * 超级管理员获取房屋列表
     * @param map
     * @return
     */
    @RequestMapping("getAdminHouseList")
    public Response getAdminHouseList(@RequestParam Map map){
        return houseService.getAdminHouseList(map);
    }






}
