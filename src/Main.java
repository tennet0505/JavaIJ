// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Main {

    static String[][] ticTacArray = {{" _ ", " _ ", " _ "}, {" _ ", " _ ", " _ "}, {" _ ", " _ ", " _ "}};
    static String[] passedSteps = {};
    static Scanner scanner = new Scanner(System.in);
    static boolean isFirstPlayerPlay = true;

    public static void main(String[] args) {
        Main.startGame();
    }
    static String firstPlayerName = "";
    static String secondPlayerName = "";
    static void startGame() {
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
        exampleCellCoordinate();
        enterCellCoordinate(firstPlayerName, scanner);
    }

    //input coordinate
    static void enterCellCoordinate(String playerName, Scanner scanner) {
        isFirstPlayerPlay = Objects.equals(playerName, firstPlayerName);
        System.out.printf(passedSteps.length == 0 ?
                "Let's start %s. \nChoose cell x:" : "Your move %s. \nChoose cell x:", playerName);
        int x = scanner.nextInt();
        System.out.print("Now choose y: ");
        int y = scanner.nextInt();
        System.out.printf("%s. Your cell is (%s, %s) \n", playerName, x, y);
        chooseCellWithValidation(x, y, playerName, scanner);
    }

    // setup tic tac array
    static void setupTicTacArray(int x, int y, boolean isFirstPlayerPlay) {

        Main.ticTacArray[x][y] = " " + (isFirstPlayerPlay ? "X" : "O") + " ";
        for (int i = 0; i < Main.ticTacArray.length; i++) {
            for (int j = 0; j < Main.ticTacArray[i].length; j++) {
                System.out.print(Main.ticTacArray[i][j]);
            }
            System.out.println("\t");
        };

        if (gameIsEnded()) {
            System.out.printf("%s you are a WINNER!", (isFirstPlayerPlay ? firstPlayerName : secondPlayerName));
        } else {
            addPassedStep(x,y);
            isFirstPlayerPlay = !isFirstPlayerPlay;
            enterCellCoordinate((isFirstPlayerPlay ? firstPlayerName : secondPlayerName), scanner);
        };
    };

    //checking game's status
    static boolean gameIsEnded() {
        boolean isEnded = false;
        for (int i = 0; i < Main.ticTacArray.length; i++) {
            if (Main.ticTacArray[i][0].equals(Main.ticTacArray[i][1])
                    && Main.ticTacArray[i][0].equals(Main.ticTacArray[i][2])
                    && !Main.ticTacArray[i][0].equals(" _ ")) {
                isEnded = true;
                break;
            }
        };
        for (int j = 0; j < Main.ticTacArray.length; j++) {
            if (Main.ticTacArray[0][j].equals(Main.ticTacArray[1][j])
                    && Main.ticTacArray[0][j].equals(Main.ticTacArray[2][j])
                    && !Main.ticTacArray[0][j].equals(" _ ")) {
                isEnded = true;
                break;
            }
        };
        if (Main.ticTacArray[0][0].equals(Main.ticTacArray[1][1])
                    && Main.ticTacArray[0][0].equals(Main.ticTacArray[2][2])
                    && !Main.ticTacArray[0][0].equals(" _ ")) {
                isEnded = true;
        };
        if (Main.ticTacArray[2][0].equals(Main.ticTacArray[1][1])
                && Main.ticTacArray[2][0].equals(Main.ticTacArray[0][2])
                && !Main.ticTacArray[2][0].equals(" _ ")) {
            isEnded = true;
        };
        return isEnded;
    };


    //update array with passed coordinates
    static void addPassedStep(int x, int y) {
        String cellCoordinate = String.format("(%s,%s)",x, y);
        String[] updatedArray = new String[Main.passedSteps.length + 1];
        for (int i = 0; i < Main.passedSteps.length; i++) {
            updatedArray[i] = Main.passedSteps[i];
        }
        updatedArray[Main.passedSteps.length] = cellCoordinate;
        Main.passedSteps = updatedArray;
    }

    // check validation of input coordinate
    static boolean validationCoordinate(String cellCoordiante) {
        return switch (cellCoordiante) {
            case "(0,0)", "(0,1)", "(0,2)", "(1,0)", "(1,1)", "(1,2)", "(2,0)", "(2,1)", "(2,2)" -> true;
            default -> false;
        };
    }


    //choose coordinate for player
    static void chooseCellWithValidation(int x, int y, String playerName, Scanner scanner) {
        String cellCoordinate = String.format("(%s,%s)",x, y);
        boolean isValid = validationCoordinate(cellCoordinate);
        boolean isContainCoordinate = Arrays.asList(passedSteps).contains(cellCoordinate);
        if (isContainCoordinate) {
            System.out.println("----- This cell is chosen, please choose other cell. ------");
            enterCellCoordinate(playerName, scanner);
            return;
        }
        if (isValid) {
            setupTicTacArray(x, y, isFirstPlayerPlay);
        } else {
            System.out.println("Please enter correct cell coordinate");
            exampleCellCoordinate();
            enterCellCoordinate(playerName, scanner);
        };
    }

    //example cell coordinate
    static void exampleCellCoordinate() {
        System.out.println("\n");
        System.out.println("(0,0) | (0,1) | (0,2)");
        System.out.println("---------------------");
        System.out.println("(1,0) | (1,1) | (1,2)");
        System.out.println("---------------------");
        System.out.println("(2,0) | (2,1) | (2,2)");
        System.out.println("\n");
    }
}