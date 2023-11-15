// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Main.startGame();
    }
    static void startGame() {
        String firstPlayerName = "";
        String secondPlayerName = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's play Tic Tac Toe.");
        System.out.println("Human vs Human version");
        System.out.print("First player enter your name: ");
        String firstUserName = scanner.nextLine();
        firstPlayerName = firstUserName;

        System.out.printf("Nice to meet you %s, you are playing with \"X\" \n", firstUserName);
        System.out.print("Second player enter your name: ");
        String secondUserName = scanner.nextLine();
        secondPlayerName = secondUserName;

        System.out.printf("Nice to meet you %s you are playing with \"O\"\n", secondUserName);
        System.out.println("\n");
        System.out.println("(0,0) | (0,1) | (0,2)");
        System.out.println("------------");
        System.out.println("(1,0) | (1,1) | (1,2)");
        System.out.println("------------");
        System.out.println("(2,0) | (2,1) | (2,2)");
        System.out.println("\n");
        System.out.printf("Let's start %s. \nChoose cell x: ", firstPlayerName);
        int x = scanner.nextInt();
        System.out.print("Now choose y: ");
        int y = scanner.nextInt();
        System.out.printf("%s. Your cell is (%s, %s) \n", firstPlayerName, x, y);
    }

}