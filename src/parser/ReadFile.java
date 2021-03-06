/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author salma
 */
public class ReadFile {
    String file;
    ArrayList<String> nonTerminalsNames=new ArrayList<>();
    ArrayList<String> rules=new ArrayList<>();
   static HashMap<String, Node> nonTerminalsMap = new HashMap <>();
    static HashMap<String, Node> terminalsMap = new HashMap <>();
   static Set<String> terminalsNames=new HashSet<>();
   static ArrayList<Terminal> terminals=new ArrayList<>();
    static ArrayList<NonTerminal> nonTerminals=new ArrayList<>();
    NodeFactory factory=new NodeFactory();
    static boolean isTaken(String k){
        return nonTerminalsMap.containsKey(k);
    }
    void concatinateFile(){
    String fileName = "input.txt";
     String line ="";
         file=line;
        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line=bufferedReader.readLine())!= null) {
                file=file+=line;
                
            }
            file=file.trim();
        }  catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
}
   void split(){
       String[] lines = file.split("\\#");
       int index;
        for(int i=1;i<lines.length;i++)
        {  
            index=lines[i].indexOf('=');
        nonTerminalsNames.add(lines[i].substring(0, index).trim());
        rules.add(lines[i].substring(index+1, lines[i].length()).trim());
        }
    
   } 
   void fillMap(){
       for(String key:nonTerminalsNames){
           Node nT=factory.getNonTerminal();
           ((NonTerminal) nT).setName(key);
           if(nonTerminals.isEmpty())
               ((NonTerminal) nT).setIsStartSymbol(true);
           nonTerminals.add((NonTerminal) nT);
           nonTerminalsMap.put(key,nT);
       }
   }
   void fillProductions(){
       String rule,s,ss,n;
       String[] splitOr, splitSpace;
       ArrayList<Node> sequence;
       ArrayList<ProductionRule> productions;
       ProductionRule productionRule;
       Node nT;
        for(int i=0;i<nonTerminalsNames.size();i++){
            productions=new ArrayList<>();
            n=nonTerminalsNames.get(i);
            nT=nonTerminalsMap.get(n);
          rule=rules.get(i);
          splitOr=rule.split("\\|");
           for (String splitOr1 : splitOr) {
               sequence=new ArrayList<>();
               s = splitOr1;
               s= s.trim();
               splitSpace=s.split("\\s+");  
               for (String splitSpace1 : splitSpace) {
                   ss = splitSpace1;
                   ss= ss.trim();
                   if(ss.charAt(0)=='‘'){
                       ///////////////////////////
                       ss=ss.substring(1, ss.length()-1);
                        if(terminalsNames.add(ss)){
                          Node t=factory.getTerminal(ss);
                           sequence.add(t);
                            terminals.add((Terminal)t);
                            terminalsMap.put(ss, t);
                       }
                        else{
                            sequence.add(terminalsMap.get(ss));
                        }
                       ////////////////////////////
                       
                       
                      
                   }
                   else{
                     
                       sequence.add(nonTerminalsMap.get(ss));
                   }
               }
              productionRule=new ProductionRule();
              productionRule.setLHS(((NonTerminal)nT));
              productionRule.setSequence(sequence);
              productions.add(productionRule);
           }
           ((NonTerminal)nT).setProductions(productions);
       }
   }
   void print(){
      for (Node n : nonTerminals) {
      
      System.out.print(((NonTerminal)n).getName()+" = ");
      ArrayList<ProductionRule> a=((NonTerminal)n).productions;
       for(ProductionRule production:a){
            ArrayList<Node> n2=production.getSequence();
            for(Node n3:n2){
                if(n3.isTerminal())
                   System.out.print(((Terminal)n3).getValue()+" "); 
                else System.out.print(((NonTerminal)n3).getName()+" "); 
            }
            System.out.print("| "); 
       }
       System.out.println();
     }
   }
  
   void read(){
       concatinateFile();
       split();
       fillMap();
       fillProductions();
     // print();
   }
}
