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
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(unique = true)
    private UUID review_key;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_key", nullable = false)
    private Item item;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = true)
    private LocalDate upload_date;

    @Builder
    public Review(User user, Item item, String title, String content){

        this.user = user;
        this.item = item;
        this.title = title;
        this.content = content;
        this.upload_date = LocalDate.now();
    }
}
