package com.huineey.myhome.controller;

import com.huineey.myhome.model.Board;
import com.huineey.myhome.repository.BoardRepository;
import com.huineey.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//board를 웹에서 클릭해서 보드 컨투롤러를 호출하게 되면, 데이터 값이 넘어간다. 여기서는 21번째 라인
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired //디펜던시 인젝션을 이용하기 위해서! 서버기동될때 인스턴스가 넘어간다.
    private BoardRepository boardRepository; //보드 레파지토리를 이용해서 값을 넘긴다.

    @Autowired //스프링 기동될때 인스턴스가 담김
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable) { //데이터값을 추가하고 싶을 때 파라미터로 모델 넘김
        Page<Board> boards = boardRepository.findAll(pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards); //키값에 boards를 줍니다.
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        //(required = false) 필수인지 아닌지, 새글을 작성할때는 파라미터가 필요 없다
        if(id == null){
            model.addAttribute("board", new Board());
            //아이디가 null이면 새 보드 클래스를 생성해서 form에 넘김
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            //boardRepository 에서 아이디로 값을 찾아서 넘긴다. 아이디가 없으면 null을 넘긴다.
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String gettingSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/form";
        }
        boardRepository.save(board);
        /*
        ㅇㅏ이디의 키값이 있으면 업데이트
        없으면 인서트
         */
        return "redirect:/board/list"; //리스트로 리다이렉트가 되면, 리스트에서 다시 한번 조회가 되면서 화면이 이동
    }



}
