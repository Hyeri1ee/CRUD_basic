package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;
    private int fileAttached;//파일 첨부 여부 확인 필드
    private List<MultipartFile> boardFile;//파일 자체를 담기 위한 필드
}