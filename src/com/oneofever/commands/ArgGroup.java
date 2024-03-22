package com.oneofever.commands;

import java.util.ArrayList;

public class ArgGroup
{
    public ArrayList<Object> contents = new ArrayList<Object>();
    public String argType;
    public Integer number;
    public String name;
    public ArgGroup(String name, String argType, Integer number){
        this.name = name;
        this.argType = argType;
        this.number =  number;
    }

    public void print(){
        System.out.print(name+" cont: ");
        for(Object a: contents){
            System.out.print(a.toString()+" ");
        }
        System.out.println();
    }
}
