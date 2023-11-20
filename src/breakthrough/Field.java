package breakthrough;

import java.awt.Color;

/**
 * The {@code Field} class represents a single field on the game board in the Breakthrough game.
 * Each field can have a color and may contain a pawn.
 *
 * @author v5inla
 */
public class Field {

    /**
     * The color of the field.
     */
    private Color color;

    /**
     * The pawn present on the field.
     */
    private LazyPawn pawn;

    /**
     * Constructs a new {@code Field} with no specified color and no pawn.
     */
    public Field() {
        color = null;
        pawn = null;
    }

    /**
     * Constructs a new {@code Field} with specified color and pawn.
     * 
     * @param color The color to set for the field.
     * @param pawn The pawn standing on the field.
     */
    public Field(Color color,LazyPawn pawn) {
        this.color = color;
        this.pawn = pawn;
    }

    /**
     * Gets the color of the field.
     *
     * @return The color of the field.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the field.
     *
     * @param color The color to set for the field.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the pawn present on the field.
     *
     * @return The pawn on the field.
     */
    public LazyPawn getPawn() {
        return pawn;
    }

    /**
     * Sets the pawn on the field.
     *
     * @param pawn The pawn to be placed on the field.
     */
    public void setPawn(LazyPawn pawn) {
        this.pawn = pawn;
    }
}
