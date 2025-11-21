package org.battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.displayBoard();

        System.out.println("Enter the coordinates of the ship:");
        // x1, x2
        String[] coordinates = sc.nextLine().split(" ");
        String firstCoordinateX;
        String firstCoordinateY;
        String endCoordinateX;
        String endCoordinateY;

        // TODO what if the input is single?
        int compareCoordinates = coordinates[0].compareTo(coordinates[1]);
        if (compareCoordinates < 0) {
            firstCoordinateX = coordinates[0].substring(0, 1);
            firstCoordinateY = coordinates[0].substring(1);
            endCoordinateX = coordinates[1].substring(0, 1);
            endCoordinateY = coordinates[1].substring(1);
        } else {
            firstCoordinateX = coordinates[1].substring(0, 1);
            firstCoordinateY = coordinates[1].substring(1);
            endCoordinateX = coordinates[0].substring(0, 1);
            endCoordinateY = coordinates[0].substring(1);
        }

        if (board.placeShip(firstCoordinateX, Integer.parseInt(firstCoordinateY), endCoordinateX, Integer.parseInt(endCoordinateY))) {
            System.out.println();

            // display length boat
            int shipLength;
            // axis x
            if (firstCoordinateX.equals(endCoordinateX)) {
                shipLength = Integer.parseInt(endCoordinateY) - Integer.parseInt(firstCoordinateY);
            // axis y
            } else {
                shipLength = board.translateAlphabetToInt(endCoordinateX) - board.translateAlphabetToInt(firstCoordinateX);
            }
            System.out.println("Length: " + (shipLength + 1));

            // displaying parts
            System.out.print("Parts: ");
            for (String coordinate : coordinates) {
                System.out.print(coordinate + " ");
            }

            // displaying actual board
            System.out.println();
            System.out.println();
            board.displayBoard();
        }
    }
}