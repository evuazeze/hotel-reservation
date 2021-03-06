package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

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
        List<IRoom> reservedRooms = new ArrayList<>();
        for (Reservation reservation: reservations) {
            reservedRooms.add(reservation.getRoom());
        }
        List<IRoom> unreservedRooms = new ArrayList<>();
        for (IRoom room: rooms.values()) {
            if (!reservedRooms.contains(room)) {
               unreservedRooms.add(room);
            }
        }
        List<IRoom> freeRoomsB4CheckinDate = new ArrayList<>();
        for (Reservation reservation: reservations) {
            if (reservation.getCheckOutDate().before(checkInDate)) {
                freeRoomsB4CheckinDate.add(reservation.getRoom());
            }
        }

        List<IRoom> availableRoomsStream = new ArrayList<>();
        availableRoomsStream.addAll(unreservedRooms);
        availableRoomsStream.addAll(freeRoomsB4CheckinDate);

        return availableRoomsStream;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.stream().filter(reservation -> reservation.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    public void printAllReservation() {
        reservations.forEach(System.out::println);
    }
}
