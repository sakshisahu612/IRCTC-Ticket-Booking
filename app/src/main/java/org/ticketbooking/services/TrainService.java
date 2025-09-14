package org.ticketbooking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ticketbooking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    private Train train;
    private List<Train> trainList;
    private static final String TRAINS_PATH = "app/src/main/java/ticketbooking/localDb/trains.json";

    private ObjectMapper objectMapper = new ObjectMapper();


    public TrainService(){
    }

    public TrainService(Train t1) throws IOException {
        this.train = t1;
        File trains = new File(TRAINS_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }

    public List<Train> searchTrains(String source, String destination) throws IOException{
        return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    private Boolean validTrain(Train t, String source, String destination){
        List<String> stationInOrder = t.getStations();
        int srcIdx = stationInOrder.indexOf(source.toLowerCase());
        int destIdx = stationInOrder.indexOf(destination.toLowerCase());

        return srcIdx != -1 && destIdx != -1 && srcIdx < destIdx;
    }


}
