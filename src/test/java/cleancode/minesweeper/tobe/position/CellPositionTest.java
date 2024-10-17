package cleancode.minesweeper.tobe.position;

import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 움직임에 대한 이동은 지뢰 찾기의 중요한 로직이기 때문에 테스트를 진행하게 되었습니다.
 */
class CellPositionTest {

    @Test
    @DisplayName("셀 최초 위치 설정 - 정상 케이스")
    void successCellPosition() {
        //given
        CellPosition cellPosition = CellPosition.of(1, 1);

        //when,then
        assertThat(cellPosition.getRowIndex()).isEqualTo(1);
        assertThat(cellPosition.getColIndex()).isEqualTo(1);
    }

    @Test
    @DisplayName("셀 최초 위치 설정 row - 실패 케이스 (올바르지 않은 좌표입니다.)")
    void failRowCellPosition() {
        //given,when,then
        assertThatThrownBy(() -> {
            CellPosition.of(-1, 1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

    @Test
    @DisplayName("셀 최초 위치 설정 col - 실패 케이스 (올바르지 않은 좌표입니다.)")
    void failColCellPosition() {
        //given,when,then
        assertThatThrownBy(() -> {
            CellPosition.of(1, -1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }



}