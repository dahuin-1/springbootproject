package com.huineey.myhome.controller;

import com.huineey.myhome.model.Board;
import com.huineey.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//board를 웹에서 클릭해서 보드 컨투롤러를 호출하게 되면, 데이터 값이 넘어간다. 여기서는 21번째 라인
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired //디펜던시 인젝션이 일어나고 서버기동될때 인스턴스가 넘어간다.
    private BoardRepository boardRepository; //보드 레파지토리를 이용해서 값을 넘긴다.

    @GetMapping("/list")
    public String list(Model model) { //데이터값을 추가하고 싶을 때 파라미터로 모델 넘김
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards); //키값에 boards를 줍니다.
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";
    }

    @PostMapping("/form")
    public String gettingSubmit(@ModelAttribute Board board) {
        boardRepository.save(board);
        /*
        ㅇㅏ이디의 키값이 있으면 업데이트
        없으면 인서트
         */
        return "redirect:/board/list"; //리스트로 리다이렉트가 되면, 리스트에서 다시 한번 조회가 되면서 화면이 이동
    }



}
