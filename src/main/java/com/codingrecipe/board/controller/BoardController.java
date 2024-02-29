package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor //final이 붙은 클래스만 생성자를 만들어줌
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {//게시글에서 작성한 데이터를 컨트롤러에 가져오기
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list"; //화면을 띄우는게 아니라 get 매핑을 호출(아래)
    }

    @GetMapping("/list")
    public String findAll(Model model){//DB에 있는 데이터를 화면으로 가져오기
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "list";
    }

    // /10, /1
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        //조회수 처리
        boardService.updateHits(id); //예외처리 하면 좋음
        // 상세내용 가져옴
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO); //board : alias 와 같음
        if (boardDTO.getFileAttached() == 1)
        {
            BoardFileDTO boardFileDTO = (BoardFileDTO) boardService.findFile(id);
            model.addAttribute("boardFile",boardFileDTO);
        }
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "update";//데이터를 가지고 update.html에 가져감 -> 수정함
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);//수정후(업데이트 후) 모델에 담고 detail.html에 가져감
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board",dto);
        return "detail";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }
}
