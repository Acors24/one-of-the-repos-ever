package com.oneofever.commands;

import java.util.ArrayList;

public abstract class ICommand
{
/*
    public ArrayList<CommandInfo> commandInfo = new ArrayList<CommandInfo>();
    public abstract String[] next(String current);
    public abstract Integer tokens(String current);
*/
    public ArrayList<ArgGroup> argGroups = new ArrayList<ArgGroup>();
    public ArrayList<Object> looseArgs =  new ArrayList<Object>();
    public String looseArgsType = "";
    public Integer looseArgsNumber =0;

    public String[] argGroupsNames(){
        return argGroups.stream().map(x -> x.name).toArray(String[]::new);
    }

    public abstract String name();

    public abstract String description();

    public void print(){
        System.out.println("name: "+this.name()+" ArgGroups");
        for(ArgGroup a: argGroups){
            a.print();
        }
        System.out.print("loose: ");
        for(Object a: looseArgs){
            System.out.print(a.toString()+" ");
        }
        System.out.println();
    }
    public abstract void run(String[] tokens);
}
