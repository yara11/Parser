package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Parser {

    public static HashMap<String, String> tokens = new HashMap<>();
    static Stack<String> stack = new Stack<String>();
    static ArrayList<String> output = new ArrayList<String>();

    static void readTokens() throws IOException {
        String file = "tokens.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                tokens.put(splited[0].trim(), splited[1].trim());

            }
        }
    }

    static void pushIntoStack(String production) {
        String[] splited = production.split("\\s+");
        for (int i = splited.length - 1; i >= 0; i--) {
            stack.push(splited[i]);
        }
    }

    static int get_row(String name) {
        return ParsingTable.nonTerminalsMap.get(name);

    }

    static int get_column(String name) {
        return ParsingTable.terminalsMap.get(name);
    }

    static String insert_$_sign(String input) {
        String dollar_sign = " $";
        input = input.concat(dollar_sign);
        return input;
    }

    static boolean isANonTerminal(String symbol) {
        if (ParsingTable.nonTerminalsMap.containsKey(symbol)) {
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

    static String convert_to_tokens(String lexeme) {
        String output = "";
        String[] splited = lexeme.split("\\s+");
        for (int i = 0; i < splited.length; i++) {
            String s = tokens.get(splited[i]);
            output += s + " ";

        }
        return output.trim();
    }

    static void parse(String input_string, String start_symbol) {
        String input_string_new = convert_to_tokens(input_string);
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
                String production = ParsingTable.parsingTable[row][column];

                //if production goes to epsilon
                if (production.equals(Literals.EPS.toString())) {
                    stack.pop();
                    output.add(production);
                } //empty production
                else if (production.equals("null")) {
                    output.add("Error:(illegal" + stack.peek() + ")" + " – discard " + string_in);
                    if (++index < splited.length) {
                        string_in = splited[index];
                    }

                } //production is marked as synch
                else if (production.equals("synch")) {
                    stack.pop();
                    output.add("");
                } else {
                    output.add(stack.peek() + "--> " + production);
                    stack.pop();
                    pushIntoStack(production);

                }
            }//terminal but doesn't match with the input
            else {
                output.add("error missing " + string_in);
                stack.pop();

            }

        }
        //clear output to be ready for another 
        for (int i = 0; i < output.size(); i++) {
            System.out.println(Parser.output.get(i));
        }
        output.clear();
    }
}
