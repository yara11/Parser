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
public class EliminateLeftRecursion {
   // char c='a';
    NodeFactory factory=new NodeFactory();
    ArrayList<Node> newNonTerminals=new ArrayList();
    void eliminateleftrecursion(){
        for (String key : ReadFile.nonTerminal.keySet()) {
            Node nonTerminal= ReadFile.nonTerminal.get(key);
            ArrayList<ProductionRule> productions=((NonTerminal)nonTerminal).productions;
            ArrayList<ProductionRule> copy = new ArrayList<>();
            ProductionRule LRProduction=null;
            for(ProductionRule j :productions ){
           copy.add(j);
        }
           for(ProductionRule j :copy ){
               Node n=j.getSequence().get(0);
            if(!n.isTerminal()){
                if(((NonTerminal)n).getName().equals(key)){
                    LRProduction=j;
                    productions.remove(j);
                    break;
                }
            }
        } 
           if(LRProduction!=null){
           Node newNode=factory.getNonTerminal();
           while(ReadFile.isTaken(Character.toString(LeftFactoring.c))){
      LeftFactoring.c++;
   }
   ((NonTerminal)newNode).setName(Character.toString(LeftFactoring.c));
   LeftFactoring.c++;
   if(!productions.isEmpty()){
   for(ProductionRule j :productions ){
          j.getSequence().add(newNode);
        }
   } else{
       ProductionRule p=new  ProductionRule ();
       ArrayList<Node> m=new ArrayList<>();
       m.add(newNode);
       p.setSequence(m);
       productions.add(p);
   }
           LRProduction.getSequence().remove(0);
           LRProduction.getSequence().add(newNode);
           ArrayList<ProductionRule> newProductions = new ArrayList<>();
           newProductions.add(LRProduction);
            ProductionRule eps=new  ProductionRule ();
            ArrayList<Node> epsSequence = new ArrayList<>();
            Node epsilon=factory.getTerminal(Literals.EPS.toString());
            epsSequence.add(epsilon);
            eps.setSequence(epsSequence);
            newProductions.add(eps);
           ((NonTerminal)newNode).setProductions(newProductions);
           newNonTerminals.add(newNode);
           }
        }
       
        for(Node n:newNonTerminals){
            ReadFile.nonTerminal.put( ((NonTerminal)n).getName(), ((NonTerminal)n));
        }
    }
}
