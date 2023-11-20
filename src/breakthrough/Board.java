package breakthrough;

import java.awt.Color;
import java.awt.Point;

/**
 * The {@code Board} class represents the game board for the Breakthrough game.
 * It includes methods to initialize the board, get information about specific fields,
 * check if the game is over, and perform pawn movements according to the game rules.
 * Each field on the board contains information about its color and the pawn occupying it.
 *
 * @author v5inla
 */
public class Board {

    private Field[][] board; // 2D array to represent the game board
    private final int boardSize; // Size of the game board
    private Color currentPlayer; // Color of the current player
    private boolean over; // Indicates whether the game is over

    /**
     * Constructs a new {@code Board} with the specified board size.
     *
     * @param boardSize The size of the game board.
     */
    public Board(int boardSize) {
        this.currentPlayer = Color.WHITE; // Set initial player to white
        this.boardSize = boardSize;
        this.over = false;
        board = new Field[this.boardSize][this.boardSize];

        // Initialize the game board and place pawns in their starting positions
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
                if (i == 0) {
                    board[i][j].setPawn(new LazyPawn(Color.BLACK));
                } else if (i == this.boardSize - 1) {
                    board[i][j].setPawn(new LazyPawn(Color.WHITE));
                }
                // Set the color of each field based on its position
                if (i + j % 2 == 0) {
                    board[i][j].setColor(Color.BLACK);
                } else {
                    board[i][j].setColor(Color.WHITE);
                }
            }
        }
    }

    /**
     * Checks if the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise.
     */
    public boolean isOver() {
        return this.over;
    }

    /**
     * Gets the field at the specified coordinates.
     *
     * @param x The x-coordinate of the field.
     * @param y The y-coordinate of the field.
     * @return The {@code Field} at the specified coordinates.
     */
    public Field get(int x, int y) {
        return board[x][y];
    }

    /**
     * Gets the field at the specified {@code Point}.
     *
     * @param point The {@code Point} representing the coordinates.
     * @return The {@code Field} at the specified coordinates.
     */
    public Field get(Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        return get(x, y);
    }

    /**
     * Gets the size of the game board.
     *
     * @return The size of the game board.
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Gets the color of the current player.
     *
     * @return The color of the current player.
     */
    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the color of the current player.
     *
     * @param currentPlayer The color of the current player.
     */
    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Moves a pawn from the source to the destination if the move is valid. Changes gameover state if the right condition is hit.
     *
     * @param src The source coordinates of the pawn.
     * @param dst The destination coordinates for the pawn.
     * @return {@code true} if the move is successful, {@code false} otherwise.
     */
    public Boolean movePiece(Point src, Point dst) {
        // Retrieve the source and destination fields
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

                    // Check if the move results in a win
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

                        // Check if the move results in a win
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
