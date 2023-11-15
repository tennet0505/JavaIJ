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
        Main.exampleCellCoordinate();
        Main.enterCellCoordinate(firstPlayerName, scanner);
    }

    static void chooseCellWithValidation(int x, int y, String playerName, Scanner scanner) {
        String cellCoordinate = String.format("(%s,%s)",x, y);
        boolean isValid = validationCoordinate(cellCoordinate);
        if (isValid) {
        switch(cellCoordinate) {
            case "(0,0)":
                System.out.println(" x |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                break;
            case "(0,1)":
                System.out.println("   | x |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                break;
            case "(0,2)":
                System.out.println("   |   | x ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                break;
            case "(1,0)":
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println(" x |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                break;
            case "(1,1)":
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   | x |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                break;
            case "(1,2)":
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   | x ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                break;
            case "(2,0)":
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println(" x |   |   ");
                break;
            case "(2,1)":
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   | x |   ");
                break;
            case "(2,2)":
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   | x ");
                break;
            default:
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
                System.out.println("-----------");
                System.out.println("   |   |   ");
           }
        } else {
            System.out.println("Please enter correct cell coordinate");
            exampleCellCoordinate();
            enterCellCoordinate(playerName, scanner);
        };
    }

    static void exampleCellCoordinate() {
        System.out.println("\n");
        System.out.println("(0,0) | (0,1) | (0,2)");
        System.out.println("------------");
        System.out.println("(1,0) | (1,1) | (1,2)");
        System.out.println("------------");
        System.out.println("(2,0) | (2,1) | (2,2)");
        System.out.println("\n");
    }

    static void enterCellCoordinate(String playerName, Scanner scanner) {
        System.out.printf("Let's start %s. \nChoose cell x: ", playerName);
        int x = scanner.nextInt();
        System.out.print("Now choose y: ");
        int y = scanner.nextInt();
        System.out.printf("%s. Your cell is (%s, %s) \n", playerName, x, y);
        chooseCellWithValidation(x, y, playerName, scanner);
    }
    static boolean validationCoordinate(String cellCoordiante) {
        return switch (cellCoordiante) {
            case "(0,0)", "(0,1)", "(0,2)", "(1,0)", "(1,1)", "(1,2)", "(2,0)", "(2,1)", "(2,2)" -> true;
            default -> false;
        };
    }
}