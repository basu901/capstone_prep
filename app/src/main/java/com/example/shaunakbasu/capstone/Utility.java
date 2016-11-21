package com.example.shaunakbasu.capstone;

/**
 * Created by shaunak basu on 15-11-2016.
 */
public class Utility {

    public static String getBMIDetails(float val){
        String bmi;

        if(val<18.5)
            bmi="Under Weight";
        if(val>=18.5&&val<25)
            bmi="Normal";
        if(val>=25&&val<=30)
            bmi="Over Weight";
        else
            bmi="Obese";

        return bmi;
    }

    public static boolean hasOnlyAlphabets(String s){
        if(s.matches("[a-zA-Z]+")&& s.length()>2)
            return true;
        else
            return false;
    }

}
