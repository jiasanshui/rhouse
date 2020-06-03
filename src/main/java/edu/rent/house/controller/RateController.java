package edu.rent.house.controller;

import edu.rent.house.config.Response;
import edu.rent.house.serivce.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("rate")
@RestController
public class RateController {

    @Autowired
    private RateService rateService;

    /**
     * 新增评论
     * @param map
     * @return
     */
    @RequestMapping("saveRate")
    public Response saveRate(@RequestParam Map map){
        return rateService.saveRate(map);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping("deleteRate")
    public Response deleteRate(Integer id){
        return rateService.deleteRate(id);
    }
}
