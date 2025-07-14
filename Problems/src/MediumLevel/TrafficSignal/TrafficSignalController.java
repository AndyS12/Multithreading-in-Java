package MediumLevel.TrafficSignal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficSignalController {

    ReentrantLock lock = new ReentrantLock();
    Condition northCondition = lock.newCondition();
    Condition southCondition = lock.newCondition();
    Condition eastCondition = lock.newCondition();
    Condition westCondition = lock.newCondition();

    Direction currentGreen = Direction.NORTH;

    public void signal(Direction direction){
        for(int i=0; i<3; i++){
            try {
                lock.lock();
                while(currentGreen!=direction){
                    getCurrentCondition(direction).await();
                }
                System.out.println("" + direction + " signal is green");
                currentGreen = getNextDirection(direction);
                getCurrentCondition(currentGreen).signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public Direction getNextDirection(Direction direction){
        switch(direction){
            case NORTH:
                return Direction.EAST;
            case SOUTH:
                return Direction.WEST;
            case EAST:
                return Direction.SOUTH;
            case WEST:
                return Direction.NORTH;
        }
        return null;
    }

    public Condition getCurrentCondition(Direction direction){
        switch(direction){
            case NORTH:
                return northCondition;
            case SOUTH:
                return southCondition;
            case EAST:
                return eastCondition;
            case WEST:
                return westCondition;
        }
        return null;
    }
}
