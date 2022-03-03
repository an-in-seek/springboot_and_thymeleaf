package com.web.seek.controller;

import com.web.seek.annotation.SocialUser;
import com.web.seek.domain.Board;
import com.web.seek.domain.User;
import com.web.seek.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    /**
     *
     * @param board
     * @return
     */
    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody Board board, @SocialUser User user) {
        board.setUser(user);
        Board savedBoard = boardService.save(board);
        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    /**
     *
     * @param id
     * @param board
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> putBoard(@PathVariable("id") Long id, @RequestBody Board board) {
        Board persistBoard = boardService.findBoardById(id);
        persistBoard.update(board);
        Board savedBoard = boardService.save(persistBoard);
        return new ResponseEntity<>(savedBoard, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
