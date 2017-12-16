package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ParsingTable {
    
    
    ////from reading the file////
    public static ArrayList<Terminal> terminals = new ArrayList<Terminal>();
    public static ArrayList<NonTerminal> nonTerminals = new ArrayList<NonTerminal>();
     //////////////////////
    
    public static String parsingTable[][] = new String[nonTerminals.size()][terminals.size()];
   
    public static HashMap<String, Integer> terminalsMap = new HashMap<>();
    public static HashMap<String, Integer> nonTerminalsMap = new HashMap<>();

    public static void constructParsingTable() {
        int id = 0;
        for (Terminal t : terminals) {
            terminalsMap.put(t.toString(), id++);
        }

        id = 0;
        for (NonTerminal t : nonTerminals) {
            nonTerminalsMap.put(t.toString(), id++);
        }

        
        for (NonTerminal nonterm : nonTerminals) {

            for (ProductionRule prod : nonterm.productions) {
               
                
                for (Terminal first : prod.getFirst()) {
                    int enter=0;
                    if (first.isEps()) {
                        //////from follow //////
                        Set<Terminal> nontermfollow = new HashSet<>();//= nonterm.getfollow()
                        ///////////////////////
                        for (Terminal follow : nontermfollow) {
                            
                            parsingTable[nonTerminalsMap.get(nonterm.toString())][terminalsMap.get(follow.toString())] = prod.toString();
                            enter=1;

                        }
                        
                    } else {
                        parsingTable[nonTerminalsMap.get(nonterm.toString())][terminalsMap.get(first.toString())] = prod.toString();
                         enter=1;
                    }
                    
                    if(enter==0){
                        ////////////from follow////////////////////
                         Set<Terminal> nontermfollow = new HashSet<>();//= nonterm.getfollow()
                         ///////////////////////////////////
                        for(Terminal follow : nontermfollow){
                            parsingTable[nonTerminalsMap.get(nonterm.toString())][terminalsMap.get(follow.toString())] = "sync";
                            
                        
                    }
                    
                            
                }                    
                           
                }
            }

        }
        
        
                    
                     
                     
                     
                     
                 }
                 
                 
                 
                 
             }    
        

    

   


