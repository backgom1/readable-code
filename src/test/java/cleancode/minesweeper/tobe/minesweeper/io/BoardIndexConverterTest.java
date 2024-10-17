package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 해당 테스트 코드를 작성하게된 이유는
 * 입력 값에 대해 코드의 동작이 이루어 지기때문에 올바른 입력에 대해 테스트에 대해 해야한다 판단해서 작성했습니다.
 *  또한 정확한 예외처리가 작동하는지에 대한 테스트를 진행했습니다.
 */
class BoardIndexConverterTest {

    @Test
    @DisplayName("Row 좌표에 대해 정상적으로 놓는 테스트")
    void getSelectedRowIndexSuccess() {
        //given
        String target = "a1";
        BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

        //when
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(target);

        //then
        assertThat(selectedRowIndex).isEqualTo(0);
    }

    @Test
    @DisplayName("Row 좌표에 대해 인덱스가 넘어갔을 경우에 대한 예외 테스트")
    void getSelectedRowIndexFail() {
        //given
        String target = "a0";
        BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

        //when,then
        assertThatThrownBy(() -> {
            boardIndexConverter.getSelectedRowIndex(target);
        }).isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");

    }

    @Test
    @DisplayName("Col 좌표에 대해 정상적으로 놓는 테스트")
    void getSelectedColIndexSuccess() {
        //given
        String target = "a1";
        BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

        //when
        int selectedRowIndex = boardIndexConverter.getSelectedColIndex(target);

        //then
        assertThat(selectedRowIndex).isEqualTo(0);
    }

    @Test
    @DisplayName("Col 좌표에 대해 인덱스가 설정 했을 경우에 대한 예외 테스트")
    void getSelectedColIndexFail() {
        //given
        String target = "21";
        BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

        //when,then
        assertThatThrownBy(() -> {
            boardIndexConverter.getSelectedColIndex(target);
        }).isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");

    }




}