package org.battleship;

import java.util.Arrays;

public class Board {
    private char[][] grid;

    public Board () {
        this.grid = new char[10][10];

        for (char[] chars : grid) {
            Arrays.fill(chars, '~');
        }
    }
}
