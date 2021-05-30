import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Welcome to The Hotel Reservation Application");
        Scanner scanner = new Scanner(System.in);
        new MainMenu().launch(scanner);
    }
}
