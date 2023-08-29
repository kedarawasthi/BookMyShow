package com.bookmyshow.services;

import com.bookmyshow.exceptions.InvalidUserException;
import com.bookmyshow.exceptions.SeatsAlreadyBookedException;
import com.bookmyshow.models.*;
import com.bookmyshow.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {

    UserService userService;
    SeatService seatService;
    SeatTypeShowService seatTypeShowService;
    ShowSeatService showSeatService;
    TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(UserService userService, SeatService seatService, SeatTypeShowService seatTypeShowService, ShowSeatService showSeatService,TicketRepository ticketRepository) {
        this.userService = userService;
        this.seatService = seatService;
        this.seatTypeShowService = seatTypeShowService;
        this.showSeatService = showSeatService;
        this.ticketRepository=ticketRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws SeatsAlreadyBookedException,InvalidUserException{
        //get user from user repo
        Optional<User> userOptional=userService.findById(userId);
        if(userOptional.isEmpty()){
            throw new InvalidUserException("Invalid user login");
        }
        User user= userOptional.get();

        //get showSeats from showSeats repo with help of showSeats Ids
        List<ShowSeat> showSeats= showSeatService.findByIdAndSeatStatus_Available(showSeatIds);

        // validate if all retrived are avaialable, if yer then block, else throw error
        if(showSeats == null || showSeats.size()!= showSeatIds.size()){
            throw new SeatsAlreadyBookedException("The seats you are trying to book are already booked");
        }
        for(ShowSeat showSeat:showSeats){
            showSeat.setSeatStatus(SeatStatus.BLOCKED);
            showSeat.setUser(user);
            showSeatService.save(showSeat);
        }

        //get Show from first item from showSeats
        Show show=showSeats.get(0).getShow();

        //get seats from Seats repo with the help of seat ids
        List<Integer> seatIds = showSeats.stream().map(showSeat -> showSeat.getSeat().getId()).toList();
        List<Seat> seats = seatService.findByIdIn(seatIds);

        //get seatype show from Seatype show repo with help of showid
        List<SeatTypeShow> seatTypeShows = seatTypeShowService.findByShow(show.getId());
        Map<SeatType,Double> priceMap = new EnumMap<SeatType, Double>(SeatType.class);
        for(SeatTypeShow seatTypeShow:seatTypeShows){
            priceMap.put(seatTypeShow.getSeatType(),seatTypeShow.getPrice());
        }

        // calc total price for all seats
        double price=0.0d;
        for(ShowSeat showSeat: showSeats){
            price += priceMap.get(showSeat.getSeat().getSeatType());
        }

        // form ticket obj(show,seats,entry time,user,total price)
        Ticket ticket=new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setPrice(price);
        ticket.setSeats(seats);
        ticket.setTimeOfBooking(new Date());

        // return ticket object
        return ticket;
    }
}
