package com.example.Orders.Service;
import com.example.Orders.Model.Orders;
import com.example.Orders.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public Orders addOrder(Orders order){ return ordersRepository.save(order);}
    public List<Orders> getOrders() {return ordersRepository.findAll();}
    public List<Orders> getOrderByItem(String item) {return ordersRepository.findByItem(item);}
    public void deleteById(Long id) { ordersRepository.deleteById(id);}
}
