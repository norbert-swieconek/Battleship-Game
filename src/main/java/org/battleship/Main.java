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
                board.displayMainBoard(false);
                System.out.println();
                System.out.println("Enter the coordinates of the " + shipType.getName() + " (" + shipType.getLength() + " cells):");

                // x1, x2
                String[] coordinates = sc.nextLine().toUpperCase().split(" ");
                int startRow;
                int startCol;
                int endRow;
                int endCol;

                if (!checkInputsSet(coordinates)) {
                    System.out.println();
                    System.out.println("Error: wrong coordinates.");
                    System.out.println();
                } else {
                    if (!checkCharsSet(coordinates)) {
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
        }

        System.out.println("The game starts!");
        System.out.println();

        board.displayMainBoard(true);
        System.out.println();
        System.out.println("Take a shot!");
        System.out.println();

        String coordinatesShoot = sc.nextLine().toUpperCase();


        if (coordinatesShoot.matches("^[A-J](10|[1-9])$")) {
                int row = board.translateAlphabetToInt(coordinatesShoot.substring(0, 1));
                int col = Integer.parseInt(coordinatesShoot.substring(1)) - 1;
                System.out.println();
                board.shoot(row, col);
                System.out.println();
                board.displayMainBoard(true);
        } else {
            System.out.println();
            System.out.println("Error: wrong coordinates.");
            System.out.println();
        }
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

    public static boolean checkInputsSet(String[] coordinates) {
        if (coordinates.length != 2) {
            return false;
        }
        if (coordinates[0].length() <= 1 || coordinates[0].length() > 3 || coordinates[1].length() <= 1 || coordinates[1].length() > 3) {
            return false;
        }
        return true;
    }

    public static boolean checkCharsSet(String[] coordinates) {
        if (!coordinates[0].matches("^[A-J](10|[1-9])$") || !coordinates[1].matches("^[A-J](10|[1-9])$")) {
            return false;
        }
        return true;
    }

}