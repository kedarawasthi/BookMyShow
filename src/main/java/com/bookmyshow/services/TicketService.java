package com.bookmyshow.services;

import com.bookmyshow.exceptions.InvalidUserException;
import com.bookmyshow.exceptions.SeatsAlreadyBookedException;
import com.bookmyshow.models.Ticket;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws SeatsAlreadyBookedException, InvalidUserException;
}
