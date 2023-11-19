package com.anonymousboard.entity;


import com.anonymousboard.dto.request.CreateBoardRequestDto;
import com.anonymousboard.dto.request.UpdateBoardRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    public void setUsername(String username) {
        this.username = username;
    }

    public Board(CreateBoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}

