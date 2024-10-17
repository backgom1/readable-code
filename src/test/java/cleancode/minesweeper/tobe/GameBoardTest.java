package cleancode.minesweeper.tobe;


import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPositions;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.VeryBeginner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 해당 클래스의 테스트는 게임보드의 동작에 대한 기능을 담당하는 중요한 비지니스 로직으로 판단하여 각각의 동작이
 * 올바르게 진행이 되는지에 대해서 생각을 했고 그로인해 테스트 코드를 작성하기로 했습니다.
 */
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
    @DisplayName("게임을 초기화를 진행했을때 게임이 동작이 진행중인지 확인 하는 테스트")
    void initializeGame() {
        //given
        GameBoard gameBoard = new GameBoard(new Advanced());
        gameBoard.initializeGame();

        //when
        boolean isInProgress = gameBoard.isInProgress();

        //then
        assertThat(isInProgress).isTrue();
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

    @Test
    @DisplayName("셀을 열었을 경우 - 깃발을 꽂는 경우에 대한 테스트")
    void openAt(){
        GameBoard gameBoard = new GameBoard(new VeryBeginner());
        CellPosition cellPosition = CellPosition.of(3, 3);
        gameBoard.flagAt(cellPosition);
        CellSnapshot snapshot = gameBoard.getSnapshot(cellPosition);
        Assertions.assertThat(snapshot.isSameStatus(CellSnapshotStatus.FLAG)).isTrue();
    }

}