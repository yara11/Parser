/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author salma
 */
public class LeftFactoring {
   static String quotation="'";
    NodeFactory factory=new NodeFactory();
    ArrayList<Node> newNonTerminals=new ArrayList();
    public boolean isFactored(NonTerminal nT){
        ArrayList<ProductionRule> productions=nT.productions;
        ArrayList<ProductionRule> duplicates = new ArrayList<>();
        Set<ProductionRule> ProductionRuleSet = new TreeSet<>(new ProductionRuleComparator());
for(ProductionRule p : productions)
{
    if(!ProductionRuleSet.add(p))
    {
        duplicates.add(p);
    }
}
if(duplicates.isEmpty())
    return true;
    else
    return false;

    }
    void leftFactor(NonTerminal nT){
         ArrayList<ProductionRule> productions=nT.productions;
         
         ArrayList<ProductionRule> copy = new ArrayList<>();
         ArrayList<ProductionRule> newProductions = new ArrayList<>();
         ProductionRule newProductionRule=new ProductionRule();
         ArrayList<Node> newSequence = new ArrayList<>();
        for(ProductionRule j :productions ){
           copy.add(j);
       
        }
        ArrayList<ProductionRule> duplicates = new ArrayList<>();
        Set<ProductionRule> ProductionRuleSet = new TreeSet<>(new ProductionRuleComparator());
for(ProductionRule p : copy)
{
    if(!ProductionRuleSet.add(p))
    {
        duplicates.add(p);
        productions.remove(p);
        
       
    }
}
for(ProductionRule p : ProductionRuleSet){
    Node n=p.getSequence().get(0);
    Node m=duplicates.get(0).getSequence().get(0);
    if(n.isTerminal()&&m.isTerminal()){
        if(((Terminal)n).getValue().equals(((Terminal)n).getValue()))
        { duplicates.add(p);
          productions.remove(p);
        }
    }else if(!n.isTerminal()&&!m.isTerminal()){
        if(((NonTerminal)n).getName().equals(((NonTerminal)n).getName()))
        { duplicates.add(p);
          productions.remove(p);
        }
    }
}
   Node duplicate=duplicates.get(0).getSequence().get(0);
   newSequence.add(duplicate); 
   
   Node newNode=factory.getNonTerminal();
   
   ((NonTerminal)newNode).setName(quotation+((NonTerminal)nT).getName()+quotation);
   newSequence.add(newNode);
   newProductionRule.setSequence(newSequence);
   productions.add(0, newProductionRule);
   for(ProductionRule p : duplicates)
{
    p.getSequence().remove(0);
    if(p.getSequence().isEmpty()){
        Node epsilon=factory.getTerminal(Literals.EPS.toString());
        p.getSequence().add(epsilon);
    }
}
   ((NonTerminal)newNode).setProductions(duplicates);
   newNonTerminals.add(newNode);
    }
    void leftFactorAll(){
        int counter=0;
        for (String key : ReadFile.nonTerminalsMap.keySet()) {
      Node nonTerminal= ReadFile.nonTerminalsMap.get(key);
      if(isFactored((NonTerminal)nonTerminal))
      {  
          counter++;
          continue;
       
      }
      else{
          leftFactor((NonTerminal)nonTerminal);
          
      }
     }
        for(Node n:newNonTerminals){
            ReadFile.nonTerminalsMap.put( ((NonTerminal)n).getName(), ((NonTerminal)n));
            ReadFile.nonTerminals.add(((NonTerminal)n));
        }
        for(NonTerminal nT:ReadFile.nonTerminals){
            for(ProductionRule p:nT.productions){
                p.setLHS(nT);
            }
        }
        newNonTerminals.clear();
        if(counter!=ReadFile.nonTerminalsMap.size()){
            leftFactorAll();
            quotation+="'";
        }
            
    }
}
