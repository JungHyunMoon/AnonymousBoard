package com.anonymousboard.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "board")
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "CreateAt", nullable = false)
    @CreationTimestamp
    private Date CreateAt;
}

