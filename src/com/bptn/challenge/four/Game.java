package com.bptn.challenge.four;

import com.bptn.challenge.four.exceptions.ColumnFullException;
import com.bptn.challenge.four.exceptions.InvalidMoveException;


import java.util.Scanner;
public class Game {

    private Player[] players;
    private Board board;
    private  Scanner scanner = new Scanner(System.in);

    public Game() {
        // Let's default it two players for now. Later, you can improve upon this to allow the game creator to choose how many players are involved.
        this.players = new Player[2];  // complete line.
        this.board = new Board();//  complete line
    }
    public void setUpGame() {
        System.out.println("Enter player 1's name: ");
        players[0] = new Player(scanner.nextLine(), "1");
        System.out.println("Enter player 2's name: ");
        String playerTwoName = scanner.nextLine();
        /* add logic to prevent a user from giving a second name that's equal to the first. Allow the user to try as long as the names are not different.*/
        if (playerTwoName.equals(players[0].getName())) {
            while ((players[0].getName().equals(playerTwoName))) {
                System.out.println("Error! Both Players cannot have the same name.");
                System.out.println("Enter player 2's name: ");
                playerTwoName = scanner.nextLine();

            }
        }
        players[1] = new Player(playerTwoName, "2");

        // set up the board using the appropriate method
        board.boardSetUp();
        // print the board the using appropriate method
        board.printBoard();

    }
    public void printWinner(Player player) {
        System.out.println(player.getName() + " is the winner");
    }
    public void playerTurn(Player currentPlayer)  {
        int col;
        try {
            col = currentPlayer.makeMove();
            while (!board.addToken(col, currentPlayer.getPlayerNumber())) {
                // call board method to add token.
                board.addToken(col, currentPlayer.getName());
            }
            // print board
            board.printBoard();
        }catch (InvalidMoveException e){
            System.out.print("Retry: " + e);
            playerTurn(currentPlayer);
        }catch (ColumnFullException e) {
                System.out.println(e);
                playerTurn(currentPlayer);
         }

    }
    public void play() {
        boolean noWinner = true;
        this.setUpGame();
        int currentPlayerIndex = 0;

        while (noWinner) {
            if (board.boardFull()) {
                System.out.println("Board is now full. Game Ends.");
                return;
            }
            Player currentPlayer = players[currentPlayerIndex];
            // Override default tostring for Player class
            System.out.println("It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer);
            playerTurn(currentPlayer);
            if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber())) {
                printWinner(currentPlayer);
                noWinner = false;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length; // reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).
            }
        }
    }

}

