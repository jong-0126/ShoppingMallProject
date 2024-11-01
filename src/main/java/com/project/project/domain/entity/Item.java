package com.project.project.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(unique = true)
    private UUID item_key;

    @Column(nullable = false)
    private Boolean category;

    @Column(length = 20, nullable = false)
    private String item_name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String item_img;

    @Column(length = 500, nullable = false)
    private String item_content;

    @Column(nullable = false)
    private Integer sale_price;

    @Column(nullable = false)
    private Integer cnt;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wish = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    public String getItemImage() {
        return "data:image/jpeg;base64," + this.item_img; // itemImage가 Base64로 저장된 문자열이라 가정
    }

    @Builder
    public Item(Boolean category, String item_name, String item_img, String item_content, Integer sale_price, Integer cnt){

        this.category = category;
        this.item_name = item_name;
        this.item_img = item_img;
        this.item_content = item_content;
        this.sale_price = sale_price;
        this.cnt = cnt;

    }

}
