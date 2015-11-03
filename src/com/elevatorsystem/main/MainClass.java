package com.elevatorsystem.main;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.State;
import com.elevatorsystem.exception.ElevatorException;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.ElevatorSystem;
import com.elevatorsystem.service.ElevatorService;

public class MainClass {

    public static void main(String[] args) throws ElevatorException {

        ElevatorService elevatorService = ElevatorService.getElevatorService();
        elevatorService.init(10, 10);
        suffleLiftsTest1(elevatorService.getElevatorSystem());
        System.out.println(elevatorService.getElevator(Direction.DOWN, 5, new Elevator(1)));
    }

    public static void suffleLiftsTest1(ElevatorSystem elevatorSystem) {
        Elevator fourThElevator = elevatorSystem.getElevators().get(4);
        fourThElevator.setCurrentFloor(6);
        fourThElevator.setDirection(Direction.DOWN);
        fourThElevator.setState(State.RUNNING);
    }
}
