package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Parser {

//    public static void main(String[] args) {
//       
//       
//    }
    //parse table
    //TO DO!
    static String Table[][] = new String[100][100];
    /////////////
    static Stack<String> stack = new Stack<String>();
    //static ArrayList<String> input = new ArrayList<String>();
    static ArrayList<String> output = new ArrayList<String>();
    public static HashMap<String, Integer> Terminals = new HashMap<>();
    public static HashMap<String, Integer> NonTerminals = new HashMap<>();

    void pushIntoStack(String production) {
        String[] splited = production.split("\\s+");
        for (int i = splited.length - 1; i >= 0; i--) {
            stack.push(splited[i]);
        }
    }

    int get_row(String name) {
        return NonTerminals.get(name);

    }

    int get_column(String name) {
        return Terminals.get(name);
    }

    static String insert_$_sign(String input) {
        String dollar_sign = " $";
        input = input.concat(dollar_sign);
        return input;
    }

    boolean isANonTerminal(String symbol) {
        if (NonTerminals.containsKey(symbol)) {
            return true;
        } else {
            return false;
        }
    }

    String getToken(String input) {
        if (input.contains(" ")) {
            input = input.substring(0, input.indexOf(" "));
        }
        return input;
    }

    void parse(String input_string, String start_symbol) {
        String input_string_new = input_string;
        String terminated_input = insert_$_sign(input_string_new);
        //input.add(terminated_input);
        String string_in;
        String symbol = start_symbol;

        //push $ sign and start symbol.
        stack.push("$");
        stack.push(symbol);

        //store all tokens in a array
        String[] splited = terminated_input.split(" ");
        int index = 0;
        string_in = splited[index];
        while (!stack.empty()) {
            System.out.println("hi");
            //if match character with a terminal in the stack
            if (string_in.equals(stack.peek())) {
                String temp = string_in;
                if (++index < splited.length) {
                    string_in = splited[index];
                }
                if (temp.equals("$")) {
                    output.add("accept");
                } else {
                    output.add("");
                }
                stack.pop();

                //if the the stack top is a non terminal, pop the terminal and push its production suitable.
            } else if (isANonTerminal(stack.peek())) {
                int row = get_row(stack.peek());
                int column = get_column((string_in));
                String production = Table[row][column];

                //if production goes to epsilon
                if (production.equals(Literals.EPS.toString())) {
                    stack.pop();
                    output.add(production);
                } //empty production
                else if (production.equals("")) {
                    output.add("Error:(illegal" + stack.peek() + ")" + " – discard " + string_in);
                    if (++index < splited.length) {
                        string_in = splited[index];
                    }

                } //production is marked as synch
                else if (production.equals("synch")) {
                    stack.pop();
                    output.add("");
                } else {
                    output.add(production);
                    stack.pop();
                    pushIntoStack(production);

                }
            }//terminal but doesn't match with the input
            else {
                output.add("error missing " + string_in);
                stack.pop();

            }

        }

    }
}
