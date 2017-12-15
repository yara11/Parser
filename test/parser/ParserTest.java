/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yomnabarakat
 */
public class ParserTest {

    public ParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        //fill the table 
        Parser.Table[0][0] = "T M";
        Parser.Table[0][1] = "";
        Parser.Table[0][2] = "";
        Parser.Table[0][3] = "T M";
        Parser.Table[0][4] = "";
        Parser.Table[0][5] = "";
        Parser.Table[1][0] = "";
        Parser.Table[1][1] = "+ T M";
        Parser.Table[1][2] = "";
        Parser.Table[1][3] = "";
        Parser.Table[1][4] = "\\L";
        Parser.Table[1][5] = "\\L";
        Parser.Table[2][0] = "F N";
        Parser.Table[2][1] = "";
        Parser.Table[2][2] = "";
        Parser.Table[2][3] = "F N";
        Parser.Table[2][4] = "";
        Parser.Table[2][5] = "";
        Parser.Table[3][0] = "";
        Parser.Table[3][1] = "\\L";
        Parser.Table[3][2] = "* F N";
        Parser.Table[3][3] = "";
        Parser.Table[3][4] = "\\L";
        Parser.Table[3][5] = "\\L";
        Parser.Table[4][0] = "id";
        Parser.Table[4][1] = "";
        Parser.Table[4][2] = "";
        Parser.Table[4][3] = "( E )";
        Parser.Table[4][4] = "";
        Parser.Table[4][5] = "";

        //fill the hashmap for nonTerminals
        Parser.NonTerminals.put("E", 0);
        Parser.NonTerminals.put("M", 1);
        Parser.NonTerminals.put("T", 2);
        Parser.NonTerminals.put("N", 3);
        Parser.NonTerminals.put("F", 4);

        //fill the Hashmap for terminals
        Parser.Terminals.put("id", 0);
        Parser.Terminals.put("+", 1);
        Parser.Terminals.put("*", 2);
        Parser.Terminals.put("(", 3);
        Parser.Terminals.put(")", 4);
        Parser.Terminals.put("$", 5);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of pushIntoStack method, of class Parser.
     */
    @Test
    public void testPushIntoStack() {
        System.out.println("pushIntoStack");
        String production = "id";
        Parser instance = new Parser();
        instance.pushIntoStack(production);

    }

    /**
     * Test of get_row method, of class Parser.
     */
    @Test
    public void testGet_row() {
        System.out.println("get_row");
        String name = "E";
        Parser instance = new Parser();
        int expResult = 0;
        int result = instance.get_row(name);
        assertEquals(expResult, result);

    }

    /**
     * Test of get_column method, of class Parser.
     */
    @Test
    public void testGet_column() {
        System.out.println("get_column");
        String name = "id";
        Parser instance = new Parser();
        int expResult = 0;
        int result = instance.get_column(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of insert_$_sign method, of class Parser.
     */
    @Test
    public void testInsert_$_sign() {
        System.out.println("insert_$_sign");
        String input = "id + id";
        String expResult = "id + id $";
        String result = Parser.insert_$_sign(input);
        assertEquals(expResult, result);

    }

    /**
     * Test of isANonTerminal method, of class Parser.
     */
    @Test
    public void testIsANonTerminal() {
        System.out.println("isANonTerminal");
        String symbol = "id";
        Parser instance = new Parser();
        boolean expResult = false;
        boolean result = instance.isANonTerminal(symbol);
        assertEquals(expResult, result);

    }

    /**
     * Test of parse method, of class Parser.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        String input_string = "id + id";
        String start_symbol = "E";
        Parser instance = new Parser();
        instance.parse(input_string, start_symbol);

    }

}
