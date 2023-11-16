package breakthrough;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author v5inla
 */
public class Board {

    private Field[][] board;
    private final int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
                if (i == 0) {
                    board[i][j].setPawn(new LazyPawn(Color.BLACK));
                } else if (i == this.boardSize - 1) {
                    board[i][j].setPawn(new LazyPawn(Color.WHITE));
                }
                if (i + j % 2 == 0) {
                    board[i][j].setColor(Color.BLACK);
                } else {
                    board[i][j].setColor(Color.WHITE);
                }
            }
        }
    }

    public boolean isOver() {
        // TODO
        return true;
    }

    public Field get(int x, int y) {
        return board[x][y];
    }

    public Field get(Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        return get(x, y);
    }

    public int getBoardSize() {
        return boardSize;
    }

}
