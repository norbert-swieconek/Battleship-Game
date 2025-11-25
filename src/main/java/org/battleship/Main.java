package org.battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();

        for (ShipType shipType : ShipType.values()) {
            boolean isValid = false;
            while (!isValid) {
                int shipTypeLength = shipType.getLength();
                board.displayBoard();
                System.out.println();
                System.out.println("Enter the coordinates of the " + shipType.getName() + " (" + shipType.getLength() + " cells):");

                // x1, x2
                String[] coordinates = sc.nextLine().toUpperCase().split(" ");
                int startRow;
                int startCol;
                int endRow;
                int endCol;

                if (coordinates.length != 2) {
                    System.out.println();
                    System.out.println("Error: wrong coordinates.");
                    System.out.println();
                } else {
                    int r1 = board.translateAlphabetToInt(coordinates[0].substring(0,1));
                    int c1 = Integer.parseInt(coordinates[0].substring(1)) - 1;

                    int r2 = board.translateAlphabetToInt(coordinates[1].substring(0,1));
                    int c2 = Integer.parseInt(coordinates[1].substring(1)) - 1;

                    startRow = Math.min(r1, r2);
                    endRow = Math.max(r1, r2);
                    startCol = Math.min(c1, c2);
                    endCol = Math.max(c1, c2);

                    if (board.placeShip(startRow, startCol, endRow, endCol, shipTypeLength)) {
                        int shipLength;
                        // axis x
                        if (startRow == endRow) {
                            shipLength = endCol - startCol;
                            // axis y
                        } else {
                            shipLength = endRow - startRow;
                        }
                        displayInfo(coordinates, shipLength);
                        isValid = true;
                    }
                }
            }
        }
        board.displayBoard();
    }

    public static void displayInfo(String[] coordinates, int shipLength) {
        System.out.println();

        System.out.println("Length: " + (shipLength + 1));

        // displaying parts
        System.out.print("Parts: ");
        for (String coordinate : coordinates) {
            System.out.print(coordinate + " ");
        }

        // displaying actual board
        System.out.println();
        System.out.println();
    }

}