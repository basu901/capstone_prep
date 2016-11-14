package com.example.shaunakbasu.capstone;

/**
 * Created by shaunak basu on 11-11-2016.
 */
public class RoutineChild {

    boolean checked;
    String details;

    public RoutineChild(boolean checked,String details){
        this.checked=checked;
        this.details=details;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
