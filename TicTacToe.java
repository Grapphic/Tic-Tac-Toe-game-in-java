import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private final JButton[][] buttons;
    private final int SIZE = 3;
    private boolean isPlayer1Turn = true;

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(SIZE, SIZE));
        
        buttons = new JButton[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            if (isPlayer1Turn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            isPlayer1Turn = !isPlayer1Turn;
        }
        if (checkForWinner()) {
            JOptionPane.showMessageDialog(this, (isPlayer1Turn ? "Player 2" : "Player 1") + " wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private boolean checkForWinner() {
        String[][] board = new String[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
        isPlayer1Turn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
