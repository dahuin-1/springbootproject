package com.huineey.myhome.controller;

import com.huineey.myhome.model.Board;
import com.huineey.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//보드 컨투롤러를 호출하게 되면, 데이터 값이 넘어간다.
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired //디펜던시 인젝션이 일어나고 서버기동될때 인스턴스가 넘어간다.
    private BoardRepository boardRepository; //보드 레파지토리를 이용해서 값을 넘긴다.

    @GetMapping("/list")
    public String list(Model model) { //모델이 넘어간다.

        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards); //보즈라는 키값에 보즈를 줍니다.
        return "board/list";
    }

}
