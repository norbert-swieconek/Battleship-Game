# 🚢 Battleship Game

A classic Battleship game implemented as a robust Java console application. This project was built to demonstrate proficiency in **Object-Oriented Programming (OOP)**, algorithmic logic, and the modern Java ecosystem (**Gradle**, **Git**).

## 🎮 Key Features

* **Hotseat Multiplayer:** Turn-based gameplay for two players on a single machine.
* **Fog of War Mechanics:** The opponent's grid is hidden; only hits and misses are revealed.
* **Smart Input Validation:** The system prevents illegal ship placements (e.g., ships touching each other, out-of-bounds coordinates) and validates user input formats.
* **Complete Game Loop:** Includes setup phase, shooting mechanics, turn switching, and win condition detection.

## 🛠️ Tech Stack

* **Language:** Java 21
* **Build Tool:** Gradle (Groovy DSL)
* **Version Control:** Git

## 🚀 How to Run

### Prerequisites
* Java Development Kit (JDK) 21 or higher installed.

## 📖 How to Play

1.  **Ship Placement Phase:**
    * Each player places a fleet of 5 ships (Aircraft Carrier, Battleship, Submarine, Cruiser, Destroyer).
    * Enter the start and end coordinates separated by a space.
    * **Format:** `A1 A5` (vertical or horizontal).
    * **Rule:** Ships cannot touch each other (not even diagonally).

2.  **Battle Phase:**
    * Players take turns guessing coordinates to fire at.
    * Enter a target coordinate, e.g., `F4`.
    * **Hit:** Marked as `X`.
    * **Miss:** Marked as `M`.
    * The game ends when one player sinks all of the opponent's ships.

## 📂 Project Structure

The architecture follows solid OOP principles:

* **`Main`**: Orchestrates the game loop, manages player turns, and handles user I/O.
* **`Board`**: Encapsulates the grid logic, stores cell states, validates rules (adjacency, boundaries), and handles shooting mechanics.
* **`Player`**: Represents a player entity and holds their own `Board`.
* **`ShipType`**: Enum defining ship types and their specific lengths.
* **`CellStatus`**: Enum defining possible states of a grid cell (`FOG`, `SHIP`, `HIT`, `MISS`).
