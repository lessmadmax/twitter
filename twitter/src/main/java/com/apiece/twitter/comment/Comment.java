package com.apiece.twitter.comment;

import com.apiece.twitter.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "comments")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    public void updateContent(String content) {
        this.content = content;
    }
    @Column(name = "post_id", nullable = false,
            insertable = false, updatable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
