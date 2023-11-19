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
    private Color currentPlayer;
    private boolean over;

    public Board(int boardSize) {
        this.currentPlayer = Color.WHITE;
        this.boardSize = boardSize;
        this.over = false;
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
        return this.over;
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

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Boolean movePiece(Point src, Point dst) {
        Field destination = this.get(dst);
        Field source = this.get(src);

        // Check if the source pawn exists and belongs to the current player
        if (source.getPawn() != null && source.getPawn().getColor() == this.currentPlayer) {
            if ((dst.y + 1 == src.y && this.currentPlayer == Color.WHITE)
                    || (dst.y - 1 == src.y && this.currentPlayer == Color.BLACK)) {
                // Check if the destination is empty and the move is valid
                if (dst.x == src.x && destination.getPawn() == null) {
                    destination.setPawn(source.getPawn());
                    source.setPawn(null);

                    if ((dst.y == 0 && this.currentPlayer == Color.WHITE)
                            || (dst.y == this.boardSize - 1 && this.currentPlayer == Color.BLACK)) {
                        this.over = true;
                    }
 
                    return true;
                }

                // Check if the destination has an opponent's pawn that can be captured
                if (dst.x == src.x + 1 || dst.x == src.x - 1) {
                    if (destination.getPawn() != null && destination.getPawn().getColor() != this.currentPlayer) {
                        destination.setPawn(source.getPawn());
                        source.setPawn(null);

                        if ((dst.y == 0 && this.currentPlayer == Color.WHITE)
                                || (dst.y == this.boardSize - 1 && this.currentPlayer == Color.BLACK)) {
                            this.over = true;
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }
}
