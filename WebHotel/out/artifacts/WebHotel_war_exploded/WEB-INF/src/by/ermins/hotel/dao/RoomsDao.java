package by.ermins.hotel.dao;

import java.util.Date;
import java.util.List;

import by.ermins.hotel.model.entity.GuestRoom;

public interface RoomsDao {
	
	public List<GuestRoom> getRooms(Date checkIn,
									Date checkIut, int price,
									int guestsAmount, String searchType);
}
