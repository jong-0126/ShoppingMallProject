package com.project.project.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orderItem")
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(unique = true)
    private UUID orderItem_key;  // 고유 ID

    @ManyToOne
    @JoinColumn(name = "order_key", nullable = false)
    private Orders orders;  // 주문 외래키

    @ManyToOne
    @JoinColumn(name = "item_key", nullable = false)
    private Item item;  // 아이템 외래키

    @Column(nullable = false)
    private Integer cnt;  // 아이템 수량

    @Column(nullable = false)
    private Integer price;  // 아이템 가격

    // 생성자
    public OrderItem(Orders orders, Item item, Integer cnt, Integer price) {
        this.orders = orders;
        this.item = item;
        this.cnt = cnt;
        this.price = price;
    }
}
