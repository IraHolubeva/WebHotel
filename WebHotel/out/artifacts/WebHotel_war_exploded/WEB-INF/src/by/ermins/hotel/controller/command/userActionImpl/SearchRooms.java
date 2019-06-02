package by.ermins.hotel.controller.command.userActionImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ermins.hotel.controller.command.UserAction;
import by.ermins.hotel.dao.RoomsDao;
import by.ermins.hotel.dao.roomsDaoImpl.RoomsDaoImpl;
import by.ermins.hotel.model.SearchRoomsService;
import by.ermins.hotel.model.Service;
import by.ermins.hotel.model.entity.GuestRoom;
import by.ermins.hotel.view.ViewRooms;

public class SearchRooms implements UserAction {

    private Date checkIn;
    private Date checkOut;

    private int guestsAmount;
    private int price;

    @Override
    public void performAction(HttpServletRequest req, HttpServletResponse resp) {

        setSearchParam(req, resp);

        if (isValid(checkIn, checkOut)) {
            Service roomsSearcher = new SearchRoomsService();
            roomsSearcher.search(checkIn, checkOut, price, guestsAmount, req, resp);
        }else {
            System.out.println("Invalid date input!");
        }
    }


    private void setSearchParam(HttpServletRequest req, HttpServletResponse resp) {

        String paramGuestsAmount = req.getParameter("guests_amount");
        String paramPrice = req.getParameter("price");

        guestsAmount = !paramGuestsAmount.equals("") ? Integer.parseInt(paramGuestsAmount) : 0;
        price = !paramPrice.equals("") ? Integer.parseInt(paramPrice) : 0;

        setStayDates(req, resp);
    }

    //TODO duty method !REMOVE!
    private void checkSearchCriteria() {
        System.out.println(checkIn);
        System.out.println(checkOut);
        System.out.println(guestsAmount);
        System.out.println(price);

    }

    private void setStayDates(HttpServletRequest req,
                              HttpServletResponse resp) {

        String pattern = "MM/dd/yyyy";
        String checkInParam = req.getParameter("check-in_date");
        String checkOutParam = req.getParameter("check-out_date");

        try {
            checkIn = !checkInParam.equals("") ?
                    new SimpleDateFormat(pattern).parse(checkInParam) : null;
            checkOut = !checkOutParam.equals("") ?
                    new SimpleDateFormat(pattern).parse(checkOutParam) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean isValid(Date checkIn, Date checkOut) {
        Date currentDate = new Date(System.currentTimeMillis());
        System.out.println(currentDate);
        return (checkIn.getDate() >= currentDate.getDate()) && checkIn.before(checkOut);
    }
}
