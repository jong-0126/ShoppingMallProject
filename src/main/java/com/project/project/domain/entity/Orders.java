package com.project.project.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(unique = true)
    private UUID order_key;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_key", nullable = false)
    private Address address;

    @Column(nullable = false)
    private Integer total_price;

    @Column(nullable = false)
    private Integer total_cnt = 0;

    @Column(nullable = false)
    private LocalDate order_date;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Orders(User user, Address address, List<OrderItem> orderItems, Integer total_price, Integer total_cnt) {
        this.user = user;
        this.address = address;
        this.orderItems = orderItems;
        this.total_price = total_price;
        this.order_date = LocalDate.now();
        this.total_cnt = total_cnt != null ? total_cnt : 0;
    }
}
