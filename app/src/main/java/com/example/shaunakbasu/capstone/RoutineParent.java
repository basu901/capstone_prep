package com.example.shaunakbasu.capstone;

/**
 * Created by shaunak basu on 11-11-2016.
 */
public class RoutineParent {

    String routine_header;
    int ID;

    public RoutineParent(String routine_header,int ID){
        this.routine_header=routine_header;
        this.ID=ID;
    }

    public String getRoutine_header() {
        return routine_header;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRoutine_header(String routine_header) {
        this.routine_header = routine_header;
    }
}
