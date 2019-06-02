package by.ermins.hotel.dao.roomsDaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.ermins.hotel.dao.RoomsDao;
import by.ermins.hotel.dao.utilSql.DataBaseConnection;
import by.ermins.hotel.model.entity.GuestRoom;

public class RoomsDaoImpl implements RoomsDao {

    @Override
    public List<GuestRoom> getRooms(Date checkIn,
                                    Date checkOut, int price,
                                    int guestsAmount, String searchType) {

        List<GuestRoom> guestRooms = null;

        ResultSet result = getResultSet();

        switch (searchType) {
            case "ALL_ROOMS":
                guestRooms = getAllRooms(result, checkIn, checkOut);
                break;
            case "DATE":
                guestRooms = getRoomsByDate(result, checkIn, checkOut);
                break;
            case "DATE_AND_PRICE":
                guestRooms = getRoomsByDateAndPrice(result, checkIn, checkOut, price);
                break;
            case "DATE_AND_GUESTS":
                guestRooms = getRoomsByDateAndGuests(result, checkIn, checkOut, guestsAmount);
                break;
            case "PRICE_AND_GUESTS":
                guestRooms = getRoomsByPriceAndGuests(result, price, guestsAmount, checkIn, checkOut);
                break;
            case "PRICE":
                guestRooms = getRoomsByPrice(result, price, checkIn, checkOut);
                break;
            case "GUESTS":
                guestRooms = getRoomsByGuests(result, guestsAmount, checkIn, checkOut);
                break;
            case "ALL_PARAMETERS":
                guestRooms = getRoomsByAllParam(result, checkIn, checkOut, price, guestsAmount);
                break;
        }

        return guestRooms;
    }


    private List<GuestRoom> getAllRooms(ResultSet res, Date checkIn, Date checkOut) {
        List<GuestRoom> guestRooms = new ArrayList<GuestRoom>();
        boolean isFree;
        try {
            while (res.next()) {
                isFree = isFreeOnDates(checkIn, checkOut, res);
                addRoomToList(guestRooms, res, isFree);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    private List<GuestRoom> getRoomsByDate(ResultSet res, Date checkIn, Date checkOut) {
        List<GuestRoom> guestRooms = new ArrayList<GuestRoom>();
        boolean isFree;
        try {
            while (res.next()) {

                isFree = isFreeOnDates(checkIn, checkOut, res);

                if (isFree) {
                    addRoomToList(guestRooms, res, isFree);
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    private List<GuestRoom> getRoomsByDateAndPrice(ResultSet res, Date checkIn, Date checkOut, int price) {
        List<GuestRoom> guestRooms = new ArrayList<>();
        boolean isFree;

        try {
            while (res.next()) {

                isFree = isFreeOnDates(checkIn, checkOut, res);

                int pricePerDayDB = res.getInt("pricePerDay");
                int stayDays = checkOut.getDate() - checkIn.getDate();
                int fullPrice = pricePerDayDB * stayDays;

                if (fullPrice <= price) {
                    if (isFree) {
                        addRoomToList(guestRooms, res, isFree);
                    }
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    private List<GuestRoom> getRoomsByDateAndGuests(ResultSet res, Date checkIn, Date checkOut, int guestsAmount) {
        List<GuestRoom> guestRooms = new ArrayList<GuestRoom>();
        boolean isFree;
        try {
            while (res.next()) {
                int guestsAmountDB = res.getInt("guests");
                isFree = isFreeOnDates(checkIn, checkOut, res);
                if (guestsAmountDB >= guestsAmount) {
                    if (isFree) {
                        addRoomToList(guestRooms, res, isFree);
                    }
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    private List<GuestRoom> getRoomsByPriceAndGuests(ResultSet res, int price, int guestsAmount,
                                                     Date checkIn, Date checkOut) {
        List<GuestRoom> guestRooms = new ArrayList<GuestRoom>();
        boolean isFree;
        try {
            while (res.next()) {
                int pricePerDay = res.getInt("pricePerDay");
                int guestsAmountDB = res.getInt("guests");

                isFree = isFreeOnDates(checkIn, checkOut, res);

                if (pricePerDay <= price && guestsAmountDB <= guestsAmount) {
                    addRoomToList(guestRooms, res, isFree);
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    private List<GuestRoom> getRoomsByPrice(ResultSet res, int price, Date checkIn, Date checkOut) {
        List<GuestRoom> guestRooms = new ArrayList<GuestRoom>();
        boolean isFree;
        try {
            while (res.next()) {
                int priceDB = res.getInt("pricePerDay");
                isFree = isFreeOnDates(checkIn, checkOut, res);
                if (priceDB <= price) {
                    addRoomToList(guestRooms, res, isFree);
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    private List<GuestRoom> getRoomsByGuests(ResultSet res, int guestsAmount, Date checkIn, Date checkOut) {
        List<GuestRoom> guestRooms = new ArrayList<>();
        boolean isFree;
        try {
            while (res.next()) {
                int guestsAmountDB = res.getInt("guests");

                isFree = isFreeOnDates(checkIn, checkOut, res);

                if (guestsAmountDB >= guestsAmount) {
                    addRoomToList(guestRooms, res, isFree);
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

    private List<GuestRoom> getRoomsByAllParam(ResultSet res, Date checkIn,
                                               Date checkOut, int price, int guestsAmount) {

        List<GuestRoom> guestRooms = new ArrayList<GuestRoom>();
        boolean isFree;

        try {
            while (res.next()) {

                int guestsAmountDB = res.getInt("guests");
                int pricePerDayDB = res.getInt("pricePerDay");
                int fullPrice;

                if (checkOut.getDate() > checkIn.getDate()) {
                    fullPrice = pricePerDayDB * (checkOut.getDate() - checkIn.getDate());
                } else{
                    //TODO MAKE THE RIGHT EXPRESSION!!!!
                    fullPrice = pricePerDayDB * (checkIn.getDate());
                }

                isFree = isFreeOnDates(checkIn, checkOut, res);

                if (guestsAmountDB >= guestsAmount && fullPrice <= price) {
                    if (isFree) {
                        addRoomToList(guestRooms, res, true);
                    }
                }
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestRooms;
    }

//    @Override
//    public void bookingRoom() {
//        // TODO Auto-generated method stub
//
//    }

    private void addRoomToList(List<GuestRoom> guestRooms,
                               ResultSet res, boolean isFree) throws SQLException {

        int roomNumber = res.getInt("roomNUmber");
        int guests = res.getInt("guests");
        int floor = res.getInt("floor");
        double pricePerDay = res.getDouble("pricePerDay");

        guestRooms.add(new GuestRoom(roomNumber, floor, guests, pricePerDay, isFree));
    }


    private ResultSet getResultSet() {
        try {
            Connection con = DataBaseConnection.getDBConnection();
            Statement st = con.createStatement();
            return st.executeQuery("select * from hotelrooms");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isFreeOnDates(Date checkIn, Date checkOut, ResultSet res) throws SQLException {

        Date checkInDB = res.getDate("check-in");
        Date checkOutDB = res.getDate("check-out");

        if (checkInDB != null && checkOutDB != null) {
            if (checkIn != null && checkOut != null) {
                if ((checkIn.before(checkInDB) && checkOut.before(checkInDB)) ||
                        (checkIn.after(checkOutDB) && checkOut.after(checkOutDB))) {
                    return true;
                } else return false;
            } else {
                Date currentDate = new Date(System.currentTimeMillis());
                if (currentDate.before(checkInDB) || currentDate.after(checkOutDB)) {
                    return true;
                } else return false;
            }
        } else return true;
    }
}