/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tictactoe;

/**
 *
 * @author MWENDE 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private char currentPlayer;
    private JButton[][] buttons;
    private boolean isGameOver;
    private JLabel promptLabel;

    public TicTacToe() {
        currentPlayer = 'X';
        buttons = new JButton[3][3];
        isGameOver = false;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout());

        initializeButtons();
        promptLabel = new JLabel("Player " + currentPlayer + "'s turn");
        promptLabel.setHorizontalAlignment(JLabel.CENTER);
        promptLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(promptLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void initializeButtons() {
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                boardPanel.add(buttons[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void checkForWin(int row, int col) {
        // Check rows, columns, and diagonals
        if (buttons[row][0].getText().equals(buttons[row][1].getText())
                && buttons[row][1].getText().equals(buttons[row][2].getText())
                && !buttons[row][0].getText().isEmpty()) {
            highlightWinningCells(row, 0, row, 2);
            isGameOver = true;
        }

        if (buttons[0][col].getText().equals(buttons[1][col].getText())
                && buttons[1][col].getText().equals(buttons[2][col].getText())
                && !buttons[0][col].getText().isEmpty()) {
            highlightWinningCells(0, col, 2, col);
            isGameOver = true;
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()) {
            highlightWinningCells(0, 0, 2, 2);
            isGameOver = true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty()) {
            highlightWinningCells(0, 2, 2, 0);
            isGameOver = true;
        }

        if (isGameOver) {
            String winner = (currentPlayer == 'X') ? "Player X" : "Player O";
            promptLabel.setText(winner + " wins!");
            disableAllButtons();
        } else if (isBoardFull()) {
            promptLabel.setText("It's a draw!");
        }
    }

    private void highlightWinningCells(int startRow, int startCol, int endRow, int endCol) {
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                buttons[i][j].setBackground(Color.GREEN);
            }
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (!isGameOver && buttons[row][col].getText().isEmpty()) {
                buttons[row][col].setText(Character.toString(currentPlayer));
                checkForWin(row, col);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                promptLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
