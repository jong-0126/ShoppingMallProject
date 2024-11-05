package com.project.project.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
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
    private String postcode;

    @Column(nullable = false)
    private String roadAddress;

    @Column(nullable = false)
    private String jibunAddress;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private String extraAddress;

    @Builder
    public Address(String postcode, String roadAddress, String jibunAddress, String detailAddress, String extraAddress){

        this.postcode = postcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;

    }

}
