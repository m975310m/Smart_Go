package com.example.user.smart_go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DataCenter
{
    String id = "";
    int point = 0;
    //public static String id="";
    public void setID(String id)
    {
        this.id = id;
    }
    public String getID()
    {
        return id;
    }

    public void setPoint(int point){this.point = point;}
    public int getPoint() {return point;}
}
