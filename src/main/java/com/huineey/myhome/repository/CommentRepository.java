package com.huineey.myhome.repository;

import com.huineey.myhome.model.Board;
import com.huineey.myhome.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByBoard(Board post);
    List<Comment> findCommentByContent(String content);
}
