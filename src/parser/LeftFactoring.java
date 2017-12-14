/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;

/**
 *
 * @author salma
 */
public class LeftFactoring {
    char c='A';
    void leftFactor(){
        for (String key : ReadFile.nonTerminal.keySet()) {
      Node nonTerminal= ReadFile.nonTerminal.get(key);
      ArrayList<ProductionRule> productionRule=((NonTerminal)nonTerminal).productions;
      for (int i=0;i<productionRule.size();i++){
            
       }
     }
    }
    
    
}
