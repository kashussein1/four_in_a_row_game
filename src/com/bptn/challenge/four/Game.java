package com.bptn.challenge.four;

import com.bptn.challenge.four.exceptions.ColumnFullException;
import com.bptn.challenge.four.exceptions.InvalidMoveException;


import java.util.*;

public class Game {

    private Player[] players;
    private ArrayList<Player> playerList;
    private HashSet<String> nameSet;

    private HashSet<String> tokenSet;
    private Board board;
    private  Scanner scanner = new Scanner(System.in);


private Timer timer = new Timer();
private int seconds = 120;

    public Game() {
        // Let's default it two players for now. Later, you can improve upon this to allow the game creator to choose how many players are involved.
//        this.players = new Player[2];  // complete line.
        this.board = new Board();//  complete line
        this.playerList = new ArrayList<>();
        this.nameSet = new HashSet<>();
        this.tokenSet = new HashSet<>();
    }


    public void setUpGame() {

        System.out.println("Enter the number of players: ");
        int nums = scanner.nextInt();
        int counter =0;
        scanner.nextLine();

        String name = null;
        while (counter < nums){
            boolean endLoop = true;
            boolean endLoop2 = true;
            boolean endLoop3 = true;
            boolean isComputer =false;

            while (endLoop){
                System.out.println("Enter player " + (counter +1) + "'s name" ) ;

                name = scanner.nextLine();

                if(nameSet.contains(name)){
                    System.out.println("Error! Players cannot have the same name.");

                }else {
                    nameSet.add(name);
                    endLoop =false;

                }
            }
            String tokenColour = null;
            while (endLoop2){

                System.out.println("Enter player " + (counter +1) + "'s Token colour" ) ;
                tokenColour = scanner.nextLine();
                if(tokenSet.contains(tokenColour)){
                    System.out.println("Error! Players cannot have the same Token colour.");

                }else {
                    tokenSet.add(tokenColour);

                }
                endLoop2 =false;
            }



            playerList.add((new Player(name, String.valueOf(counter+1), tokenColour)));

            counter++;





        }


//        System.out.println("Enter player 1's name: ");
//        players[0] = new Player(scanner.nextLine(), "1");
//        System.out.println("Enter player 2's name: ");
//        String playerTwoName = scanner.nextLine();
//        /* add logic to prevent a user from giving a second name that's equal to the first. Allow the user to try as long as the names are not different.*/
//        if (playerTwoName.equals(players[0].getName())) {
//            while ((players[0].getName().equals(playerTwoName))) {
//                System.out.println("Error! Both Players cannot have the same name.");
//                System.out.println("Enter player 2's name: ");
//                playerTwoName = scanner.nextLine();
//
//            }
//        }
//        players[1] = new Player(playerTwoName, "2");

        // set up the board using the appropriate method
        board.boardSetUp();
        // print the board the using appropriate method
        board.printBoard();

    }
    public void printWinner(Player player) {
        System.out.println(player.getName() + " is the winner");
    }
    public void playerTurn(Player currentPlayer)  {
        int col =0;


            try {
                col = currentPlayer.makeMove();
                while (!board.addToken(col, currentPlayer.getPlayerNumber())) {
                    // call board method to add token.
                    board.addToken(col, currentPlayer.getPlayerNumber());

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

        timer.scheduleAtFixedRate(new GameTimer(), 0, 1000);


        while (noWinner ) {
            if (board.boardFull()) {
                System.out.println("Board is now full. Game Ends.");
                return;
            }
            Player currentPlayer = playerList.get(currentPlayerIndex);
            // Override default tostring for Player class
            System.out.println("It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer);
            playerTurn(currentPlayer);
            if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber())) {
                printWinner(currentPlayer);
                noWinner = false;
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size(); // reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).
            }
            System.out.println("Time left: " + seconds + " seconds");
        }

    }
    private class GameTimer extends TimerTask {



        @Override
        public void run() {
            seconds --;

            if(seconds <=0){
                timer.cancel();
                System.out.println("Times up, Game Over");


            }
        }
    }
}

