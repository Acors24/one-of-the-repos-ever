/* (C)2024 - one-of-the-teams-ever */
package com.oneofever.commands;

import com.oneofever.shapes.Properties;
import java.util.ArrayList;

public abstract class AbstractCommand {

    public ArrayList<ArgGroup> argGroups = new ArrayList<ArgGroup>();
    public ArrayList<Object> looseArgs = new ArrayList<Object>();
    public Integer groupNumber = 0;
    public String looseArgsType = "";
    public Integer looseArgsNumber = 0;

    public String[] argGroupsNames() {
        return argGroups.stream().map(x -> x.name).toArray(String[]::new);
    }

    public abstract String name();

    public abstract String description();

    // public void print()
    // {
    //     System.out.println("name: "+this.name()+" ArgGroups");
    //     for(ArgGroup a: argGroups){
    //         a.print();
    //     }
    //     System.out.print("loose: ");
    //     for(Object a: looseArgs){
    //         System.out.print(a.toString()+" ");
    //     }
    //     System.out.println();
    // }

    public abstract void run();

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        for (ArgGroup arg : argGroups) {
            a.append(arg + " ");
        }
        return a.toString().strip();
    }

    public String usage() {
        return null;
    }

    public Properties toProperties() {
        return null;
    }

    public AbstractCommand newObject() {
        try {
            return this.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            return null;
        }
    }
}
