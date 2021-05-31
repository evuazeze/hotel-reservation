import api.HotelResource;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    HotelResource hotelResource = HotelResource.getInstance();

    public void launch(Scanner scanner) {
        System.out.println("-----------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-----------------------------------------");
        System.out.println("Please select a number for the menu option");

        while (true) {
            switch (scanner.nextInt()) {
                case 1 -> System.out.println("1...");
                case 2 -> System.out.println("2...");
                case 3 -> {
                    while (true) {
                        try {
                            System.out.println("Enter Email format: name@domain.com");
                            String email = scanner.next();
                            System.out.println("First Name");
                            String firstName = scanner.next();
                            System.out.println("Last Name");
                            String lastName = scanner.next();

                            hotelResource.createACustomer(email, firstName, lastName);
                            launch(scanner);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input: Re-enter details");
                            scanner.next();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid email: Re-enter details");
                        }
                    }
                }
                case 4 -> {
                    new AdminMenu().launch(scanner.reset());
                    return;
                }
                case 5 -> System.exit(0);
            }
        }
    }
}
