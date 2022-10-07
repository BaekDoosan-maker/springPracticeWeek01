package com.sparta.board2.service;
import com.sparta.board2.domain.Board;
import com.sparta.board2.dto.BoardCheckRequestDto;
import com.sparta.board2.dto.BoardRequestDto;
import com.sparta.board2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor /* final 이 붙거나 @Not Null 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션*/
public class BoardService {
    private  final BoardRepository boardRepository;
    /* 스프링은 @Transactional 어노테이션을 이용한 선언적 트랜잭션 처리를 지원한다. 클래스나 메서드에 @Transactional을 붙여줄 경우해당 범위 내 메서드가 트랜잭션이 되도록 보장해준다. */

    /* 전체 게시글 조회*/
    @Transactional
    public List<Board> showAllBoard() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    /* 특정 게시글 조회 */
    @Transactional
    public  Board showOneBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        return board;
    }

    /* 특정 게시글 삭제 */
    @Transactional
    public Long deleteById(Long id) {
        boardRepository.deleteById(id);
        return id;
    }

    /* 특정 게시물 수정 */
    @Transactional
    public Long updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board =  boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        board.update(boardRequestDto);
        return board.getId();
    }

    /* 특정 게시물 등록 */
    @Transactional
    public Board saveBoard(BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
        return boardRepository.save(board);
    }

    /**
     * 비밀번호 확인
     */
     /* 스프링은 @Transactional 어노테이션을 이용한 선언적 트랜잭션 처리를 지원한다. 클래스나 메서드에 @Transactional을 붙여줄 경우
    해당 범위 내 메서드가 트랜잭션이 되도록 보장해준다. */
    @Transactional
    public boolean passwordCheck(Long id, BoardCheckRequestDto boardCheckRequestDto)  {
        Board board =  boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지않는 글입니다.")
        );
        return board.getPassword().equals(boardCheckRequestDto.getPassword());
    }
}
