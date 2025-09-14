package org.ticketbooking.entities;

import java.util.List;

public class User {
    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketsBooked;                 // ab tak kitni tickets book ki hai user ne
    private String userId;

    public User(String name1, String password, String hashPassword, List<Ticket> ticketsBooked, String userId){
        this.name = name1;
        this.password = password;
        this.hashPassword = hashPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    public User(){}         // default constructor

    public String getName(){
        return name;
    }
     public String getPassword(){
        return password;
     }

     public String getHashPassword(){
        return hashPassword;
     }

     public List<Ticket> getTicketsBooked(){
        return ticketsBooked;
     }

     public void printTickets(){
        for(int i = 0; i < ticketsBooked.size(); i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
     }

     public void setName(String name){
        this.name = name;
     }

     public void setPassword(String password){
        this.password = password;
     }

     public void setHashPassword(String hashPassword){
        this.hashPassword = hashPassword;
     }

     public void setTicketsBooked(List<Ticket>ticketsBooked){
        this.ticketsBooked = ticketsBooked;
     }

     public void setUserId(String userId){
        this.userId = userId;
     }
}
