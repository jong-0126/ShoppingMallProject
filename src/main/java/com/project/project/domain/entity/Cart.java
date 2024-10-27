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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(unique = true)
    private UUID cart_key;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_key", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer cnt = 0;

    @Column(nullable = false)
    private LocalDate date;

    @Builder
    public Cart(Integer cnt){
        this.cnt = (cnt != null && cnt > 0) ? cnt : 0;
        this.date = LocalDate.now();
    }
}
