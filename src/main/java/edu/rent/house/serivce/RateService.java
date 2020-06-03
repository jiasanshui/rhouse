package edu.rent.house.serivce;

import edu.rent.house.config.Response;
import edu.rent.house.dao.OrderDao;
import edu.rent.house.dao.RateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RateService {

    @Autowired
    private RateDao rateDao;
    @Autowired
    private OrderDao orderDao;

    public Response saveRate(Map map){
        Integer saveInt = rateDao.saveRate(map);
        if(saveInt>0){
            Integer houseId = Integer.valueOf(String.valueOf(map.get("houseId")));
            orderDao.rateOrder(houseId);//订单已经评价
            return new Response(200,"添加成功",null);
        }
        return new Response(501,"添加失败",null);
    }

    public Response deleteRate(Integer id) {
        Integer delInt = rateDao.deleteRate(id);
        if(delInt>0){
            return new Response(200,"删除成功",null);
        }
        return new Response(501,"删除失败",null);
    }
}
