package com.apiece.twitter.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

/*
public interface PostRepository {
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void deleteById(Long id);
    List<Post> findAllPaged(int page, int size);
}
*/
public interface PostRepository extends JpaRepository<Post, Long> {
    Slice<Post> findSlicesBy(Pageable pageable);
    Slice<Post> findSlicesByIdLessThan(Long lastId, Pageable pageable);
}
