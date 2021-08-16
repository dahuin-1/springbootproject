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

  //  @CrossOrigin(origins = "*", allowedHeaders = "*")
 /*   @GetMapping("/comments/{id}")
    List<Comment> getBoardComments(@PathVariable Long id) {
        System.out.println("hello");
        Board post = boardRepository.findById(id).get();
        return commentRepository.findCommentsByBoard(post);
    }*/

    @GetMapping("/comments")
    List<Comment> all (@RequestParam(required = false, defaultValue = "") String content) {
            if(StringUtils.isEmpty(content)) {
                return commentRepository.findAll();
            }
            return commentRepository.findCommentByContent(content);
        }
    }


  /*  @GetMapping("/boards/{id}")
    public String list(Model model,@PathVariable Long id, @PathVariable Long commentID) {
        //데이터값을 추가하고 싶을 때 파라미터로 모델 넘김
        // Page<Board> boards = boardRepository.findAll(pageable);
        Board post = boardRepository.findById(id).get();
        Comment newComment = commentRepository.findById(commentID).get();
        model.addAttribute("post", post);
        return "/boards/"+ id;
    }*/


   /* @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/boards/{id}/comment")
    public Comment createComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentItem = commentRepository.findById(id);
        comment.setContent(commentItem.get());
        commentRepository.save(comment);
        return comment;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/boards/{id}/comment/{commentID}")
    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentID, @RequestBody Comment comment) {
        Optional<Board> boardItem = boardRepository.findById(id);
        comment.setBoard(boardItem.get());
        Comment newComment = commentRepository.findById(commentID).get();
        newComment.setContent(comment.getContent());
        newComment.setWriter(comment.getWriter());
        return newComment;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/boards/{id}/comment/{commentID}")
    public String createComment(@PathVariable Long id, @PathVariable Long commentID) {
        commentRepository.deleteById(commentID);
        return "Comment delete success";
    }*/


