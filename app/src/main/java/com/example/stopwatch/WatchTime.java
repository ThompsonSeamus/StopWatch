package com.example.stopwatch;

public class WatchTime {
    private long startTime; //the time of the internal phone clock
    private long timeUpdate; //the time of stored time + timeInMilliseconds for a real time.
    private long storedTime; //the time saved on stop (in case of resume)

    public WatchTime() {
        //the "L" is to convert the variable into a Long Literal
        startTime = 0L;
        timeUpdate = 0L;
        storedTime = 0L;
    }

    //reset
    public void reset(){
        //the "L" is to convert the variable into a Long Literal
        startTime = 0L;
        timeUpdate = 0L;
        storedTime = 0L;
    }

    //getters
    public long getStartTime() {
        return startTime;
    }

    public long getTimeUpdate() {
        return timeUpdate;
    }

    public long getStoredTime() {
        return storedTime;
    }

    //setters
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setTimeUpdate(long timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public void setStoredTime(long storedTime) {
        this.storedTime = storedTime;
    }

    //in case people press start and stop multiple times
    public void addStoredTime(long milliseconds){
        this.storedTime += milliseconds;
    }
}
