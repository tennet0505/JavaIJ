// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        int age = 23;
        String name = "Oleg";
        double salaryExpectation = 100000;
        boolean lookingForJob = true;

        System.out.println(age);
        System.out.println(name);
        System.out.println("salaryExpectation" + salaryExpectation + "$/annually");
        System.out.println("lookingForJob: " + lookingForJob);

        Main.loopExample();
        Main.test();
        Main.testWithParam(1000);
    }

    public static void loopExample() {
        int[] arrayOfInt = {0, 1, 3, 5, 7};
        for (int valueOfArray : arrayOfInt) {
            System.out.println("valueOfArray: " + valueOfArray);
        }
    }

    public static void test() {
        System.out.println("new method call");
    }
    public static void testWithParam(int newValue) {
        System.out.print("print new value form params: ");
        System.out.print(newValue);
    }
}