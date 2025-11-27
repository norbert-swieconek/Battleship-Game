package org.battleship;

public enum ShipType {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5, 0),
    BATTLESHIP("Battleship", 4, 0),
    SUBMARINE("Submarine", 3, 0),
    CRUISER("Cruiser", 3, 0),
    DESTROYER("Destroyer", 2, 0);

    private final String name;
    private final int length;


    ShipType(String name, int length, int hitCells) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
