package com.project.project.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
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
    @Column(name = "order_key", unique = true, nullable = false)
    private UUID order_key;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = true)
    private Integer price;

    @Column(nullable = true)
    private LocalDate order_date;

    @Column(length = 20, nullable = true)
    private String recipient;

    @Column(length = 10, nullable = true)
    private String zip_code;

    @Column(length = 100, nullable = true)
    private String address;

    @Column(length = 100, nullable = true)
    private String address_detail;

    @Column(length = 20, nullable = true)
    private String recipient_tel;

    @Column(nullable = true)
    private int item_cnt;

    @Builder
    public Orders(Integer price, LocalDate order_date, String recipient, String zip_code, String address, String address_detail, String recipient_tel, int item_cnt) {
        this.price = price;
        this.order_date = order_date;
        this.recipient = recipient;
        this.zip_code = zip_code;
        this.address = address;
        this.address_detail = address_detail;
        this.recipient_tel = recipient_tel;
        this.item_cnt = item_cnt;
    }
}
