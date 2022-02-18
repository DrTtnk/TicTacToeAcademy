package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameTest {

    @Test
    void an_empty_board_has_no_winner() {
        var testGame = new TicTacToeGame();
        testGame.gameTable = new CellStatus[][]{
                {CellStatus.EMPTY, CellStatus.EMPTY, CellStatus.EMPTY},
                {CellStatus.EMPTY, CellStatus.EMPTY, CellStatus.EMPTY},
                {CellStatus.EMPTY, CellStatus.EMPTY, CellStatus.EMPTY},
        };
        assertFalse(testGame.getTheWinner().isPresent());
    }

    @Test
    void a_board_with_a_full_row_has_a_winner_of_the_same_type_of_the_cell() {
        var testGame = new TicTacToeGame();
        testGame.gameTable = new CellStatus[][]{
                {CellStatus.X,     CellStatus.X,     CellStatus.X},
                {CellStatus.EMPTY, CellStatus.EMPTY, CellStatus.EMPTY},
                {CellStatus.EMPTY, CellStatus.EMPTY, CellStatus.EMPTY},
        };
        assertEquals(Player.X, testGame.getTheWinner().orElseThrow());
    }

    // ToDO Aggiungere altri casi di test per colonne e diagonali e diversi giocatori
}