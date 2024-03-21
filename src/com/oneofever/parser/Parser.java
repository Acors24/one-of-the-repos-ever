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

    public Properties parse(String[] toParse) throws ParseException
    {
        ArrayList<Double> sides = new ArrayList<Double>();
        ArrayList<Double> heights = new ArrayList<Double>();
        ArrayList<Double> diagonals = new ArrayList<Double>();
        Double area = -1.0;

        int i = 0;
        String activeModifier = null;
        ICommand activeCommand = null;
        System.out.println(toParse);
        Boolean end = false;
        for (String a : toParse)
        {
            System.out.println(activeModifier);
            if (end) throw new ParseException("Unexpected argument: "+a);
            if (activeModifier == null)
            {
                Optional<ICommand> match = commands.stream().filter(σ -> σ.name().equals(a)).findFirst();
                if (match.isPresent()) {
                    activeCommand = match.get();
                    activeModifier = a;
                } else {
                   throw new ParseException("Unrecognized command: "+a);
                }

                Integer tokens = activeCommand.tokens(activeModifier);
                if (tokens == null) i--;
                else i = tokens;

                if (activeCommand.next(activeModifier) == null || tokens == 0) end = true;
                continue;
            }
            final String g=activeModifier;
            String[] possibilities = activeCommand.next(activeModifier);
            //for(String s : possibilities) System.out.println(s);
            if (Arrays.stream(possibilities).map(ę -> {return g+ę.toUpperCase();}).anyMatch(a::equals))
            {
                activeModifier = a;
            }
            continue;
        }
        //TODO write checks, update props
        if(sides.isEmpty() && heights.isEmpty() && diagonals.isEmpty() && area < 0) return null;
        return Properties.create((Double[])sides.toArray(), (Double[])heights.toArray(), (Double[])diagonals.toArray(), area);
    }
}
