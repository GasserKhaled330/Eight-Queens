package com.eightqueens;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EightQueen extends Application {
    public static final int SIZE = 8;// the size of the EightQueens board
    // queens are placed at (i, queens[i])
    // −1 indicates that no queen is currently placed in the ith row
    // Initially, place a queen at (0, 0) in the 0th row
    private final int[] queens = {-1,-1,-1,-1,-1,-1,-1,-1};
    @Override
    public void start(Stage stage) {
        search(); // search for a solution

        GridPane board = new GridPane();
        board.setAlignment(Pos.CENTER);
        Label[][] labels = new Label[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board.add(labels[row][col] = new Label(),col,row);
                labels[row][col].setStyle("-fx-border-color: #acacac");
                labels[row][col].setPrefSize(55,55);
            }
        }
        try{
            FileInputStream imagePath = new FileInputStream("src/main/resources/com/eightqueens/queen.jpg");
            Image queenImage = new Image(imagePath,50,50,false,false);
            for (int row = 0; row < SIZE; row++) {
                labels[row][queens[row]].setGraphic(new ImageView(queenImage));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error! URL for queen image is wrong");
            System.out.println(ex.getMessage());
        }

        Scene scene = new Scene(board,55 * SIZE, 55 * SIZE);
        stage.setTitle("Eight Queens Game!");
        stage.setScene(scene);
        stage.show();
    }

    private void search() {
        int row = 0;
        while(row >= 0 && row < SIZE) {
            // find position to place a queen in the col^th row
            int pos = findValidPosition(row);
            if(pos < 0) {
                queens[row] = -1;
                row--; // backtrack to the previous row
            } else {
                queens[row] = pos;
                row++;
            }
        }
    }

    private int findValidPosition(int row) {
        int start = queens[row] + 1;

        for (int col = start; col < SIZE; col++) {
            if(isValidPosition(row,col)) {
                return col; // (col,row) is the place to put the queen
            }
        }
        return -1;
    }

    private boolean isValidPosition(int row, int col) {
        for (int i = 1; i <= row; i++) {
            if(queens[row - i] == col // check column
            || queens[row - i] == col - i // check up left diagonal
            || queens[row - i] == col + i){ // check up right diagonal
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        launch();
    }
}