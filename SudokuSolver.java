/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sukodusolver;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author sthandisobrighton
 */
public class SudokuSolver {
    private static final int SIZE = 9;
    
   
    public boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) { 
                    for (int num = 1; num <= SIZE; num++) { 
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; 
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0; 
                        }
                    }
                    return false; // 
                }
            }
        }
        return true;
    }
    
    
    private boolean isValid(int[][] board, int row, int col, int num) {
       
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}

