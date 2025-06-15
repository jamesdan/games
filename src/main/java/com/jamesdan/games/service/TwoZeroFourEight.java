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
        int cellsToFill = random.nextInt(2); // 0 to 2

        for (int k = 0; k < cellsToFill && !emptyCells.isEmpty(); k++) {
            int idx = random.nextInt(emptyCells.size());
            int[] cell = emptyCells.remove(idx);
            board[cell[0]][cell[1]] = random.nextBoolean() ? 2 : 4;
        }

        printBoard();
        if (emptyCells.isEmpty()) {
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

        System.out.println("Use 'w' (up), 'a' (left), 's' (down), 'd' (right). Type 'q' to quit.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.isEmpty()) continue;
                char ch = Character.toLowerCase(line.charAt(0));
                switch (ch) {
                    case 'w' -> {
                        if (moveUp()) return;
                    }
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
        System.out.println("Moving tiles up...");
        for (int x = 0; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                if (board[y][x] != 0) {
                    int targetY = y;
                    while (targetY > 0 && board[targetY - 1][x] == 0) {
                        board[targetY - 1][x] = board[targetY][x];
                        board[targetY][x] = 0;
                        targetY--;
                    }
                    if (targetY > 0 && board[targetY - 1][x] == board[targetY][x]) {
                        board[targetY - 1][x] *= 2;
                        board[targetY][x] = 0;
                    }
                }
            }
        }

        return randomize();
    }

    private void moveDown() {
        System.out.println("Moving tiles down...");
        for (int x = 0; x < 4; x++) {
            for (int y = 2; y >= 0; y--) {
                if (board[y][x] != 0) {
                    int targetY = y;
                    while (targetY < 3 && board[targetY + 1][x] == 0) {
                        board[targetY + 1][x] = board[targetY][x];
                        board[targetY][x] = 0;
                        targetY++;
                    }
                    if (targetY < 3 && board[targetY + 1][x] == board[targetY][x]) {
                        board[targetY + 1][x] *= 2;
                        board[targetY][x] = 0;
                    }
                }
            }
        }
        randomize();
    }

    private void moveLeft() {
        System.out.println("Moving tiles left...");
        for (int y = 0; y < 4; y++) {
            for (int x = 1; x < 4; x++) {
                if (board[y][x] != 0) {
                    int targetX = x;
                    while (targetX > 0 && board[y][targetX - 1] == 0) {
                        board[y][targetX - 1] = board[y][targetX];
                        board[y][targetX] = 0;
                        targetX--;
                    }
                    if (targetX > 0 && board[y][targetX - 1] == board[y][targetX]) {
                        board[y][targetX - 1] *= 2;
                        board[y][targetX] = 0;
                    }
                }
            }
        }
        randomize();
    }

    private void moveRight() {
        System.out.println("Moving tiles right...");
        for (int y = 0; y < 4; y++) {
            for (int x = 2; x >= 0; x--) {
                if (board[y][x] != 0) {
                    int targetX = x;
                    while (targetX < 3 && board[y][targetX + 1] == 0) {
                        board[y][targetX + 1] = board[y][targetX];
                        board[y][targetX] = 0;
                        targetX++;
                    }
                    if (targetX < 3 && board[y][targetX + 1] == board[y][targetX]) {
                        board[y][targetX + 1] *= 2;
                        board[y][targetX] = 0;
                    }
                }
            }
        }
        randomize();
    }
}
