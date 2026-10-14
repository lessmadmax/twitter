package com.apiece.twitter.comment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 댓글을 ID 내림차순으로 조회
    List<Comment> findByPostIdOrderByIdDesc(Long postId);

    // 댓글 ID와 게시글 ID가 모두 일치하는 댓글 조회
    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);

    @EntityGraph(attributePaths = "post", type = EntityGraph.EntityGraphType.LOAD)
    List<Comment> findAll();
}