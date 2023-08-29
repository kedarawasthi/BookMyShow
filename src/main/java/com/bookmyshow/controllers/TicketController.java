package com.bookmyshow.controllers;

import com.bookmyshow.dtos.BookTicketRequestDto;
import com.bookmyshow.dtos.BookTicketResponseDto;
import com.bookmyshow.exceptions.BookTicketRequestValidationException;
import com.bookmyshow.exceptions.InvalidUserException;
import com.bookmyshow.exceptions.SeatsAlreadyBookedException;
import com.bookmyshow.models.Ticket;
import com.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto bookTicketRequestDto){
        try{
            validateRequest(bookTicketRequestDto);
            Ticket ticket= ticketService.bookTicket(bookTicketRequestDto.getShowSeatIds(),bookTicketRequestDto.getUserId());
            return BookTicketResponseDto.getSuccessResponse(ticket);
        }catch (BookTicketRequestValidationException | SeatsAlreadyBookedException | InvalidUserException e){
            return BookTicketResponseDto.getFailureResponse(e.getMessage());
        }
        catch(Exception e) {
            return BookTicketResponseDto.getFailureResponse(e.getMessage());
        }
    }

    private void validateRequest(BookTicketRequestDto bookTicketRequestDto) throws BookTicketRequestValidationException {
        int userId=bookTicketRequestDto.getUserId();
        List<Integer> showSeatIds=bookTicketRequestDto.getShowSeatIds();
        if(userId<0){
            throw new BookTicketRequestValidationException("Invalid User id");
        }
        if(showSeatIds == null){
            throw new BookTicketRequestValidationException("No Seats selected");
        }
        if(showSeatIds.size() == 0){
            throw new BookTicketRequestValidationException("Atleast one seat have to be selected");
        }
        if(showSeatIds.size() > 10 ){
            throw new BookTicketRequestValidationException("More than 10 seats cannot be selected");
        }
    }
}
