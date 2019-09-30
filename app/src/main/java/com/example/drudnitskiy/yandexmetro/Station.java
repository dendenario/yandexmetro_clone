package com.example.drudnitskiy.yandexmetro;

import java.util.ArrayList;

public class Station {
    public String Name;
    public int X;
    public int Y;
    public int _id;

    public ArrayList<Link> Links;

    public Station(String name, int X, int Y, int id)
    {
        Name = name;
        this.X = X;
        this.Y = Y;
        _id = id;

        Links = new ArrayList<Link>();
//        Links.add(new Link(1,10));
//        Links.add(new Link(2,20));
//        Links.add(new Link(3,30));
    }

}

