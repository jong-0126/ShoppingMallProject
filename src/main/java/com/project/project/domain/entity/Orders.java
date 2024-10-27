package com.project.project.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
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

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalDate order_date;

    @Column(length = 20, nullable = false)
    private String recipient;

    @Column(length = 10, nullable = false)
    private String zip_code;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 100, nullable = false)
    private String address_detail;

    @Column(length = 20, nullable = false)
    private String recipient_tel;

    @Column(nullable = false)
    private int item_cnt = 0;

    @Builder
    public Orders(User user, Integer price, String recipient, String zip_code, String address, String address_detail, String recipient_tel, Integer item_cnt) {
        this.user = user;
        this.price = price;
        this.order_date = LocalDate.now();
        this.recipient = recipient;
        this.zip_code = zip_code;
        this.address = address;
        this.address_detail = address_detail;
        this.recipient_tel = recipient_tel;
        this.item_cnt = item_cnt != null ? item_cnt : 0;
    }
}
