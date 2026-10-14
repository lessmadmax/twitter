package com.apiece.twitter.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    // 컨트롤러에서 유효성 검증
    @PostMapping
    public CommentResponse createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest request
    ) {
// 유효성 검증을 통과한 요청만 처리

        return commentService.createComment(postId, request);
    }

    @GetMapping
    public List<CommentResponse> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }
    @PutMapping("/{commentId}")
    public CommentResponse updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequest request
    ) {
        return commentService.updateComment(postId, commentId, request);
    }
    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(postId, commentId);
    }

    @GetMapping("/api/comments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }
}
