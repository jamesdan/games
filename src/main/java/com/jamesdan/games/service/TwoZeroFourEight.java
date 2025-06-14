package com.jamesdan.games.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TwoZeroFourEight {
    private final int[][] board = new int[4][4];

    private boolean randomize() {
        // Find all empty cells
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        Random random = new Random();
        int cellsToFill = 1 + random.nextInt(2); // 1 to 2

        for (int k = 0; k < cellsToFill && !emptyCells.isEmpty(); k++) {
            int idx = random.nextInt(emptyCells.size());
            int[] cell = emptyCells.remove(idx);
            board[cell[0]][cell[1]] = random.nextBoolean() ? 2 : 4;
        }

        printBoard();
        if(emptyCells.isEmpty()) {
            System.out.println("GAME OVER! No more empty cells to fill.");
            return true; // Game over
        }

        return false; // Game continues
    }



    private void printBoard() {
        System.out.println("Current board:");
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%4d", cell);
            }
            System.out.println();
        }
    }

    public void startGame() throws IOException {
        // Logic to initialize and start the 2048 game
        System.out.println("Hello from TwoZeroFourEight service!");

        System.out.println("Starting the 2048 game...");
        // Additional game setup code would go here
        randomize();
        printBoard();

        System.out.println("Use 'w' (up), 'a' (left), 's' (down), 'd' (right). Type 'q' to quit.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.isEmpty()) continue;
                char ch = Character.toLowerCase(line.charAt(0));
                switch (ch) {
                    case 'w' -> { if (moveUp()) return; }
                    case 'a' -> moveLeft();
                    case 's' -> moveDown();
                    case 'd' -> moveRight();
                    case 'q' -> {
                        System.out.println("Quitting game.");
                        return;
                    }
                    default -> System.out.println("Invalid input. Use 'w', 'a', 's', 'd', or 'q'.");
                }
            }
        }
    }

    private boolean moveUp() {
        // Logic to move tiles up
        System.out.println("Moving tiles up...");
        // Implement the logic for moving tiles up
        return randomize();
    }

    private void moveDown() {
        // Logic to move tiles down
        System.out.println("Moving tiles down...");
        // Implement the logic for moving tiles down
        printBoard();
    }

    private void moveLeft() {
        // Logic to move tiles left
        System.out.println("Moving tiles left...");
        // Implement the logic for moving tiles left
        printBoard();
    }

    private void moveRight() {
        // Logic to move tiles right
        System.out.println("Moving tiles right...");
        // Implement the logic for moving tiles right
        printBoard();
    }
}
