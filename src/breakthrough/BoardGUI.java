package breakthrough;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardGUI {
    private Board board;
    private JPanel boardPanel;

    private Point sourceCoordinates; // To store the source coordinates of the drag

    public BoardGUI(int boardSize) {
        boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        board = new Board(boardSize);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                FieldPanel fieldPanel = new FieldPanel(new Point(i, j));
                fieldPanel.addMouseListener(new PieceClickListener());
                boardPanel.add(fieldPanel);
            }
        }
    }

    private class FieldPanel extends JPanel {
        private Point coordinates;

        public FieldPanel(Point coordinates) {
            this.coordinates = coordinates;
            setBackground(board.get(coordinates.x, coordinates.y).getColor());
            setLayout(new BorderLayout());

            Field field = board.get(coordinates.x, coordinates.y);
            if (field.getPawn() != null) {
                JLabel pieceLabel = new JLabel(field.getPawn().getColor() == Color.WHITE ? "W" : "B");
                pieceLabel.setHorizontalAlignment(JLabel.CENTER);
                add(pieceLabel, BorderLayout.CENTER);
            }
        }
    }

    private class PieceClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            sourceCoordinates = ((FieldPanel) e.getSource()).coordinates;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Point destinationCoordinates = ((FieldPanel) e.getSource()).coordinates;
            if (sourceCoordinates != null && destinationCoordinates != null) {
                board.movePiece(sourceCoordinates, destinationCoordinates);
                // Update the GUI and check for game over, etc.
                // You might need to add code here to update the GUI components based on the game state.
            }
            sourceCoordinates = null; // Reset the source coordinates after the move
            refreshBoard(); // Refresh the board to update the visual representation
        }
    }

    private void refreshBoard() {
        for (Component component : boardPanel.getComponents()) {
            if (component instanceof FieldPanel) {
                FieldPanel fieldPanel = (FieldPanel) component;
                Point coordinates = fieldPanel.coordinates;
                fieldPanel.removeAll();

                Field field = board.get(coordinates.x, coordinates.y);
                if (field.getPawn() != null) {
                    JLabel pieceLabel = new JLabel(field.getPawn().getColor() == Color.WHITE ? "W" : "B");
                    pieceLabel.setHorizontalAlignment(JLabel.CENTER);
                    fieldPanel.add(pieceLabel, BorderLayout.CENTER);
                }

                fieldPanel.revalidate();
                fieldPanel.repaint();
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}
