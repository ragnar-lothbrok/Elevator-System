package com.elevatorsystem.model;

import java.io.Serializable;
import java.util.Arrays;

import com.elevatorsystem.enums.Direction;
import com.elevatorsystem.enums.State;

public class Elevator implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer elevatorNumber;
    private Direction direction = Direction.UP;
    private State state = State.STOPPED;
    private Float currentWeight;
    private Floor floor[];
    private Floor currentFloor;

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = this.floor[currentFloor-1];
    }

    public Elevator(Integer elevatorNumber, Integer numOfFloors) {
        this.elevatorNumber = elevatorNumber;
        this.floor = new Floor[numOfFloors];
        for (int i = 0; i < numOfFloors; i++) {
            this.floor[i] = new Floor(i + 1);
        }
        currentFloor = new Floor(0);
    }

    public Integer getElevatorNumber() {
        return elevatorNumber;
    }

    public void setElevatorNumber(Integer elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public State getState() {
        return state;
    }

    public Floor[] getFloor() {
        return floor;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Elevator(Integer elevatorNumber) {
        super();
        this.elevatorNumber = elevatorNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elevatorNumber == null) ? 0 : elevatorNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Elevator other = (Elevator) obj;
        if (elevatorNumber == null) {
            if (other.elevatorNumber != null)
                return false;
        } else if (!elevatorNumber.equals(other.elevatorNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Elevator [elevatorNumber=" + elevatorNumber + ", direction=" + direction + ", state=" + state
                + ", currentWeight=" + currentWeight + ", floor=" + Arrays.toString(floor) + ", currentFloor="
                + currentFloor + "]";
    }

    public void addFloor(Integer floor) {
        this.floor[floor - 1].setIsDestination(true);
    }
    
    public void removeFloor(Integer floor) {
        this.floor[floor - 1].setIsDestination(false);
    }

}
