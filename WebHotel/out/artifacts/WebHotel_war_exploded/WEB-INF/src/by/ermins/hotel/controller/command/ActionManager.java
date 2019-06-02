package by.ermins.hotel.controller.command;

import static by.ermins.hotel.controller.util.ControllerConstantStorage.*;

import by.ermins.hotel.controller.command.userActionImpl.DefaultUserAction;
//import by.ermins.hotel.controller.command.userActionImpl.ViewAllRooms;
import by.ermins.hotel.controller.command.userActionImpl.SearchRooms;
import by.ermins.hotel.controller.command.userActionImpl.ViewAllRooms;

public class ActionManager {
	public static UserAction defineAction(String action) {
		switch(action) {
		case ACTION_SEARCH_ROOMS:
			return new SearchRooms();
		case ACTION_ALL_ROOMS:
                return new ViewAllRooms();
		default:
			return new DefaultUserAction();
		}
	}
}