package com.example.Orders.Model;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private Integer quantity;
    private String item;
    private Date time;

    public Orders( Double price, Integer quantity, String item) {

        this.price = price;
        this.quantity = quantity;
        this.item = item;

    }

}
