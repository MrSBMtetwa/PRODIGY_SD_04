/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sukodusolver;

/**
 *
 * @author sthandisobrighton
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SudokuGUI extends Application {
    private static final int SIZE = 9;
    private TextField[][] grid = new TextField[SIZE][SIZE];
    private SudokuSolver solver = new SudokuSolver();
    
    @Override
    public void start(Stage primaryStage) {
      
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new TextField();
                grid[row][col].setPrefWidth(40);
                grid[row][col].setFont(Font.font(18));
                grid[row][col].setStyle("-fx-alignment: center;");
                gridPane.add(grid[row][col], col, row);
            }
        }

     
        Button solveButton = new Button("Solve");
        solveButton.setFont(Font.font(18));
        solveButton.setOnAction(e -> solvePuzzle());

        gridPane.add(solveButton, 0, SIZE, SIZE, 1);

    
        Scene scene = new Scene(gridPane, 400, 450);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  
    private void solvePuzzle() {
        int[][] board = new int[SIZE][SIZE];
        
        
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String value = grid[row][col].getText();
                if (value.isEmpty()) {
                    board[row][col] = 0; 
                } else {
                    try {
                        board[row][col] = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        board[row][col] = 0; 
                    }
                }
            }
        }

        Sudoku sudoku = new Sudoku(board);

        
        if (solver.solve(sudoku.getBoard())) {
            
            updateGrid(sudoku.getBoard());
        } else {
            System.out.println("No solution found!");
        }
    }


    private void updateGrid(int[][] solvedBoard) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col].setText(String.valueOf(solvedBoard[row][col]));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

