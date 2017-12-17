package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class ParsingTable {

    public static String parsingTable[][] = new String[ReadFile.nonTerminals.size()][ReadFile.terminals.size()];

    public static HashMap<String, Integer> terminalsMap = new HashMap<>();
    public static HashMap<String, Integer> nonTerminalsMap = new HashMap<>();

    public static void constructParsingTable() {

        int id = 0;
        for (Terminal t : ReadFile.terminals) {
            terminalsMap.put(t.toString(), id++);
        }

        id = 0;
        for (NonTerminal t : ReadFile.nonTerminals) {
            nonTerminalsMap.put(t.toString(), id++);
        }

        for (NonTerminal nonterm : ReadFile.nonTerminals) {

            for (ProductionRule prod : nonterm.productions) {

                for (Terminal first : prod.getFirst()) {

                    if (first.isEps()) {

                        for (Terminal follow : nonterm.getFollow()) {

                            parsingTable[nonTerminalsMap.get(nonterm.toString())][terminalsMap.get(follow.toString())] = prod.toString();

                        }

                    } else {
                        parsingTable[nonTerminalsMap.get(nonterm.toString())][terminalsMap.get(first.toString())] = prod.toString();

                    }

                }

            }

        }//end of for of nonterminals 

        for (int i = 0; i < ReadFile.nonTerminals.size(); i++) {
            for (int j = 0; j < ReadFile.terminals.size(); j++) {
                NonTerminal nontermObj = null;
                Terminal termObj = null;
                if (parsingTable[i][j] == null) {

                    for (Entry<String, Integer> entry : nonTerminalsMap.entrySet()) {
                        if (entry.getValue() == i) {
                            nontermObj = (NonTerminal) ReadFile.nonTerminalsMap.get(entry.getKey());

                        }
                    }

                    for (Entry<String, Integer> entry1 : terminalsMap.entrySet()) {
                        if (entry1.getValue() == j) {
                            termObj = (Terminal) ReadFile.terminalsMap.get(entry1.getKey());

                        }
                    }

                    for (Terminal follow : nontermObj.getFollow()) {
                        if (follow.toString().equals(termObj.toString())) {

                            parsingTable[i][j] = "sync";
                        }

                    }

                }

            }
        }

    }//end of construct 

    public static void printParsingTable() {
        System.out.print(String.format("%50s %50s ", " ", " "));

        for (int h = 0; h < ReadFile.terminals.size(); h++) {

            for (Entry<String, Integer> entry : terminalsMap.entrySet()) {
                if (entry.getValue() == h) {
                    System.out.print(String.format("%50s %50s ", entry.getKey(), " "));

                }
            }
        }

        System.out.println();

        for (int i = 0; i < ReadFile.nonTerminals.size(); i++) {
            for (Entry<String, Integer> entry : nonTerminalsMap.entrySet()) {
                if (entry.getValue() == i) {
                    System.out.print(String.format("%50s %50s ", entry.getKey(), " "));

                }
            }

            for (int j = 0; j < ReadFile.terminals.size(); j++) {

                System.out.print(String.format("%50s %50s ", parsingTable[i][j], " "));

            }

            System.out.println();

        }

    }//end of print parsing table

}//end of class
