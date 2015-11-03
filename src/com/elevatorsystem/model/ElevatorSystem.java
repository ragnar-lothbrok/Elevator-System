package com.elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {

    private static volatile ElevatorSystem elevatorSystem;
    List<Elevator> elevators = null;
    private Integer numOfFloors = null;

    private ElevatorSystem() {
    }

    public static ElevatorSystem getElevatorSystem(Integer numOfElevator, Integer numOfFloors) {
        if (elevatorSystem == null) {
            synchronized (ElevatorSystem.class) {
                if (elevatorSystem == null) {
                    elevatorSystem = new ElevatorSystem();
                    elevatorSystem.elevators = new ArrayList<Elevator>(numOfElevator);
                    for(int i=0;i<numOfElevator;i++){
                        elevatorSystem.elevators.add(new Elevator(i+1, numOfFloors));
                    }
                    elevatorSystem.numOfFloors = numOfFloors;
                }
            }
        }
        return elevatorSystem;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public Integer getNumOfFloors() {
        return numOfFloors;
    }

}
