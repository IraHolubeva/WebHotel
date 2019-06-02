package by.ermins.hotel.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserAction {
	
	public void performAction(HttpServletRequest req, HttpServletResponse resp);
	//public void sendResultOnView(HttpServletRequest req, HttpServletResponse resp);	
}
