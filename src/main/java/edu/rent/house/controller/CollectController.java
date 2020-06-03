package edu.rent.house.controller;

import edu.rent.house.config.Response;
import edu.rent.house.serivce.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("collect")
@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;

    @RequestMapping("getCollectList")
    public Response getCollectList(Integer userId){
        return collectService.getCollectList(userId);
    }

    @RequestMapping("saveCollect")
    public Response saveCollect(@RequestParam Map map){
        return collectService.saveCollect(map);
    }

    @RequestMapping("deleteCollect")
    public Response deleteCollect(Integer id){
        return collectService.deleteCollect(id);
    }

}
