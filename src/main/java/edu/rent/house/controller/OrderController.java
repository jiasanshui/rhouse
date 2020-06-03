package edu.rent.house.controller;

import edu.rent.house.config.Response;
import edu.rent.house.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     * @param map
     * @return
     */
    @RequestMapping("saveOrder")
    public Response saveOrder(@RequestParam Map map){
        return orderService.saveOrder(map);
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @RequestMapping("cancelOrder")
    public Response cancelOrder(Integer id){
        return orderService.cancelOrder(id);
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @RequestMapping("deleteOrder")
    public Response deleteOrder(Integer id){
        return orderService.deleteOrder(id);
    }

    /**
     * 确认订单
     * @param id
     * @return
     */
    @RequestMapping("confirmOrder")
    public Response confirmOrder(Integer id){
        return orderService.confirmOrder(id);
    }

    /**
     * 完成订单
     * @param id
     * @return
     */
    @RequestMapping("finishOrder")
    public Response finishOrder(Integer id){
        return orderService.finishOrder(id);
    }
}
