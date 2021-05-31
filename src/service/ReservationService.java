package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReservationService {

    private final Map<String, IRoom> rooms = new TreeMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

    private ReservationService() {}

    private static ReservationService myInstance;

    public static ReservationService getInstance() {
        if (myInstance == null) {
            myInstance = new ReservationService();
        }
        return myInstance;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> reservedRooms = reservations.stream().map(Reservation::getRoom).collect(Collectors.toList());
        List<IRoom> unreservedRooms = rooms.values().stream().filter(room -> !reservedRooms.contains(room)).collect(Collectors.toList());
        List<IRoom> freeRoomsB4CheckinDate = reservations.stream().filter(reservation -> reservation.getCheckOutDate().before(checkInDate)).map(Reservation::getRoom).collect(Collectors.toList());
        Stream<IRoom> availableRoomsStream = Stream.concat(unreservedRooms.stream(), freeRoomsB4CheckinDate.stream());
        return availableRoomsStream.collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.stream().filter(reservation -> reservation.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    public void printAllReservation() {
        reservations.forEach(System.out::println);
    }
}
