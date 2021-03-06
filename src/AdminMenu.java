import api.AdminResource;
import model.*;

import java.util.*;

public class AdminMenu {

    AdminResource adminResource = AdminResource.getInstance();

    public void launch(Scanner scanner) {
        System.out.println();
        System.out.println("Admin Menu");
        System.out.println("-----------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-----------------------------------------");
        System.out.println("Please select a number for the menu option");

        while (true) {
            Done:
            switch (scanner.nextInt()) {
                case 1 -> {
                    Collection<Customer> customers = adminResource.getAllCustomers();
                    if (!customers.isEmpty()) {
                        customers.forEach(System.out::println);
                    } else {
                        System.out.println("There are no customers at this time");
                    }
                    launch(scanner);
                }
                case 2 -> {
                    Collection<IRoom> rooms = adminResource.getAllRooms();
                    if (!rooms.isEmpty()) {
                        rooms.forEach(System.out::println);
                    } else {
                        System.out.println("There are no rooms at this time");
                    }
                    launch(scanner);
                }
                case 3 -> {
                    adminResource.displayAllReservations();
                    launch(scanner);
                }
                case 4 -> {
                    List<IRoom> rooms = new ArrayList<>();
                    AnotherRoom:
                    while (true) {
                        try {
                            System.out.println("Enter room number");
                            String roomNumber = scanner.next();
                            System.out.println("Enter price per night");
                            double price = scanner.nextDouble();
                            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                            RoomType roomType;

                            while (true) {
                                int type = scanner.nextInt();
                                if (type == 1) {
                                    roomType = RoomType.SINGLE;
                                    break;
                                } else if (type == 2) {
                                    roomType = RoomType.DOUBLE;
                                    break;
                                } else {
                                    System.out.println("Please enter 1 (single bed) or 2 (double bed)");
                                }
                            }

                            rooms.add(new Room(roomNumber, price, roomType));

                            System.out.println("Would you like to add another room y/n");

                            while (true) {
                                switch (scanner.next().toLowerCase()) {
                                    case "y", "yes" -> {
                                        continue AnotherRoom;
                                    }
                                    case "n", "no" -> {
                                        adminResource.addRoom(rooms);
                                        launch(scanner);
                                        break Done;
                                    }
                                    default -> {
                                        System.out.println("Please enter Y (Yes) or N (No)");
                                    }
                                }

                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input: Re-enter room details");
                            scanner.next();
                        }
                    }
                }
                case 5 -> {
                    new MainMenu().launch(scanner.reset());
                    return;
                }
            }
        }
    }
}
