package com.oneofever.parser;

import com.oneofever.commands.AbstractCommand;

public class ParseException extends Exception
{
    AbstractCommand what;

    public ParseException(String s, AbstractCommand fail)
    {
        super(s);
        what = fail;
    }

    public AbstractCommand getCommand()
    {
        return what;
    }
}
