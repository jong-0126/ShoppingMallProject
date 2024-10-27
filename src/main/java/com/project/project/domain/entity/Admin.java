package com.project.project.domain.entity;

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
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(unique = true)
    private UUID admin_key;

    @Column(nullable = false, unique = true)
    private String admin_id;

    @Column(nullable = false)
    private String admin_pwd;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean auth;

    @Column(nullable = false)
    private Boolean isSuperAdmin;


    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notice> notices = new ArrayList<>();

    @Builder
    public Admin(String admin_id, String admin_pwd, String name, Boolean auth, Boolean isSuperAdmin){

        this.admin_id = admin_id;
        this.admin_pwd = admin_pwd;
        this.name = name;
        this.auth = auth;
        this.isSuperAdmin = isSuperAdmin;
    }
}
