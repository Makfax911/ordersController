package com.example.Orders.Conrtoller;
import com.example.Orders.Model.Orders;
import com.example.Orders.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/add_order")
    public @ResponseBody Orders addOrder(@RequestBody Orders order){
        if(order.getTime() == null)
            order.setTime(Calendar.getInstance().getTime());
        return ordersService.addOrder(order);
    }

    @GetMapping("/get_all")
    @ResponseBody
    public List<Orders> getAll(){
        List<Orders> actual = ordersService.getOrders();
         actual = deleteOld(actual);
        return actual;
    }

    @GetMapping("/get_by_item")
    @ResponseBody
    public List<Orders> getByItem(@RequestParam String item){
        List<Orders> actual = ordersService.getOrderByItem(item);
        actual = deleteOld(actual);
        List<Orders> result = new ArrayList<>();
        double min = Double.MAX_VALUE;
        for(Orders order : actual){
            if(min > order.getPrice())
                min = order.getPrice();
        }
        for(Orders order : actual){
            if(min == order.getPrice())
                result.add(order);
        }
        return result;
    }

    private List<Orders> deleteOld(List<Orders> orders){
        List<Orders> result = new ArrayList<>();
        Date now = Calendar.getInstance().getTime();
        for(Orders order : orders){
            if(now.getTime() - order.getTime().getTime() >= 600000)
                ordersService.deleteById(order.getId());
            else
                result.add(order);
        }
        return result;
    }
}
