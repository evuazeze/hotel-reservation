import java.util.Scanner;

public class AdminMenu {

    public void launch(Scanner scanner) {
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");

        while (true) {
            switch (scanner.nextInt()) {
                case 1 -> System.out.println("1...");
                case 2 -> System.out.println("2...");
                case 3 -> System.out.println("3...");
                case 4 -> System.out.println("4...");
                case 5 -> {
                    new MainMenu().launch(scanner.reset());
                    return;
                }
            }
        }
    }
}
