import java.util.Scanner;

public class MainMenu {

    public void launch(Scanner scanner) {
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");

        while (true) {
            switch (scanner.nextInt()) {
                case 1 -> System.out.println("1...");
                case 2 -> System.out.println("2...");
                case 3 -> System.out.println("3...");
                case 4 -> {
                    new AdminMenu().launch(scanner.reset());
                    return;
                }
                case 5 -> System.exit(0);
            }
        }
    }
}