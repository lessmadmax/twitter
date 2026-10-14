package com.apiece.twitter.post;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "posts")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public void updateContent(String content) {
        this.content = content;
    }
    public void increaseCommentCount() {
        this.commentCount++;
    }
    public void decreaseCommentCount() {
        this.commentCount--;
    }
}
