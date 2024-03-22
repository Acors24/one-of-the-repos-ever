package com.oneofever.parser;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Arrays;

import com.oneofever.shapes.Properties;
import com.oneofever.commands.AbstractCommand;

public class Parser
{
    LinkedList<AbstractCommand> commands = null;

    public Parser(LinkedList<AbstractCommand> commands)
    {
        this.commands = commands;
    }

    public static boolean isArgumentOfType(String argument, String type) {
        //na razie zakladam ze type to typ podstawowy
        try {
            switch (type) {
                case "Double":
                    Double.parseDouble(argument);
                    return true;
                case "String":
                // Always can parse a string
                    return true;
                case "Integer":
                    Integer.parseInt(argument);
                    return true;
                // jak bedziemy cos dodawac
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //na razie niech zwraca wypelniony AbstractCommand, potem pewnie liste AbstractCommand
    public AbstractCommand parse(String[] toParse) throws ParseException
    {

        // String activeModifier = null;
        AbstractCommand activeCommand = null;
        Integer activeGroupIndex = -1;

        System.out.println(toParse);
        Boolean end = false; //is command finished (we might remove this in the future if we will need more that one command in line)

        Integer looseCnt = 0; //counter for how much more looseargs needed
        Integer groupCnt = 0; // counter for how much more needed in curr group
        Integer groups = 0; // number of groups in curr command
        //we might need number of groups needed or we can leave it up to commands to deal with it
        // for now leave it up commands
        for (String a : toParse)
        {
            // if(activeCommand != null ) System.out.println("acname: "+activeCommand.name()+ "acg: "+activeGroupIndex);
            if (end) throw new ParseException("Unexpected argument: "+a);
            //we curretly dont hava a command to parse
            if (activeCommand== null)
            {
                Optional<AbstractCommand> match = commands.stream().filter(σ -> σ.name().equals(a)).findFirst();
                if (match.isPresent()) {
                    activeCommand = match.get();
                    // activeModifier = a;
                } else {
                   throw new ParseException("Unrecognized command: "+a);
                }

                looseCnt = activeCommand.looseArgsNumber;
                groupCnt = 0;
                groups = activeCommand.groupNumber;

                if (activeCommand.argGroups.size()+activeCommand.looseArgsNumber == 0) end = true;
                continue;
            }
            //we donst have an arggroup
            if(activeGroupIndex<0){
                activeGroupIndex = Arrays.asList(activeCommand.argGroupsNames()).indexOf(a);

                if(activeGroupIndex>=0){
                    if(groups==0){ //juz za duzo grup
                        throw new ParseException("too many groupargs: "+activeCommand.name()+" gr: "+a+" grcom: "+activeCommand.groupNumber);
                    }
                    //chce miec nowa grupe z nieuzupeniona poprzednia
                    if(groupCnt>0){
                        throw new ParseException("too few arguments in previous group: "+activeCommand.name()+" gr: "+a);
                    }
                    groups--;
                    groupCnt = activeCommand.argGroups.get(activeGroupIndex).number;
                }
                else{
                    if(looseCnt!=0){
                        //type parse
                        if(isArgumentOfType(a, activeCommand.argGroups.get(activeGroupIndex).argType)){
                            activeCommand.looseArgs.add(a);
                            looseCnt--;
                            if(looseCnt==0) end = true;
                        }
                        else{
                            throw new ParseException("wrong command argument: "+activeCommand.name()+" arg: "+a);
                        }

                    }
                    else{
                        throw new ParseException("wrong amount of loose args: "+activeCommand.name()+" arg: "+a);
                    }
                }
                continue;
            }
            //nie spawdzam czy grupa pelna bo wtedy end
            if(isArgumentOfType(a,activeCommand.argGroups.get(activeGroupIndex).argType)){
                if(groupCnt==0){
                    throw new ParseException("too many groupargs: "+activeCommand.name()+" group: "+activeCommand.argGroups.get(activeGroupIndex).name);
                }
                activeCommand.argGroups.get(activeGroupIndex).contents.add(a);
                groupCnt--;
                //tu trzeba zmienic bo potem moze byc wiecej niz jedna grupa
                if(groupCnt==0){
                    if(looseCnt==0 && groups==0) end=true;
                    else{
                        activeGroupIndex = -1;
                    }
                }
            }
            else{
                throw new ParseException("wrong command groupargument: "+activeCommand.name()+"arggrop:"+activeCommand.argGroups.get(activeGroupIndex).name+" arg: "+a);
            }

        }

        if(groups>0){
            throw new ParseException("Too few groups");
        }
        if(looseCnt>0){
            throw new ParseException("Too few loose arguments");
        }
        if(groupCnt>0){
            throw new ParseException("Too few arguments in a group: "+activeCommand.argGroups.get(activeGroupIndex).name);
        }
        //debug lines don't erase yet
        System.out.println("out of for");
        System.out.println(activeCommand.name());
        System.out.println("agidx:"+activeGroupIndex+" groups:"+groups+" loosecnt:"+looseCnt+" groupCnt:"+groupCnt);
        activeCommand.print();

        return activeCommand;
    }
}
