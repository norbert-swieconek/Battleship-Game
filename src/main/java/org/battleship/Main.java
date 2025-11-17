package org.battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.displayBoard();

        System.out.println("Enter the coordinates of the ship:");
        // x1, x2
        String[] coordinates = sc.nextLine().split(" ");
        String[] firstCoordinate;
        String[] endCoordinate;

        int compareCoordinates = coordinates[0].compareTo(coordinates[1]);
        if (compareCoordinates < 0) {
            firstCoordinate = coordinates[0].split("");
            endCoordinate = coordinates[1].split("");
        } else {
            firstCoordinate = coordinates[1].split("");
            endCoordinate = coordinates[0].split("");
        }

        int boatLength = 0;

        if (!firstCoordinate[0].equals(endCoordinate[0]) && !firstCoordinate[1].equals(endCoordinate[1])) {
            System.out.println("Error: Ship must be placed on x or y axis or coordinates are out of board.");
        } else {
            // x or y
            if (firstCoordinate[0].equals(endCoordinate[0])) {
                boatLength = Integer.parseInt(endCoordinate[1]) - Integer.parseInt(firstCoordinate[1]);

                if (boatLength > 3) {
                    System.out.println("Error: Ship can't be bigger than 4 fields.");
                } else {
                    // length of boat and setting on board depends on length
                    if (boatLength == 1) {
                        board.setGrid(translateAlphabetToInt(firstCoordinate[0]), Integer.parseInt(firstCoordinate[1]) - 1);
                        board.setGrid(translateAlphabetToInt(endCoordinate[0]), Integer.parseInt(endCoordinate[1]) - 1);
                    } else {
                        for (int i = boatLength; i >= 0; i--) {
                            board.setGrid(translateAlphabetToInt(firstCoordinate[0]), Integer.parseInt(firstCoordinate[1]) - 1 + i);
                        }
                    }
                }
            } else {
                boatLength = translateAlphabetToInt(endCoordinate[0]) - translateAlphabetToInt(firstCoordinate[0]);

                if (boatLength > 3) {
                    System.out.println("Error: Ship can't be bigger than 4 fields.");
                } else {
                    if (boatLength == 1) {
                        board.setGrid(translateAlphabetToInt(firstCoordinate[0]), Integer.parseInt(firstCoordinate[1]) - 1);
                        board.setGrid(translateAlphabetToInt(endCoordinate[0]), Integer.parseInt(endCoordinate[1]) - 1);
                    } else {
                        for (int i = boatLength; i >= 0; i--) {
                            board.setGrid(translateAlphabetToInt(firstCoordinate[0]) + i, Integer.parseInt(firstCoordinate[1]) - 1);
                        }
                    }
                }
            }
        }

        System.out.println();

        // display length boat
        System.out.println("Length: " + (boatLength + 1));

        //displaying parts
        System.out.print("Parts: ");
        for (String coordinate : coordinates) {
            System.out.print(coordinate + " ");
        }

        // displaying actual board
        System.out.println();
        System.out.println();
        board.displayBoard();
    }

    // translate char to index of column
    public static int translateAlphabetToInt(String x) {
        int row = 0;
        switch (x) {
            case "A" -> row = 0;
            case "B" -> row = 1;
            case "C" -> row = 2;
            case "D" -> row = 3;
            case "E" -> row = 4;
            case "F" -> row = 5;
            case "G" -> row = 6;
            case "H" -> row = 7;
            case "I" -> row = 8;
            case "J" -> row = 9;
        }
        return row;
    }

}