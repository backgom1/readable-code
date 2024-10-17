package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellsTest {

    @Test
    @DisplayName("모든 셀이 열려있을때에 대한 테스트")
    void checkAllCells() {
        // given
        Cell[][] board = new Cell[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new EmptyCell();
                board[i][j].open();
            }
        }
        Cells cells = Cells.from(board);

        // when
        boolean areAllCellsChecked = cells.isAllChecked();

        // then
        assertThat(areAllCellsChecked).isTrue();
    }

}