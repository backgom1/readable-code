package cleancode.minesweeper.tobe.cell;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.LandMineCell;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    @DisplayName("지뢰 셀인지 판단하는 테스트")
    void isLandMine() {
        //given
        Cell cell = new LandMineCell();

        //when
        boolean isLandMine = cell.isLandMine();

        //then
        Assertions.assertThat(isLandMine).isTrue();
    }

    @Test
    @DisplayName("지뢰 수 여부 테스트")
    void hasLandMineCount() {
        //given
        Cell cell = new LandMineCell();

        //when
        boolean isLandMine = cell.hasLandMineCount();

        //then
        Assertions.assertThat(isLandMine).isFalse();
    }


}