package com.project.project.domain.entity;

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
    @Column(unique = true)
    private UUID address_key;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String zip_code;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String address_detail;

    @Column(nullable = false)
    private String recipient_tel;

    @Column(nullable = false)
    private Boolean is_default;

    @Builder
    public Address(String recipient, String zip_code, String address, String address_detail, String recipient_tel, Boolean is_default){

        this.recipient = recipient;
        this.zip_code = zip_code;
        this.address = address;
        this.address_detail = address_detail;
        this.recipient_tel = recipient_tel;
        this.is_default = is_default;

    }

}
