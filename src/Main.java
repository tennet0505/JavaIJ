// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    static String[][] ticTacArray = {{"➖", "➖", "➖"}, {"➖", "➖", "➖"}, {"➖", "➖", "➖"}};
    static String[][] ticTacArrayVertical = {{"➖", "➖", "➖"}, {"➖", "➖", "➖"}, {"➖", "➖", "➖"}};
    static String[][] ticTacArrayDiagonals = {{"➖", "➖", "➖"}, {"➖", "➖", "➖"}};
    static String[] passedSteps = {};
    static Scanner scanner = new Scanner(System.in);
    static boolean isFirstPlayerPlay = true;
    static int computerStep = 0;
    public static void main(String[] args) {
        Main.startGame();
    }
    static String playerName = "";
    static boolean isGameEnded = false;
    static boolean isNextGame = false;
    static String X = "❎";
    static String O = "\uD83C\uDD7E️";
    static String E = "➖";
    static void startGame() {

        if (!isNextGame) {
            System.out.println("Let's play Tic Tac Toe.");
            System.out.println("Human vs Comp version");
            System.out.print("Player enter your name: ");
            String firstUserName = scanner.nextLine();
            playerName = firstUserName;
            System.out.printf("Nice to meet you %s, you are playing with \"❎\" \n", firstUserName);
            System.out.print("I am an AI and I will your concurrent! I am playing with \"\uD83C\uDD7E️\"\n");
            exampleCellCoordinate();
        } else {
            System.out.println("Next Game.");
        }
        enterCellCoordinate(playerName, scanner);
    }

    static void resetGame() {
        isFirstPlayerPlay = true;
        computerStep = 0;
        isGameEnded = false;
        ticTacArray = new String[][]{{E, E, E}, {E, E, E}, {E, E, E}};
        ticTacArrayVertical =  new String[][]{{E, E, E}, {E, E, E}, {E, E, E}};
        ticTacArrayDiagonals =  new String[][]{{E, E, E}, {E, E, E}};
        passedSteps = new String[]{};
    }

    //input coordinate
    static void enterCellCoordinate(String playerName, Scanner scanner) {

        if (isFirstPlayerPlay) {
            isFirstPlayerPlay = false;

            int x = validateInputIsIntWithText(passedSteps.length == 0 ?
                    "Let's start " + playerName + ". You go first. \nChoose x: " : "Your move " + playerName + ". \nChoose x: ");
            int y = validateInputIsIntWithText("Now choose y: ");

            System.out.printf("%s. Your cell is (%s,%s) \n", playerName, x, y);
            checkCellWithValidation(x, y, playerName, scanner);
        } else {
            computerStep += 1;
            isFirstPlayerPlay = true;
            checkPattern();
        }
    }

    //validateInput
    static int validateInputIsIntWithText(String text) {
        int x = 0;
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print(text);
            String digit = input.nextLine();
            try{
                x = Integer.parseInt(digit);
                break;
            }catch (NumberFormatException ex){
                System.out.println("Invalid input " + x + " is not a number");
            }
        }
        return x;
    }

    static void checkPattern() {

        boolean isCenterHasOccupied = Arrays.asList(passedSteps).contains("(1,1)");
        if (computerStep == 1) {
            if (isCenterHasOccupied) {
                checkFreeCorner();
            } else {
                System.out.println("My move is: (1,1)");
                checkCellWithValidation(1, 1, playerName, scanner);
            }
        }
        checkingIfThere(true);
        if (!isGameEnded) {
            checkingIfThere(false);
        }
    }

    static void checkingIfThere(boolean isWinningPattern) {
        int occupiedI = 0;
        int occupiedJ = 0;

        //check rows
        for (int i = 0; i < Main.ticTacArray.length; i++) {
            int countOfXinRow = 0;
            for (int j = 0; j < Main.ticTacArray[i].length; j++) {
                if (Objects.equals(Main.ticTacArray[i][j], E)) {
                    occupiedI = i;
                    occupiedJ = j;
                }
                if (Objects.equals(Main.ticTacArray[i][j], isWinningPattern ? O : X)) {
                    countOfXinRow +=1;
                }
            }
            if (countOfXinRow == 2 && Arrays.asList(Main.ticTacArray[i]).contains(E)) {
                System.out.println("My move is: (" + occupiedI + "," + occupiedJ + ")");
                if (isWinningPattern) {
                    isGameEnded = true;
                }
                setupTicTacArray(occupiedI, occupiedJ, !isFirstPlayerPlay);
            }
        }

        //check columns
        for (int i = 0; i < Main.ticTacArrayVertical.length; i++) {
            int countOfXinColumn = 0;
            for (int j = 0; j < Main.ticTacArrayVertical[i].length; j++) {
                if (Objects.equals(Main.ticTacArrayVertical[i][j], E)) {
                    occupiedI = i;
                    occupiedJ = j;
                }
                if (Objects.equals(Main.ticTacArrayVertical[i][j], isWinningPattern ? O : X)) {
                    countOfXinColumn += 1;
                }
            }
            if (countOfXinColumn == 2 && Arrays.asList(Main.ticTacArrayVertical[i]).contains(E)) {
                System.out.println("My move is: (" + occupiedJ + "," + occupiedI + ")");
                if (isWinningPattern) {
                    isGameEnded = true;
                }
                setupTicTacArray(occupiedJ, occupiedI, !isFirstPlayerPlay);
            }
        }

        //check diagonals
        for (int i = 0; i < Main.ticTacArrayDiagonals.length; i++) {
            int countOfXinColumn = 0;
            for (int j = 0; j < Main.ticTacArrayDiagonals[i].length; j++) {
                if (Objects.equals(Main.ticTacArrayDiagonals[i][j], E)) {
                    occupiedI = i;
                    occupiedJ = j;
                }
                if (Objects.equals(Main.ticTacArrayDiagonals[i][j], isWinningPattern ? O : X)) {
                    countOfXinColumn += 1;
                }
                if (countOfXinColumn == 2 && Arrays.asList(Main.ticTacArrayDiagonals[i]).contains(E)) {
                    String cellCoordinate = String.format("(%s,%s)",occupiedI, occupiedJ);
                    switch (cellCoordinate) {
                        case ("(0,0)"):
                            System.out.println("My move is: (" + 0 + "," + 0 + ")");
                            if (isWinningPattern) {
                                isGameEnded = true;
                                setupTicTacArray(0, 0, !isFirstPlayerPlay);
                                break;
                            }
                        case ("(1,1)"), ("(0,1)"):
                            System.out.println("My move is: (" + 1 + "," + 1 + ")");
                            if (isWinningPattern) {
                                isGameEnded = true;
                                setupTicTacArray(1, 1, !isFirstPlayerPlay);
                                break;
                            }

                        case ("(0,2)"):
                            System.out.println("My move is: (" + 2 + "," + 2 + ")");
                            if (isWinningPattern) {
                                isGameEnded = true;
                                setupTicTacArray(2, 2, !isFirstPlayerPlay);
                                break;
                            }

                        case ("(1,2)"):
                            System.out.println("My move is: (" + 0 + "," + 2 + ")");
                            if (isWinningPattern) {
                                isGameEnded = true;
                                setupTicTacArray(0, 2, !isFirstPlayerPlay);
                                break;
                            }
                        default:
                            System.out.println("wrong coordinate!!!");
                    }
                }
            }
            if (countOfXinColumn == 2) {
                break;
            }
        }
        if (!isWinningPattern) {
             checkFreeCorner();
        }
    }

    static void checkFreeCorner() {
        if (!Arrays.asList(passedSteps).contains("(0,0)")) {
            System.out.println("My move is: (0,0)");
            setupTicTacArray(0, 0, !isFirstPlayerPlay);
        }
        if (!Arrays.asList(passedSteps).contains("(0,2)")) {
            System.out.println("My move is: (0,2)");
            setupTicTacArray(0, 2, !isFirstPlayerPlay);
        }
        if (!Arrays.asList(passedSteps).contains("(2,0)")) {
            System.out.println("My move is: (2,0)");
            setupTicTacArray(2, 0, !isFirstPlayerPlay);
        }
        if (!Arrays.asList(passedSteps).contains("(2,2)")) {
            System.out.println("My move is: (2,2)");
            setupTicTacArray(2, 2, !isFirstPlayerPlay);
        }

        for (int i = 0; i < Main.ticTacArray.length; i++) {
            for (int j = 0; j < Main.ticTacArray[i].length; j++) {
                if (Objects.equals(Main.ticTacArray[i][j], E)) {
                    System.out.println("My move is: (" + i + "," + j + ")");
                    setupTicTacArray(i, j, !isFirstPlayerPlay);
                }
            }
        }
    }

    //check validation
    static void checkCellWithValidation(int x, int y, String playerName, Scanner scanner) {
        String cellCoordinate = String.format("(%s,%s)",x, y);
        boolean isValid = validationCoordinate(cellCoordinate);
        boolean isContainCoordinate = Arrays.asList(passedSteps).contains(cellCoordinate);
        if (isContainCoordinate) {
            isFirstPlayerPlay = !isFirstPlayerPlay;
            System.out.println(isFirstPlayerPlay ? "❗️----- This cell is chosen, please choose other cell. ------❗️" : "I am thinking \uD83E\uDD14");
            enterCellCoordinate(playerName, scanner);
            return;
        }
        if (isValid) {
            setupTicTacArray(x, y, !isFirstPlayerPlay);
        } else {
            isFirstPlayerPlay = !isFirstPlayerPlay;
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
/////////////////
        setupTicTacToeField(x, y, isFirstPlayerPlay);
        checkGameStatus();
        drawTicTacToeField();
        if (isGameEnded) {
            System.out.println(isFirstPlayerPlay ? ("\uD83C\uDF89 Congratulation " + playerName + " you are a WINNER! \uD83C\uDF89") : "I am a WINNER! Ha-ha-ha.\uD83C\uDF89");
            startNextGame();
        } else {
            if (passedSteps.length == 8) {
                System.out.println("Finally! A worthy opponent! Our battle was legendary! \uD83E\uDD1D");
                startNextGame();
            } else {
                addPassedStep(x, y);
                enterCellCoordinate(playerName, scanner);
            }
        }
    }

    static void startNextGame() {
        System.out.println("One more game? yes/no: ");
        String newGame = scanner.nextLine();
        if (newGame.matches("yes")) {
            isNextGame = true;
            resetGame();
            startGame();
        } else {
            System.exit(0);
        }
    }

    //Draw a field Tic Tac Toe
    static void drawTicTacToeField() {
        for (int i = 0; i < Main.ticTacArray.length; i++) {
            for (int j = 0; j < Main.ticTacArray[i].length; j++) {
                System.out.print(Main.ticTacArray[i][j]);
            }
            System.out.println("\t");
        }
    }

    static void setupTicTacToeField(int x, int y, boolean isFirstPlayerPlay) {
        Main.ticTacArray[x][y] = (isFirstPlayerPlay ? X : O);
        Main.ticTacArrayVertical[y][x] = (isFirstPlayerPlay ? X : O);

        String cellCoordinate = String.format("(%s,%s)",x, y);
        switch (cellCoordinate) {
            case "(0,0)":
                Main.ticTacArrayDiagonals[0][0] = (isFirstPlayerPlay ? X : O);
                break;
            case "(1,1)":
                Main.ticTacArrayDiagonals[0][1] = (isFirstPlayerPlay ? X : O);
                Main.ticTacArrayDiagonals[1][1] = (isFirstPlayerPlay ? X : O);
                break;
            case "(2,2)":
                Main.ticTacArrayDiagonals[0][2] = (isFirstPlayerPlay ? X : O);
                break;
            case "(2,0)":
                Main.ticTacArrayDiagonals[1][0] = (isFirstPlayerPlay ? X : O);
                break;
            case "(0,2)":
                Main.ticTacArrayDiagonals[1][2] = (isFirstPlayerPlay ? X : O);
                break;
        }
    }

    //checking game's status
    static void checkGameStatus() {

        //check first row
        for (int i = 0; i < Main.ticTacArray.length; i++) {
            if (Main.ticTacArray[i][0].equals(Main.ticTacArray[i][1])
                    && Main.ticTacArray[i][0].equals(Main.ticTacArray[i][2])
                    && !Main.ticTacArray[i][0].equals(E)) {
                Main.ticTacArray[i][0] = coloredStringBackground(Main.ticTacArray[i][0]);
                Main.ticTacArray[i][1] = coloredStringBackground(Main.ticTacArray[i][1]);
                Main.ticTacArray[i][2] = coloredStringBackground(Main.ticTacArray[i][2]);
                isGameEnded = true;
            }
        }

        //check columns
        for (int i = 0; i < Main.ticTacArrayVertical.length; i++) {
            if (Main.ticTacArrayVertical[i][0].equals(Main.ticTacArrayVertical[i][1])
                    && Main.ticTacArrayVertical[i][0].equals(Main.ticTacArrayVertical[i][2])
                    && !Main.ticTacArrayVertical[i][0].equals(E)) {
                Main.ticTacArray[0][i] = coloredStringBackground(Main.ticTacArrayVertical[i][0]);
                Main.ticTacArray[1][i] = coloredStringBackground(Main.ticTacArrayVertical[i][1]);
                Main.ticTacArray[2][i] = coloredStringBackground(Main.ticTacArrayVertical[i][2]);
                isGameEnded = true;
        }

        //check first diagonal
        if (Main.ticTacArray[0][0].equals(Main.ticTacArray[1][1])
                    && Main.ticTacArray[0][0].equals(Main.ticTacArray[2][2])
                    && !Main.ticTacArray[0][0].equals(E)) {
                Main.ticTacArray[0][0] = coloredStringBackground(Main.ticTacArray[0][0]);
                Main.ticTacArray[1][1] = coloredStringBackground(Main.ticTacArray[1][1]);
                Main.ticTacArray[2][2] = coloredStringBackground(Main.ticTacArray[2][2]);
                isGameEnded = true;
        }

        //check second diagonal
        if (Main.ticTacArray[2][0].equals(Main.ticTacArray[1][1])
                && Main.ticTacArray[2][0].equals(Main.ticTacArray[0][2])
                && !Main.ticTacArray[2][0].equals(E)) {
            Main.ticTacArray[2][0] = coloredStringBackground(Main.ticTacArray[2][0]);
            Main.ticTacArray[1][1] = coloredStringBackground(Main.ticTacArray[1][1]);
            Main.ticTacArray[0][2] = coloredStringBackground(Main.ticTacArray[0][2]);
            isGameEnded = true;
        }
        }
    }

    //update array with passed coordinates
    static void addPassedStep(int x, int y) {
        String cellCoordinate = String.format("(%s,%s)",x, y);
        String[] updatedArray = new String[Main.passedSteps.length + 1];
        System.arraycopy(Main.passedSteps, 0, updatedArray, 0, Main.passedSteps.length);
        updatedArray[Main.passedSteps.length] = cellCoordinate;
        Main.passedSteps = updatedArray;
    }

    //color for background
    static String coloredStringBackground(String x) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BACKGROUND = "\u001B[30m";
        return ANSI_RED_BACKGROUND + x + ANSI_RESET;
    }

    //example cell coordinate
    static void exampleCellCoordinate() {

        System.out.println("\n");
        System.out.println("                  |       |      ");
        System.out.println("            (0,0) | (0,1) | (0,2)");
        System.out.println("          -------------------------");
        System.out.println("            (1,0) | (1,1) | (1,2)");
        System.out.println("          -------------------------");
        System.out.println("            (2,0) | (2,1) | (2,2)");
        System.out.println("                  |       |      ");
        System.out.println("\n");
        System.out.println("=============================================");
        System.out.println("||  First choose \"x\", then \"y\": (x,y)  ||");
        System.out.println("=============================================");
    }
}