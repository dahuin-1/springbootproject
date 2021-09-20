package com.huineey.myhome.controller;

import com.huineey.myhome.model.Board;
import com.huineey.myhome.model.Comment;
import com.huineey.myhome.repository.BoardRepository;
import com.huineey.myhome.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comments")
    List<Comment> all(@RequestParam(required = false, defaultValue = "") String content) {
        if (StringUtils.isEmpty(content)) {
            return commentRepository.findAll();
        }
        return commentRepository.findCommentByContent(content);
    }

    @PostMapping("/comments/{id}")
    Comment CreateComment(@RequestParam String comment, @PathVariable Long id, Authentication authentication) {
        //validation
        Board board = boardRepository.findOneById(id);
        String username = authentication.getName();
        Comment newComment = new Comment();
        newComment.setBoard(board);
        newComment.setContent(comment);
        newComment.setWriter(username);
        return commentRepository.save(newComment);
    }
}
