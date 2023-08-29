package com.bookmyshow.dtos;

import com.bookmyshow.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDto {
    private Ticket ticket;
    private Response response;

    public static BookTicketResponseDto getSuccessResponse(Ticket ticket){
        BookTicketResponseDto bookTicketResponseDto=new BookTicketResponseDto();
        bookTicketResponseDto.setResponse(Response.getSuccessResponse("Ticket Successfully created"));
        bookTicketResponseDto.setTicket(ticket);
        return bookTicketResponseDto;
    }

    public static BookTicketResponseDto getFailureResponse(String message){
        BookTicketResponseDto bookTicketResponseDto=new BookTicketResponseDto();
        bookTicketResponseDto.setResponse(Response.getFailureResponse(message));
        return bookTicketResponseDto;
    }

}
