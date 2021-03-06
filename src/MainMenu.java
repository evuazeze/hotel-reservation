import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                case 1 -> {
                    while (true) {
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

                            System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                            String checkInDateStr = scanner.next();
                            Date checkInDate = formatter.parse(checkInDateStr);

                            System.out.println("Enter CheckOut Date month/day/year example 2/21/2020");
                            String checkOutDateStr = scanner.next();
                            Date checkOutDate = formatter.parse(checkOutDateStr);

                            Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);

                            if (rooms.isEmpty()) {
                                System.out.println("There are no rooms available at this time \n");
//                                launch(scanner);
//                                break;
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(formatter.parse(checkInDateStr));
                                calendar.add(Calendar.DAY_OF_MONTH, 7);
                                Date extendedCheckInDate = calendar.getTime();
                                calendar.setTime(formatter.parse(checkOutDateStr));
                                calendar.add(Calendar.DAY_OF_MONTH, 7);
                                Date extendedCheckOutDate = calendar.getTime();

                                Collection<IRoom> extendedSearchRooms = hotelResource.findARoom(extendedCheckInDate, extendedCheckOutDate);

                                if (extendedSearchRooms.isEmpty()) {
                                    System.out.println("Or in 7 days from now");
                                    launch(scanner);
                                    break;
                                }

                                System.out.println("But here are some rooms available from " + formatter.format(extendedCheckInDate) + " to " + formatter.format(extendedCheckOutDate));
                                extendedSearchRooms.forEach(System.out::println);
                            } else {
                                rooms.forEach(System.out::println);
                            }

                            System.out.println("\n Would you like to book a room? y/n");
                            String shouldBookRoom = scanner.next().toLowerCase();

                            if (shouldBookRoom.equals("no") || shouldBookRoom.equals("n")) {
                                launch(scanner);
                                break;
                            }

                            System.out.println("Do you have an account with us? y/n");
                            String accountExists = scanner.next().toLowerCase();

                            if (accountExists.equals("no") || accountExists.equals("n")) {
                                System.out.println("Please create an account \n");
                                launch(scanner);
                                break;
                            }

                            System.out.println("Enter Email format: name@domain.com");
                            String email = scanner.next();

                            System.out.println("What room number would you like to reserve");
                            String roomNumber = scanner.next();

                            Reservation reservation = hotelResource.bookARoom(email, hotelResource.getRoom(roomNumber), checkInDate, checkOutDate);

                            System.out.println();
                            System.out.println(reservation);
                            launch(scanner);
                            break;

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input: Re-enter reservation details");
                        } catch (ParseException e) {
                            System.out.println("Invalid date input: Re-enter reservation details");
                        }
                    }
                }
                case 2 -> {
                    while (true) {
                        try {
                            System.out.println("Enter Email format: name@domain.com");
                            String email = scanner.next();

                            Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);

                            reservations.forEach(System.out::println);
                            launch(scanner);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input: please try again");
                        }
                    }
                }
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
                case 5 -> {
                    scanner.close();
                    System.exit(0);
                }
            }
        }
    }
}
