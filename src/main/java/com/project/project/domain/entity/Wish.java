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
@Table(name = "wish")
public class Wish {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(unique = true)
    private UUID wish_key;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_key", nullable = false)
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Wish(Item item, User user) {
        this.item = item;
        this.user = user;
    }
}
