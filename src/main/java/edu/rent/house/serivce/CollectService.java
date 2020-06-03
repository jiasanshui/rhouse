package edu.rent.house.serivce;

import edu.rent.house.config.Response;
import edu.rent.house.dao.CollectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CollectService {

    @Autowired
    private CollectDao collectDao;
    @Autowired
    private HouseService houseService;

    public Response getCollectList(Integer userId) {
        List<Map> collectList = collectDao.getCollectList(userId);
        for (Map collect : collectList) {
            Integer houseId = Integer.valueOf(String.valueOf(collect.get("house_id")));
            Map houseMap = houseService.getHouseById(houseId);
            collect.put("houseData",houseMap);
        }
        return new Response(200,"查询成功",collectList);
    }


    public Response saveCollect(Map map) {
        Integer saveInt = collectDao.saveCollect(map);
        if(saveInt>0){
            return new Response(200,"添加成功",null);
        }
        return new Response(501,"添加失败",null);
    }

    public Response deleteCollect(Integer id) {
        Integer delInt = collectDao.deleteCollect(id);
        if(delInt>0){
            return new Response(200,"删除成功",null);
        }
        return new Response(501,"删除失败",null);
    }
}
