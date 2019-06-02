package by.ermins.hotel.controller.command.userActionImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ermins.hotel.controller.command.UserAction;

public class DefaultUserAction implements UserAction{

	@Override
	//TODO change to more informative option
	public void performAction(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("incorrect action");
	}



}
