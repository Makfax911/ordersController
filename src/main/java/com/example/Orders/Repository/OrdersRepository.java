package com.example.Orders.Repository;
import com.example.Orders.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByItem(String item);
}
