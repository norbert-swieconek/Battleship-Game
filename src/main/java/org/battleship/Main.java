package org.battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        Player player1 = new Player();
        Player player2 = new Player();


        System.out.println("Player 1, place your ships on the game field");
        System.out.println();
        setGame(sc, player1.getBoard());
        System.out.println("Pass the move to another player. Player 2, place your ships to the game field. Press enter to start.");
        sc.nextLine();
        System.out.println("...");
        System.out.println();
        System.out.println("Player 2, place your ships to the game field");
        System.out.println();
//        Player player2 = new Player(setGame(sc));
        setGame(sc, player2.getBoard());

        System.out.println();
        System.out.println("Pass the move to another player. Player 1 round. Press enter to start.");
        sc.nextLine();
        System.out.println("...");
        System.out.println();
        System.out.println("The game starts!");
        System.out.println();

        boolean isTurnPlayer1 = true;
        boolean isTurnPlayer2 = false;
        int whosTurn = 1;

        while (true) {
            switch (whosTurn) {
                case 1 -> {
                    while (isTurnPlayer1) {
                        boolean doHit = true;
                        while (doHit) {
                            player2.getBoard().displayMainBoard(true);
                            System.out.println("---------------------");
                            player1.getBoard().displayMainBoard(false);
                            System.out.println();
                            System.out.println("Player 1, it's your turn. Shoot:");
                            String coordinatesShoot = sc.nextLine().toUpperCase();
                            if (coordinatesShoot.matches("^[A-J](10|[1-9])$")) {
                                int row = board.translateAlphabetToInt(coordinatesShoot.substring(0, 1));
                                int col = Integer.parseInt(coordinatesShoot.substring(1)) - 1;
                                System.out.println();
                                doHit = player2.getBoard().shoot(row, col);
                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("Error: wrong coordinates.");
                                System.out.println();
                            }
                            if (player2.getBoard().getSankCells() == player2.getBoard().getAllShipCells()) {
                                player2.getBoard().displayMainBoard(false);
                                System.out.println("---------------------");
                                player1.getBoard().displayMainBoard(false);
                                System.out.println();
                                System.out.println("You sank the last ship. You won Player 1. Congratulations!");
                                return;
                            }
                        }
                        System.out.println("Pass the move to another player. Player 2 round. Press enter to start.");
                        sc.nextLine();
                        System.out.println("...");
                        System.out.println();

                        whosTurn = 2;
                        isTurnPlayer1 = false;
                        isTurnPlayer2 = true;
                    }
                }
                case 2 -> {
                    while (isTurnPlayer2) {
                        boolean doHit = true;

                        while (doHit) {
                            player1.getBoard().displayMainBoard(true);
                            System.out.println("---------------------");
                            player2.getBoard().displayMainBoard(false);
                            System.out.println();
                            System.out.println("Player 2, it's your turn. Shoot:");
                            String coordinatesShoot = sc.nextLine().toUpperCase();
                            if (coordinatesShoot.matches("^[A-J](10|[1-9])$")) {
                                int row = board.translateAlphabetToInt(coordinatesShoot.substring(0, 1));
                                int col = Integer.parseInt(coordinatesShoot.substring(1)) - 1;
                                System.out.println();
                                doHit = player1.getBoard().shoot(row, col);
                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("Error: wrong coordinates.");
                                System.out.println();
                            }

                        }

                        if (player1.getBoard().getSankCells() == player1.getBoard().getAllShipCells()) {
                            player1.getBoard().displayMainBoard(false);
                            System.out.println("---------------------");
                            player2.getBoard().displayMainBoard(false);
                            System.out.println();
                            System.out.println("You sank the last ship. You won Player 2. Congratulations!");
                            return;
                        }

                        System.out.println("Pass the move to another player. Player 1 round. Press enter to start.");
                        sc.nextLine();
                        System.out.println("...");
                        System.out.println();

                        whosTurn = 1;
                        isTurnPlayer2 = false;
                        isTurnPlayer1 = true;
                    }
                }
            }
        }
    }


    public static void setGame(Scanner sc, Board playerGrid) {
        for (ShipType shipType : ShipType.values()) {
            boolean isValid = false;
            while (!isValid) {
                int shipTypeLength = shipType.getLength();
                playerGrid.displayMainBoard(false);
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
                        int r1 = playerGrid.translateAlphabetToInt(coordinates[0].substring(0,1));
                        int c1 = Integer.parseInt(coordinates[0].substring(1)) - 1;

                        int r2 = playerGrid.translateAlphabetToInt(coordinates[1].substring(0,1));
                        int c2 = Integer.parseInt(coordinates[1].substring(1)) - 1;

                        startRow = Math.min(r1, r2);
                        endRow = Math.max(r1, r2);
                        startCol = Math.min(c1, c2);
                        endCol = Math.max(c1, c2);

                        if (playerGrid.placeShip(startRow, startCol, endRow, endCol, shipTypeLength)) {
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