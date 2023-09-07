/*
 The player class is concerned with descibing a player and things that relate to it. It keeps track of the name of a player, the order of the player in relation to other players in the game, and the move the player may want to make (which is just as simple as the user picking which column of the board they want their token to be dropped in). This class could also have logic to create only valid users. E.g. the playerNumber should not be greater than 4 based on the specification we've received.
 */
package com.bptn.challenge.four;


import java.util.InputMismatchException;
import java.util.Scanner;
public class Player {

    private String name;
    // Add other instance variable(s)
    private String playerNumber;

    private String tokenColour;

    // Question: should scanner be static or not if i have other classes that would get user input
    private static Scanner scanner = new Scanner(System.in); // complete line



    public Player(String name, String playerNumber, String tokenColour) {
        // complete constructor
        this.name = name;
        this.playerNumber = playerNumber;
        this.tokenColour = tokenColour;


    }

    // create getter methods
    public String getName() {
        return name;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public String getTokenColour() {return tokenColour;}



    public int makeMove() throws InputMismatchException {

        int column = -1;

        while (column <0){

            try{
                System.out.println("Make your move. What column do you want to put a token in?");
                column= scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.print("Please enter a correct input ");
                scanner.nextLine();
            }

        }

return column;
    }

    public String toString() {
        return ("Player " + playerNumber + " is " + name + " and their Token colour is " + tokenColour);
    }
}