package org.ticketbooking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ticketbooking.entities.Train;
import org.ticketbooking.entities.User;
import org.ticketbooking.util.UserServiceutil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserBookingService{
    private User user;              // global level pe stored hai taki baki func usko use kar paye
    private List<User> userList;        // local db se sare users ko fetch karke store

    private static final String USERS_PATH = "app/src/main/java/ticketbooking/localDb/users.json";

    private ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void fetchInitialUsers() throws IOException{
        File usersFetch = new File(USERS_PATH);
        userList = objectMapper.readValue(usersFetch, new TypeReference<List<User>>() {});
    }
    private void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }
    public UserBookingService(User user1) throws IOException{             // constructor
        this.user = user1;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) &&
                    UserServiceutil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }
        catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings(){
        user.printTickets();
    }

    public Boolean bookTrainSeat(Train t, int row, int col){
        List<List<Integer>> seatsToPick = t.getSeats();
        if(seatsToPick.get(row).get(col) == 0){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }
        catch(IOException ex){
            return new ArrayList<>();
        }
    }

    //public Boolean cancelBooking(String ticketId){
        // TO COMPLETE
      //  return Boolean.FALSE;
    //}
}

// List<E> - generic datatype - E kya hai kisi ko nai pata
// run time pe jo chiz generic hoti hai usko resolve hum ni kr pate -> isliye we use TypeReference
// json file ko de serialize krne ke liye we use object mapper
// TypeReference hume Object mapper me use krna pdta hai chizo ko run time me resolve karne ke liye


// agar user hum nahi bhi find kar paaye toh Null Pointer na aaye --> use Optional<User>


// Cancel booking -> user apne pass global level pe stored hai
// ticketId hai apne pass -> ticket fetch karenge
// uss array me jake ticketId ko remove krna hai user ke ticket me se -- ticketsBooked , stream filter use kar skte ticketID pe
// fir user json file ko modify krna kyuki User ke rticket ko remove kiya hai
