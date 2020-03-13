package com.mogikanlol.game.core.pacman;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Grid {

    private GridBlock[][] grid = new GridBlock[15][20];

    public Grid() {

        int[][] tmp = loadMapFromFile();

        for (int row = 0, i = 14; row < 15; row++, i --) {
            for (int col = 0; col < 20; col++) {
                grid[row][col] = toGridBlock(row, col, tmp[i][col]);
            }
        }
    }

    public GridBlock[][] getGrid() {
        return grid;
    }

    public GridBlock at(int row, int col) {
        return grid[row][col];
    }

    private GridBlock toGridBlock(int row, int col, int type) {
        return new GridBlock(col * 40, row * 40, type);
    }

    private int[][] loadMapFromFile() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("pacman/pacman-map.txt");

        int[][] tmp = new int[15][20];

        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            int row = 0;
            String[] splittedLine;
            while (br.ready()) {
                splittedLine = br.readLine().split(",");
                for (int col = 0; col < splittedLine.length; col++) {
                    tmp[row][col] = Integer.parseInt(splittedLine[col]);
                }
                row++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }
}
