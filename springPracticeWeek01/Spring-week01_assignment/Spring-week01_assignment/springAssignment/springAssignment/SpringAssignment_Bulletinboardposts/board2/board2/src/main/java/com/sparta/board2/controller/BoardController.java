package com.sparta.board2.controller;
import com.sparta.board2.domain.Board;
import com.sparta.board2.dto.BoardCheckRequestDto;
import com.sparta.board2.dto.BoardRequestDto;
import com.sparta.board2.repository.BoardRepository;
import com.sparta.board2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor /* final 이 붙거나 @Not Null 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션*/
@RestController /* @Controller에 @ResponseBody가 추가된 형태. Json 형태로 객체 데이터를 반환하는 용도가 주 용도*/
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    /**
     *  전체 게시글 조회 매핑
     */
    @GetMapping("/api/boards")
    public List<Board> getAllBoard() {
        return boardService.showAllBoard();
    }

    /**
     * 특정 게시글 조회 매핑
     */
    @GetMapping("/api/boards/{id}")
    public Board getOneBoard(@PathVariable Long id) { /* @PathVariable : 스프링에서 값을 넘겨 받을 때 사용하는 어노테이션 */
        return boardService.showOneBoard(id);
    }

    /**
     * 게시글 등록
     */
    @PostMapping("/api/boards") /* //@RequestBody: http 통신을 할 때 post의 body에 data를 넣어서 보내겠다라는 의미로, RequestBody에 SearchParam 값들을 매칭시켜서 달라는 의미이다. */
    public Board createBoard(@RequestBody BoardRequestDto boardRequestDto) { /* @RequestBody : 파라미터에 http 요청의 본문(body)이 그대로 전달되는 어노테이션 */
        return boardService.saveBoard(boardRequestDto);
    }

    /**
     * 게시글 비밀번호 확인
     */
    @PostMapping("/api/boards/{id}") /* @PathVariable : 스프링에서 값을 넘겨 받을 때 사용하는 어노테이션 */
    public boolean passwordCheck(@PathVariable Long id, @RequestBody BoardCheckRequestDto boardCheckRequestDto) { /* @RequestBody : 파라미터에 http 요청의 본문(body)이 그대로 전달되는 어노테이션 */
        return boardService.passwordCheck(id, boardCheckRequestDto);
    }
    /*데이터 하나 조회하기(findById)-이 데이터 하나를 유일하게 구분할 수 있는 PK로 ID의 의미를 생각하면 된다.
     @PathVariable 뒤에오는 변수가 주소{id}로 넘어가서 대입됨
     => PutMapping의 대상을 유일한 하나로 지정해줌 */

    /**
     * 특정 게시글 수정
     */

    @PutMapping("/api/boards/{id}") /* @PathVariable : 스프링에서 값을 넘겨 받을 때 사용하는 어노테이션 */
    public Long update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) { /* @RequestBody : 파라미터에 http 요청의 본문(body)이 그대로 전달되는 어노테이션 */
        return boardService.updateBoard(id, boardRequestDto);
    }

    /**
     * 특정 게시글 삭제
     */
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) { /* @RequestBody : 파라미터에 http 요청의 본문(body)이 그대로 전달되는 어노테이션 */
        return boardService.deleteById(id);
    }
}

