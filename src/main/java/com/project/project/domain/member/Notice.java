package com.project.project.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.thymeleaf.spring6.processor.SpringActionTagProcessor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID notice_key;

    @ManyToOne
    @JoinColumn(name = "admin_key", nullable = false)
    private Admin admin;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String img;

    @Column(nullable = true)
    private Integer hit;

    @Column(nullable = true)
    private LocalDate date;

    @Builder
    public Notice(String title, String img, Integer hit, LocalDate date){
        this.title = title;
        this.img = img;
        this.hit = hit;
        this.date = date;
    }
}
