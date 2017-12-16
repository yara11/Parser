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
    static char c='A';
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
   newSequence.add(duplicates.get(0).getSequence().get(0));
   Node newNode=factory.getNonTerminal();
   while(ReadFile.isTaken(Character.toString(c))){
       c++;
   }
   ((NonTerminal)newNode).setName(Character.toString(c));
   c++;
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
        for (String key : ReadFile.nonTerminals.keySet()) {
      Node nonTerminal= ReadFile.nonTerminals.get(key);
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
            ReadFile.nonTerminals.put( ((NonTerminal)n).getName(), ((NonTerminal)n));
        }
        if(counter!=ReadFile.nonTerminals.size())
            leftFactorAll();
    }
    
    
}
