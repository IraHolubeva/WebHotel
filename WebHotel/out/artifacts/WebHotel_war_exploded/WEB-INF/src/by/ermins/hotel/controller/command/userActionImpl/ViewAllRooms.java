
package by.ermins.hotel.controller.command.userActionImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.ermins.hotel.controller.command.UserAction;
import by.ermins.hotel.model.SearchRoomsService;
import by.ermins.hotel.model.Service;


public class ViewAllRooms implements UserAction {


    @Override
    public void performAction(HttpServletRequest req, HttpServletResponse resp) {

        Service roomsSearcher = new SearchRoomsService();

        roomsSearcher.search(null, null, 0, 0, req, resp);
    }

}

