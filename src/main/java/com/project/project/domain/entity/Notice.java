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
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID notice_key;

    @ManyToOne
    @JoinColumn(name = "admin_key", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private Integer hit = 0;

    @Column(nullable = false)
    private LocalDate date;

    @Builder
    public Notice(Admin admin, String title, String img, Integer hit){
        this.admin = admin;
        this.title = title;
        this.img = img;
        this.hit = hit != null ? hit : 0;
        this.date = LocalDate.now();
    }
}
