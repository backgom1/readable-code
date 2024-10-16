package cleancode.minesweeper.tobe;


import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameBoardTest {

    @Test
    @DisplayName("게임 맵 생성 사이즈 테스트- Advanced")
    void advanced() {

        //given
        GameBoard gameBoard = new GameBoard(new Advanced());

        //when,then
        assertThat(gameBoard.getRowSize()).isEqualTo(20);
        assertThat(gameBoard.getColSize()).isEqualTo(24);
    }


    @Test
    @DisplayName("성공 케이스 - 해당 셀 위치에 대한 유효성 검증 테스트를 진행한다.")
    void isInvalidCellPosition() {
        //given
        GameBoard gameBoard = new GameBoard(new Advanced());
        CellPosition cellPosition = CellPosition.of(19, 10);

        //when
        boolean invalidCellPosition = gameBoard.isInvalidCellPosition(cellPosition);

        //then
        assertThat(invalidCellPosition).isFalse();

    }
}