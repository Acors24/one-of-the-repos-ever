package com.oneofever.oneoftest.parser;

import com.oneofever.parser.Parser;
import com.oneofever.parser.ParseException;
import com.oneofever.commands.AbstractCommand;
import com.oneofever.commands.Version;
import com.oneofever.commands.SquareCommand;
import com.oneofever.commands.RectangleCommand;
import com.oneofever.shapes.Properties;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest
{
    Parser test;

    public ParserTest()
    {
        AbstractCommand version = new Version();
        AbstractCommand square = new SquareCommand();
        AbstractCommand rectangle = new RectangleCommand();
        LinkedList<AbstractCommand> list = new LinkedList<AbstractCommand>();
        list.add(version);
        list.add(square);
        list.add(rectangle);
        test = new Parser(list);
    }

    public boolean compareAbstractCommands(AbstractCommand a, AbstractCommand b){
        if(a.name()!=b.name()) return false;
        if(a.looseArgsType!=b.looseArgsType) return false;
        if(a.looseArgs.size()!=b.looseArgs.size()) return false;
        if(a.argGroups.size()!=b.argGroups.size()) return false;

        for(int i=0;i<a.argGroups.size();i++){
            if(a.argGroups.get(i).contents.size()!=b.argGroups.get(i).contents.size()) return false;
        }
        return true;
    }

    @Test
    @DisplayName("Parses Version command correctly")
    void testValidParseVersion() throws ParseException
    {
        AbstractCommand rett = new Version();
        assertTrue(compareAbstractCommands(test.parse(new String[]{"version"}), rett));
    }


    @Test
    @DisplayName("Throws exception when failing to parse version")
    void testThrowsParseVersion() {
        ParseException thrown = assertThrows(
           ParseException.class,
           () -> test.parse(new String[]{"version","aaaaa"}));
        assertTrue(thrown.getMessage().contains("Unexpected argument:"));
    }

    @Test
    @DisplayName("Parses Square command correctly")
    void testValidParseSquare() throws ParseException
    {
        AbstractCommand rett = new SquareCommand();
        rett.argGroups.get(0).contents.add("8");
        // assertEquals(test.parse(new String[]{"square","side","8"}), null);
        assertTrue(compareAbstractCommands(test.parse(new String[]{"square","side","8"}),rett));
    }

    @Test
    @DisplayName("Throws exception when failing to parse square")
    void testThrowsParseSquare() {
        ParseException thrown = assertThrows(
           ParseException.class,
           () -> test.parse(new String[]{"square","side","1","aaaaa"}));
        assertTrue(thrown.getMessage().contains("Unexpected argument:"));
    }

    @Test
    @DisplayName("Throws exception when failing to parse square, wrong argtype")
    void testThrowsParseSquare2() {
        ParseException thrown = assertThrows(
           ParseException.class,
           () -> test.parse(new String[]{"square","side","aaaaaaaaaa","aaaaa"}));
        assertTrue(thrown.getMessage().contains("wrong command groupargument:"));
    }

    @Test
    @DisplayName("Parses Rectangle command correctly")
    void testValidParseRectangle() throws ParseException
    {
        AbstractCommand rett = new RectangleCommand();
        rett.argGroups.get(0).contents.add("8");
        rett.argGroups.get(0).contents.add("9");
        // assertEquals(test.parse(new String[]{"square","side","8"}), null);
        assertTrue(compareAbstractCommands(test.parse(new String[]{"rectangle","side","8","side","9"}),rett));
    }

    @DisplayName("Parses Rectangle command correctly")
    void testValidParseRectangle2() throws ParseException
    {
        AbstractCommand rett = new RectangleCommand();
        rett.argGroups.get(1).contents.add("8");
        rett.argGroups.get(2).contents.add("9");
        // assertEquals(test.parse(new String[]{"square","side","8"}), null);
        assertTrue(compareAbstractCommands(test.parse(new String[]{"rectangle","diagonal","8","area","9"}),rett));
    }

    @Test
    @DisplayName("Throws exception when failing to parse square, wrong argtype")
    void testThrowsParseRectangle() {
        ParseException thrown = assertThrows(
           ParseException.class,
           () -> test.parse(new String[]{"rectangle","side","aaaaaaaaaa","aaaaa"}));
        assertTrue(thrown.getMessage().contains("wrong command groupargument:"));
    }

    @Test
    @DisplayName("Throws exception when failing to parse square, wrong argtype")
    void testThrowsParseRectangle2() {
        ParseException thrown = assertThrows(
           ParseException.class,
           () -> test.parse(new String[]{"rectangle","side","1","1","1"}));
        assertTrue(thrown.getMessage().contains("wrong amount of loose args:"));
    }

    @Test
    @DisplayName("Throws exception when failing to parse square, wrong argtype")
    void testThrowsParseRectangle3() {
        ParseException thrown = assertThrows(
           ParseException.class,
           () -> test.parse(new String[]{"rectangle","side","1"}));
        assertTrue(thrown.getMessage().contains("Too few groups"));
    }

}
