package com.project.project.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(unique = true, nullable = false)
    private UUID address_key;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = true)
    private String recipient;

    @Column(nullable = true)
    private Integer zip_code;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String address_detail;

    @Column(nullable = true)
    private String recipient_tel;

    @Column(nullable = true)
    private Boolean def;

    @Builder
    public Address(String recipient, Integer zip_code, String address, String address_detail, String recipient_tel, Boolean def){

        this.recipient = recipient;
        this.zip_code = zip_code;
        this.address = address;
        this.address_detail = address_detail;
        this.recipient_tel = recipient_tel;
        this.def = def;

    }

}
