package com.project.project.domain.entitiy;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(unique = true, nullable = false)
    private UUID item_key;

    @ManyToOne
    @JoinColumn(name = "category_key", nullable = false)
    private Category category;

    @Column(length = 20, nullable = true)
    private String item_name;

    @Column(length = 100, nullable = true)
    private String item_img;

    @Column(length = 500, nullable = true)
    private String item_content;

    @Column(nullable = true)
    private Integer sale_price;

    @Column(nullable = true)
    private Integer cnt;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wish = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    @Builder
    public Item(String item_name, String item_img, String item_content, Integer sale_price, Integer cnt){

        this.item_name = item_name;
        this.item_img = item_img;
        this.item_content = item_content;
        this.sale_price = sale_price;
        this.cnt = cnt;

    }

}
