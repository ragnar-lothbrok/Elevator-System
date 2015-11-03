package com.elevatorsystem.service;

import java.util.List;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.State;
import com.elevatorsystem.exception.ElevatorException;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.ElevatorSystem;

public class ElevatorService {

    private ElevatorSystem elevatorSystem;

    private static volatile ElevatorService elevatorService;

    private ElevatorService() {

    }

    public static ElevatorService getElevatorService() {
        if (elevatorService == null) {
            synchronized (ElevatorSystem.class) {
                if (elevatorService == null) {
                    elevatorService = new ElevatorService();
                }
            }
        }
        return elevatorService;
    }

    public void init(Integer numOfElevator, Integer numOfFloors) {
        this.elevatorSystem = ElevatorSystem.getElevatorSystem(numOfElevator, numOfFloors);
    }

    public ElevatorSystem getElevatorSystem() {
        return elevatorSystem;
    }

    public Elevator getElevator(Direction direction, Integer floor, Elevator elevator) throws ElevatorException {
        Elevator resultantElevator = null;
        Elevator stoppedElevator = null;
        if (direction != null) {
            if (direction.equals(Direction.UP)) {
                if (floor == this.elevatorSystem.getNumOfFloors()) {
                    throw new ElevatorException("Invalid direction.");
                }
                List<Elevator> elevators = this.elevatorSystem.getElevators();
                for (Elevator tempElevator : elevators) {
                    if (!tempElevator.getState().equals(State.NOTWORKING)) {
                        if (tempElevator.getDirection().equals(Direction.UP)
                                && tempElevator.getCurrentFloor().getPosition() < floor) {
                            if (resultantElevator == null) {
                                resultantElevator = tempElevator;
                            } else if (resultantElevator.getCurrentFloor().getPosition() < tempElevator
                                    .getCurrentFloor().getPosition()) {
                                resultantElevator = tempElevator;
                            }
                        } else if (tempElevator.getCurrentFloor().getPosition() == floor
                                && tempElevator.getState().equals(State.STOPPED)) {
                            stoppedElevator = tempElevator;
                        }
                    }
                }
                Elevator tempElevator = elevators.get(elevator.getElevatorNumber() - 1);
                if (resultantElevator == null && tempElevator.getState().equals(State.STOPPED)) {
                    resultantElevator = tempElevator;
                }

                if (resultantElevator == null && stoppedElevator != null) {
                    resultantElevator = tempElevator;
                }

            } else {
                if (floor == 1) {
                    throw new ElevatorException("Invalid direction.");
                }
                List<Elevator> elevators = this.elevatorSystem.getElevators();
                for (Elevator tempElevator : elevators) {
                    if (!tempElevator.getState().equals(State.NOTWORKING)) {
                        if (tempElevator.getDirection().equals(Direction.DOWN)
                                && tempElevator.getCurrentFloor().getPosition() > floor) {
                            if (resultantElevator == null) {
                                resultantElevator = tempElevator;
                            } else if (resultantElevator.getCurrentFloor().getPosition() > tempElevator
                                    .getCurrentFloor().getPosition()) {
                                resultantElevator = tempElevator;
                            }
                        } else if (tempElevator.getCurrentFloor().getPosition() == floor
                                && tempElevator.getState().equals(State.STOPPED)) {
                            stoppedElevator = tempElevator;
                        }
                    }
                }

                Elevator tempElevator = elevators.get(elevator.getElevatorNumber() - 1);
                if (resultantElevator == null && tempElevator.getState().equals(State.STOPPED)) {
                    resultantElevator = tempElevator;
                }

                if (resultantElevator == null && stoppedElevator != null) {
                    resultantElevator = tempElevator;
                }
            }
        }
        if (resultantElevator != null) {
            if(resultantElevator.getCurrentFloor().getPosition() < floor){
                resultantElevator.setDirection(Direction.UP);
            }else{
                resultantElevator.setDirection(Direction.DOWN);
            }
            resultantElevator.setCurrentFloor(floor);
            resultantElevator.setState(State.RUNNING);
        }
        return resultantElevator;
    }
}
