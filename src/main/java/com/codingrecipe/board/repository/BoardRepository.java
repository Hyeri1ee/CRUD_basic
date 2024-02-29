package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    @Transactional
    public BoardDTO save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO); //mapper의 namespace, 넘길 객체
        return boardDTO;
    }

    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits",id);
    }


    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById",id);
    }

    public void update(BoardDTO boardDTO) {
        sql.update("Board.update",boardDTO);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    public void saveFile(BoardFileDTO boardFileDTO) {
        sql.insert("Board.saveFile",boardFileDTO);
    }

    public List<BoardFileDTO> findFile(Long id) {
        return sql.selectList("Board.findFile", id);
    }
}
