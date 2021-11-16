package com.example.Orders;

import com.example.Orders.Conrtoller.OrdersController;
import com.example.Orders.Model.Orders;
import com.example.Orders.Service.OrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdersController.class)
public class WebMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrdersService ordersService;
    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    public void get200StatusFromServer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get_all").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getAllOrders() throws Exception
    {
        ArrayList<Orders> orders = new ArrayList<>();
        orders.add(new Orders(1.0,2,"test"));
        orders.add(new Orders(1.0,2,"test1"));
        orders.add(new Orders(1.0,2,"test2"));
        orders.add(new Orders(1.0,2,"test3"));

        when(ordersService.getOrders()).thenReturn(orders);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/get_all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").isNotEmpty());
    }

    @Test
    public void getAllOrdersByItem() throws Exception
    {
        ArrayList<Orders> orders = new ArrayList<>();
        orders.add(new Orders(2.0,2,"test"));
        orders.add(new Orders(1.0,2,"test"));
        orders.add(new Orders(1.0,2,"test"));
        orders.add(new Orders(4.0,2,"test"));

        ArrayList<Orders> result = new ArrayList<>();

        result.add(orders.get(1));
        result.add(orders.get(2));

        when(ordersService.getOrderByItem("test")).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/get_by_item").param("item","test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].item").value("test"));
    }

    @Test
    public void addOrder() throws Exception
    {
        Orders order = new Orders(1.0,2,"test");
        when(ordersService.addOrder(order)).thenCallRealMethod(); ;
        mockMvc.perform(MockMvcRequestBuilders
                .post("/add_order")
                .content(asJsonString(order))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
