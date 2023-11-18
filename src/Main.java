// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Main {
    static String[][] ticTacArray = {{" _ ", " _ ", " _ "}, {" _ ", " _ ", " _ "}, {" _ ", " _ ", " _ "}};
    static String[] passedSteps = {};
    static Scanner scanner = new Scanner(System.in);
    static boolean isFirstPlayerPlay = true;

    public static void main(String[] args) {
        Main.startGame();
    }
    static String playerName = "";
    static void startGame() {
        System.out.println("Let's play Tic Tac Toe.");
        System.out.println("Human vs Comp version");
        System.out.print("Player enter your name: ");
        String firstUserName = scanner.nextLine();
        playerName = firstUserName;

        System.out.printf("Nice to meet you %s, you are playing with \"X\" \n", firstUserName);
        System.out.print("I am an AI and I will your concurrent! I am playing with \"O\"\n");
        exampleCellCoordinate();
        enterCellCoordinate(playerName, scanner);
    }

    //input coordinate
    static void enterCellCoordinate(String playerName, Scanner scanner) {

        if (isFirstPlayerPlay) {
            isFirstPlayerPlay = false;
            System.out.print(passedSteps.length == 0 ?
                    "Let's start " + playerName + ". You go first. \nChoose x: " : "Your move " + playerName + ". \nChoose x: ");
            int x = scanner.nextInt();
            System.out.print("Now choose y: ");
            int y = scanner.nextInt();
            System.out.printf("%s. Your cell is (%s,%s) \n", playerName, x, y);
            checkCellWithValidation(x, y, playerName, scanner);
        } else {
            isFirstPlayerPlay = true;
            Random random = new Random();
            int indexX = random.nextInt(3);
            int indexY = random.nextInt(3);
            String cellCoordinate = String.format("(%s,%s)", indexX, indexY);
            boolean isContainCoordinate = Arrays.asList(passedSteps).contains(cellCoordinate);
            System.out.println(!isContainCoordinate ? "My move is: " + cellCoordinate : "");
            checkCellWithValidation(indexX, indexY, playerName, scanner);
        }
    }

    //choose coordinate for player
    static void checkCellWithValidation(int x, int y, String playerName, Scanner scanner) {
        String cellCoordinate = String.format("(%s,%s)",x, y);
        boolean isValid = validationCoordinate(cellCoordinate);
        boolean isContainCoordinate = Arrays.asList(passedSteps).contains(cellCoordinate);
        if (isContainCoordinate) {
            isFirstPlayerPlay = ! isFirstPlayerPlay;
            System.out.println(isFirstPlayerPlay ? "❗️----- This cell is chosen, please choose other cell. ------❗️" : "I am thinking \uD83E\uDD14");
            enterCellCoordinate(playerName, scanner);
            return;
        }
        if (isValid) {
            setupTicTacArray(x, y, !isFirstPlayerPlay);
        } else {
            System.out.println("⛔️Please enter correct cell coordinate⛔️");
            exampleCellCoordinate();
            enterCellCoordinate(playerName, scanner);
        }
    }

    // check validation of input coordinate
    static boolean validationCoordinate(String cellCoordiante) {
        return switch (cellCoordiante) {
            case "(0,0)", "(0,1)", "(0,2)", "(1,0)", "(1,1)", "(1,2)", "(2,0)", "(2,1)", "(2,2)" -> true;
            default -> false;
        };
    }

    // setup tic tac array
    static void setupTicTacArray(int x, int y, boolean isFirstPlayerPlay) {

        if (passedSteps.length == 8) {
            setupTicTacField(x, y, isFirstPlayerPlay);
            System.out.print("Finally! A worthy opponent! Our battle was legendary! \uD83E\uDD1D");
        } else if (isGameEnded()) {
            setupTicTacField(x, y, isFirstPlayerPlay);
            System.out.print(isFirstPlayerPlay ? ("\uD83C\uDF89 Congratulation " + playerName + " you are a WINNER! \uD83C\uDF89") : "I am a WINNER! Ha-ha-ha.\uD83C\uDF89");
        } else {
            setupTicTacField(x, y, isFirstPlayerPlay);
            addPassedStep(x, y);
            enterCellCoordinate(playerName, scanner);
        }
    }

    static void setupTicTacField(int x, int y, boolean isFirstPlayerPlay) {
        Main.ticTacArray[x][y] = " " + (isFirstPlayerPlay ? "X" : "O") + " ";
        for (int i = 0; i < Main.ticTacArray.length; i++) {
            for (int j = 0; j < Main.ticTacArray[i].length; j++) {
                System.out.print(Main.ticTacArray[i][j]);
            }
            System.out.println("\t");
        }
    }

    //checking game's status
    static boolean isGameEnded() {
        boolean isEnded = false;
        //check first row
        for (int i = 0; i < Main.ticTacArray.length; i++) {
            if (Main.ticTacArray[i][0].equals(Main.ticTacArray[i][1])
                    && Main.ticTacArray[i][0].equals(Main.ticTacArray[i][2])
                    && !Main.ticTacArray[i][0].equals(" _ ")) {
                Main.ticTacArray[i][0] = coloredStringBackground(Main.ticTacArray[i][0]);
                Main.ticTacArray[i][1] = coloredStringBackground(Main.ticTacArray[i][0]);
                Main.ticTacArray[i][2] = coloredStringBackground(Main.ticTacArray[i][0]);
                isEnded = true;
                break;
            }
        }
        //check first column
        for (int j = 0; j < Main.ticTacArray.length; j++) {
            if (Main.ticTacArray[0][j].equals(Main.ticTacArray[1][j])
                    && Main.ticTacArray[0][j].equals(Main.ticTacArray[2][j])
                    && !Main.ticTacArray[0][j].equals(" _ ")) {
                Main.ticTacArray[1][j] = coloredStringBackground(Main.ticTacArray[1][j]);
                isEnded = true;
                break;
            }
        }
        //check first diagonal
        if (Main.ticTacArray[0][0].equals(Main.ticTacArray[1][1])
                    && Main.ticTacArray[0][0].equals(Main.ticTacArray[2][2])
                    && !Main.ticTacArray[0][0].equals(" _ ")) {
                Main.ticTacArray[0][0] = coloredStringBackground(Main.ticTacArray[0][0]);
                Main.ticTacArray[1][1] = coloredStringBackground(Main.ticTacArray[1][1]);
                Main.ticTacArray[2][2] = coloredStringBackground(Main.ticTacArray[2][2]);
                isEnded = true;
        }
        //check first diagonal
        if (Main.ticTacArray[2][0].equals(Main.ticTacArray[1][1])
                && Main.ticTacArray[2][0].equals(Main.ticTacArray[0][2])
                && !Main.ticTacArray[2][0].equals(" _ ")) {
            Main.ticTacArray[2][0] = coloredStringBackground(Main.ticTacArray[2][0]);
            Main.ticTacArray[1][1] = coloredStringBackground(Main.ticTacArray[1][1]);
            Main.ticTacArray[0][2] = coloredStringBackground(Main.ticTacArray[0][2]);
            isEnded = true;
        }
        return isEnded;
    }

    //update array with passed coordinates
    static void addPassedStep(int x, int y) {
        String cellCoordinate = String.format("(%s,%s)",x, y);
        String[] updatedArray = new String[Main.passedSteps.length + 1];
        System.arraycopy(Main.passedSteps, 0, updatedArray, 0, Main.passedSteps.length);
        updatedArray[Main.passedSteps.length] = cellCoordinate;
        Main.passedSteps = updatedArray;
    }

    static void checkWinPattern() {
        /*
        first step
        - check the center:
                boolean isCenterHasOccupied = the center is occupied?
                if isCenterHasOccupied {
                   then we have to occupy any corner
                } else {
                   we have to occupy the center
                }

        second step
        -       if isCenterHasOccupied {
                    check and guard !!!
                } else {
                    we have to occupy any free corner
                }
       third step
       -        boolean isWinCase = check rows, columns and diagonals for win case
                if  isWinCase {
                    occupy cell //we are a winner
                } else {
                    guard position
                }
       last step
       -        repeat previous step
         */

    }

    //color for background
    static String coloredStringBackground(String x) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        return ANSI_RED_BACKGROUND + x + ANSI_RESET;
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