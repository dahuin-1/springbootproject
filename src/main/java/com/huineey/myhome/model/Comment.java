package com.huineey.myhome.model;


import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Comment {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String writer;

    @ManyToOne //게시글 입장에서는 매니 투 원
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(writer, comment.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, writer);
    }

}
