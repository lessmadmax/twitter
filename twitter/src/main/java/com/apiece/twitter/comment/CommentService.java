package com.apiece.twitter.comment;

import com.apiece.twitter.post.Post;
import com.apiece.twitter.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        Comment comment = Comment.builder()
                .content(request.content())
                .post(post)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Comment newComment = commentRepository.save(comment);
        if (Objects.equals(postId, 20L)) {
            throw new RuntimeException("애플리케이션 오류 발생");
        }
        post.increaseCommentCount();
        return CommentResponse.from(newComment);
    }

    public void deleteComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));
        Post post = comment.getPost();
        post.decreaseCommentCount();
        commentRepository.delete(comment);
    }

    // 읽기 작업 - 읽기 전용 트랜잭션
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostIdOrderByIdDesc(postId);
        return comments.stream()
                .map(CommentResponse::from)
                .toList();
    }

    public CommentResponse updateComment(
            Long postId,
            Long commentId,
            CommentRequest request
    ) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() ->
                        new IllegalArgumentException("댓글을 찾을 수 없습니다"));

        comment.updateContent(request.content());

        return CommentResponse.from(comment);
    }


    public List<CommentResponse> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> {
                    Post post = comment.getPost();
                    log.info("댓글 ID: {}, 게시글 ID: {}, 게시글 내용: {}", comment.getId(), post.getId(), post.getContent());
                    return CommentResponse.from(comment);
                })
                .toList();
    }
}