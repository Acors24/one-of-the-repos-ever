package com.oneofever.parser;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Arrays;

import com.oneofever.shapes.Properties;
import com.oneofever.commands.ICommand;

public class Parser
{
    LinkedList<ICommand> commands = null;

    public Parser(LinkedList<ICommand> commands)
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

    //na razie niech zwraca wypelniony icommand, potem pewnie liste icommand
    public ICommand parse(String[] toParse) throws ParseException
    {

        // String activeModifier = null;
        ICommand activeCommand = null;
        Integer activeGroupIndex = -1;

        System.out.println(toParse);
        Boolean end = false; //is command finished (we might remove this in the future if we will need more that one command in line)

        Integer looseCnt = 0; //counter for how much more looseargs needed
        Integer groupCnt = 0; // counter for how much more needed in curr group
        Integer groups = 0; // number of groups in curr command
        //we might need number of groups needed or we can leave it up to commands to deal with it
        // for now leave it up commands
        System.out.println("asdsadsadsa");
        for (String a : toParse)
        {
            if(activeCommand != null ) System.out.println("acname: "+activeCommand.name()+ "acg: "+activeGroupIndex);
            if (end) throw new ParseException("Unexpected argument: "+a);
            if (activeCommand== null)
            {
                Optional<ICommand> match = commands.stream().filter(σ -> σ.name().equals(a)).findFirst();
                if (match.isPresent()) {
                    activeCommand = match.get();
                    // activeModifier = a;
                } else {
                   throw new ParseException("Unrecognized command: "+a);
                }

                looseCnt = activeCommand.looseArgsNumber;
                groupCnt = 0;
                groups = activeCommand.argGroups.size();

                if (groups+activeCommand.looseArgsNumber == 0) end = true;
                continue;
            }
            if(activeGroupIndex<0){
                activeGroupIndex = Arrays.asList(activeCommand.argGroupsNames()).indexOf(a);

                if(activeGroupIndex>=0){
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
                activeCommand.argGroups.get(activeGroupIndex).contents.add(a);
                groupCnt--;
                if(groupCnt==0){
                    if(looseCnt==0) end=true;
                    else{
                        activeGroupIndex = -1;
                    }
                }
            }
            else{
                throw new ParseException("wrong command groupargument: "+activeCommand.name()+"arggrop:"+activeCommand.argGroups.get(activeGroupIndex).name+" arg: "+a);
            }

        }
        System.out.println("out of for");
        System.out.println(activeCommand.name());
        System.out.println("agidx:"+activeGroupIndex+" groups:"+groups+" loosecnt:"+looseCnt+" groupCnt:"+groupCnt);
        activeCommand.print();

        return activeCommand;
    }
}
