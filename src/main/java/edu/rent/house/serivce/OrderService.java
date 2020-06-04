package edu.rent.house.serivce;

import edu.rent.house.config.Response;
import edu.rent.house.dao.OrderDao;
import edu.rent.house.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public Response saveOrder(Map map){
        String orderNum = BaseUtil.getNum();
        map.put("orderNum",orderNum);
        Integer saveInt = orderDao.saveOrder(map);
        if(saveInt>0){
            return new Response(200,"新增成功",null);
        }
        return new Response(501,"新增失败",null);
    }

    public Response cancelOrder(Integer id) {
        Integer cancelInt = orderDao.cancelOrder(id);
        if(cancelInt>0){
            return new Response(200,"取消成功",null);
        }
        return new Response(501,"取消失败",null);
    }

    public Response deleteOrder(Integer id) {
        Integer delInt = orderDao.deleteOrder(id);
        if(delInt>0){
            return new Response(200,"删除成功",null);
        }
        return new Response(501,"删除失败",null);
    }

    public Response confirmOrder(Integer id) {
        Integer conInt = orderDao.confirmOrder(id);
        if(conInt>0){
            return new Response(200,"房间确认预定成功",null);
        }
        return new Response(501,"房间确认预定失败",null);
    }

    public Response finishOrder(Integer id) {
        Integer finishInt = orderDao.finishOrder(id);
        if(finishInt>0){
            return new Response(200,"操作成功",null);
        }
        return new Response(501,"操作失败",null);
    }

    public Response getOrderList(Integer userId){
        return new Response(200,"查询成功",orderDao.getOrderList(userId));
    }
}
