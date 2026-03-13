package com.example;

import java.util.Random;
import java.util.Scanner;

public class RPSGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        try {

            System.out.println("Enter your choice:");
            System.out.println("1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");

            int player = sc.nextInt();

            int computer = rand.nextInt(3) + 1;

            System.out.println("Computer choice: " + computer);
            if(player < 1 || player > 3){
                System.out.println("Invalid Input");}
            if(player == computer){
                System.out.println("Draw");
            }
            else if(player == 1 && computer == 3 ||
                    player == 2 && computer == 1 ||
                    player == 3 && computer == 2){
                System.out.println("Player Wins");
            }
            else{
                System.out.println("Computer Wins");
            }

        }
        catch(Exception e){
            System.out.println("Invalid Input");
        }

        
    }
}