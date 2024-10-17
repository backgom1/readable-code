package cleancode.minesweeper.tobe.minesweeper.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ConsoleOutputHandlerTest {
    private OutputHandler outputHandler;
    ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outputHandler = new ConsoleOutputHandler();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("지뢰를 모두 찾았을때에 대한 메세지 테스트")
    void showGameWinningComment() {
        //given,when
        outputHandler.showGameWinningComment();

        //then
        assertThat(outContent.toString().trim()).isEqualTo("지뢰를 모두 찾았습니다. GAME CLEAR!");
        System.setOut(System.out);
    }

    @Test
    @DisplayName("지뢰를 밟았을때 메세징 처리")
    void showGameLosingComment() {
        //given,when
        outputHandler.showGameLosingComment();

        //then
        assertThat(outContent.toString().trim()).isEqualTo("지뢰를 밟았습니다. GAME OVER!");
        System.setOut(System.out);
    }


    @Test
    @DisplayName("좌표를 선택하기전 나오는 메세지 테스트")
    void showCommentForSelectingCell() {
        //given,when
        outputHandler.showCommentForSelectingCell();

        //then
        assertThat(outContent.toString().trim()).isEqualTo("선택할 좌표를 입력하세요. (예: a1)");
        System.setOut(System.out);
    }


    @Test
    @DisplayName("사용자가 좌표 입력 후 셀에 대해 동작을 처리할때 나오는 메세지 테스트")
    void showCommentForUserAction() {
        //given,when
        outputHandler.showCommentForUserAction();

        //then
        assertThat(outContent.toString().trim()).isEqualTo("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        System.setOut(System.out);
    }
}