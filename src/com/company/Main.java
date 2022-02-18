package com.company;

import java.util.*;

// ToDo Exercises
// ToDO - https://www.codewars.com/kata/51f2d1cafc9c0f745c00037d
// ToDO - https://www.codewars.com/kata/57eaeb9578748ff92a000009
// ToDO - https://www.codewars.com/kata/53dc23c68a0c93699800041d
// ToDO - https://www.codewars.com/kata/563cf89eb4747c5fb100001b
// ToDO - https://www.codewars.com/kata/54edbc7200b811e956000556
// ToDO - https://www.codewars.com/kata/58b8c94b7df3f116eb00005b
// ToDO - https://www.codewars.com/kata/56b7f2f3f18876033f000307
// ToDO - https://www.codewars.com/kata/5aff237c578a14752d0035ae
// ToDO - https://www.codewars.com/kata/5592e3bd57b64d00f3000047
// ToDO - https://www.codewars.com/kata/51e0007c1f9378fa810002a9
// ToDO - https://www.codewars.com/kata/598106cb34e205e074000031

enum CellStatus {EMPTY, X, O} // <- define the possible values of the board

enum Player {X, O} // <- define the possible values of the current player

class InvalidTicTacToeInput extends RuntimeException {
    InvalidTicTacToeInput(String msg){
        super(msg);
    }
}

class TicTacToeGame { // Also known as : Tris
    CellStatus[][] gameTable = new CellStatus[3][3];
    Player currentPlayer; // X o O

    TicTacToeGame() {
        for (int i = 0; i < 3; i++)                 // <-
            for (int j = 0; j < 3; j++)             // <- iterate over all the cells of the board
                gameTable[i][j] = CellStatus.EMPTY; // <- Set all cells empty

        currentPlayer = Player.X;                   // <- Set the initial player
    }

    public void makeMove(int i, int j) throws InvalidTicTacToeInput {
        if (i < 0 || i > 2 || j < 0 || j > 2) { // <- Check for out of bounds
            throw new InvalidTicTacToeInput("Out of Bounds");
        }
        if (gameTable[i][j] != CellStatus.EMPTY) { // <- check for already filled
            throw new InvalidTicTacToeInput("Position already used");
        }

        gameTable[i][j] = currentPlayer == Player.X ? CellStatus.X : CellStatus.O;
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    public void printBoard() {
        for (var row : gameTable) {
            for (var cell : row)
                System.out.print(cell == CellStatus.EMPTY ? "." : cell.toString());
            System.out.println();
        }
    }

    static private boolean isWinning(CellStatus c0, CellStatus c1, CellStatus c2){
        return c0 != CellStatus.EMPTY && c0 == c1 && c1 == c2;
    }

    static private Optional<Player> getWinner(CellStatus c) {
        return  Optional.empty(); // ToDo - leggi sotto
    }

    public Optional<Player> getTheWinner() {
        // Rows
        var g = this.gameTable;

        if (isWinning(g[0][0], g[0][1], g[0][2])) { // ToDo Finire di sostituire 'isWinning' anche nelle altre condizioni
            return Optional.of(g[0][0] == CellStatus.X ? Player.X : Player.O); // ToDo Creare il corpo della funzione 'getWinner' e sostituirlo nei return
        }
        if (g[1][0] != CellStatus.EMPTY && g[1][0] == g[1][1] && g[1][1] == g[1][2]) {
            return Optional.of(g[1][0] == CellStatus.X ? Player.X : Player.O);
        }
        if (g[2][0] != CellStatus.EMPTY && g[2][0] == g[2][1] && g[2][1] == g[2][2]) {
            return Optional.of(g[2][0] == CellStatus.X ? Player.X : Player.O);
        }

        // Columns
        if (g[0][0] != CellStatus.EMPTY && g[0][0] == g[1][0] && g[1][0] == g[2][0]) {
            return Optional.of(g[0][0] == CellStatus.X ? Player.X : Player.O);
        }
        if (g[0][1] != CellStatus.EMPTY && g[0][1] == g[1][1] && g[1][1] == g[2][1]) {
            return Optional.of(g[0][1] == CellStatus.X ? Player.X : Player.O);
        }
        if (g[0][2] != CellStatus.EMPTY && g[0][2] == g[1][2] && g[1][2] == g[2][2]) {
            return Optional.of(g[0][2] == CellStatus.X ? Player.X : Player.O);
        }

        // Diagonals
        if (g[0][0] != CellStatus.EMPTY && g[0][0] == g[1][1] && g[1][1] == g[2][2]) {
            return Optional.of(g[0][0] == CellStatus.X ? Player.X : Player.O);
        }
        if (g[0][2] != CellStatus.EMPTY && g[0][2] == g[1][1] && g[1][1] == g[2][0]) {
            return Optional.of(g[0][2] == CellStatus.X ? Player.X : Player.O);
        }

        return Optional.empty();
    }

    public boolean isDraw(){ // ToDo - Provare a rifare questi cicli nested con degli stream
        for(var row: gameTable)
            for (var l: row)
                if(l == CellStatus.EMPTY)
                    return false;

        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        var game = new TicTacToeGame();
        boolean gameOver = false;

        while (!gameOver) {
            try {
                Scanner scanner = new Scanner(System.in);
                String[] input = scanner.nextLine().replaceAll("[^\\d]", "").split("");

                int i = Integer.parseInt(input[0]);
                int j = Integer.parseInt(input[1]);

                game.makeMove(i, j);
                game.printBoard();
                String msg = game.getTheWinner()
                        .map(w -> w == Player.X ? "Ha vinto X" : "Ha vinto O")
                        .orElse("Non ha vinto ancora nessuno");

                System.out.println(msg);

                gameOver = game.getTheWinner().isPresent() || game.isDraw();
            }catch(InvalidTicTacToeInput e){ // ToDo - inserite anche gli altri catch che potrebbero essere imputabili a degli errori dell'utente (hint: nextLine())
                System.out.println("Invalid input");
            }
        }

        System.out.println("Grazie per aver giocato :D");
    }
}
