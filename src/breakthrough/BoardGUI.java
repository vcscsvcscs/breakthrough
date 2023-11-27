package breakthrough;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardGUI {

    private Board board;
    private JPanel boardPanel;
    private FieldPanel selectedField; // Added selectedField variable

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

        private void showWinnerPopup(String winner) {
            String message = winner + " player wins!";
            JOptionPane.showMessageDialog(boardPanel, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        public FieldPanel(Point coordinates) {
            this.coordinates = coordinates;
            setBackground(board.get(coordinates.x, coordinates.y).getColor());
            setLayout(new BorderLayout());

            Field field = board.get(coordinates.x, coordinates.y);
            if (field.getPawn() != null) {
                JLabel pieceLabel = new JLabel(field.getPawn().getColor() == Color.WHITE ? "W" : "B");
                pieceLabel.setForeground(Color.RED);
                pieceLabel.setHorizontalAlignment(JLabel.CENTER);
                add(pieceLabel, BorderLayout.CENTER);
            }

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleFieldClick();
                }
            });
        }

        private void handleFieldClick() {
            if (sourceCoordinates != null && !sourceCoordinates.equals(coordinates)) {
                // Field is already selected, perform the move logic
                Point clickCoordinates = coordinates;

                selectedField.setBorder(null); // Remove border from the previously selected field
                selectedField = null; // Reset the selected field reference
                if (board.movePiece(sourceCoordinates, clickCoordinates)) {
                    board.setCurrentPlayer(board.getCurrentPlayer() == Color.WHITE ? Color.BLACK : Color.WHITE);
                    if (board.isOver()) {
                        showWinnerPopup(board.getCurrentPlayer() == Color.WHITE ? "BLACK" : "WHITE");
                        int boardSize = board.getBoardSize();
                        boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
                        board = new Board(boardSize);
                        initializeBoard();
                    }
                    refreshBoard(); // Refresh the board to update the visual representation
                }
                sourceCoordinates = null; // Reset the source coordinates after the move
            } else {
                if (selectedField != null) {
                    // Remove border from the previously selected field
                    selectedField.setBorder(null);
                }
                selectedField = this; // Store the selected field reference
                setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // Add border to indicate selection
                sourceCoordinates = coordinates; // Set the source coordinates for the move
            }
        }
    }

    private class PieceClickListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            ((FieldPanel) e.getSource()).handleFieldClick();
        }
    }

    private void refreshBoard() {
        for (Component component : boardPanel.getComponents()) {
            if (component instanceof FieldPanel) {
                FieldPanel fieldPanel = (FieldPanel) component;
                Point coordinates = fieldPanel.coordinates;
                Field field = board.get(coordinates.x, coordinates.y);

                // Update background color
                fieldPanel.setBackground(field.getColor());

                // Update pawn label
                JLabel pieceLabel = findPieceLabel(fieldPanel);
                if (field.getPawn() != null) {
                    pieceLabel.setText(field.getPawn().getColor() == Color.WHITE ? "W" : "B");
                    pieceLabel.setForeground(Color.RED);
                    pieceLabel.setHorizontalAlignment(JLabel.CENTER);
                    fieldPanel.add(pieceLabel, BorderLayout.CENTER);
                } else {
                    pieceLabel.setText(""); // Clear label if no pawn on the field
                }
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private JLabel findPieceLabel(FieldPanel fieldPanel) {
        for (Component component : fieldPanel.getComponents()) {
            if (component instanceof JLabel) {
                return (JLabel) component;
            }
        }
        return new JLabel(); // Return a default label if not found
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}
