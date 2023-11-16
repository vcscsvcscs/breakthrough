package breakthrough;

import java.awt.Color;

/**
 * The {@code LazyPawn} class represents a pawn in the Breakthrough game with a specified color.
 * Pawns are game pieces that can be placed on the game board fields.
 *
 * @author v5inla
 */
public class LazyPawn {

    /**
     * The color of the pawn.
     */
    private Color color;

    /**
     * Constructs a new {@code LazyPawn} with the specified color.
     *
     * @param color The color of the pawn.
     */
    public LazyPawn(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the pawn.
     *
     * @return The color of the pawn.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the pawn.
     *
     * @param color The color to set for the pawn.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
