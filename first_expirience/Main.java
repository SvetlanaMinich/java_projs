import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        final var CONST_TRY = 'H';
        System.out.println(CONST_TRY);

        int digitsOfNums = 123_456;
        System.out.println(digitsOfNums);

        String textBlock = """
                Hello
                World
                """;
        System.out.println(textBlock);

        // Scanner in = new Scanner(System.in);
        // System.out.println("Enter line");
        // var numbFromConsole = in.nextLine();
        // System.out.println(numbFromConsole);
        // in.close();

        int increment = digitsOfNums++;
        System.out.println(increment);
        System.out.println(digitsOfNums);

        
    }
}
