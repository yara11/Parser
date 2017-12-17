/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;

/**
 *
 * @author yomnabarakat
 */
public class Main {
       /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ReadFile read=new ReadFile();
        read.read();
        System.out.println();
        System.out.println("***After read***");
         System.out.println();
        read.print();
        //LeftFactoring factor=new LeftFactoring();
        //factor.leftFactorAll();
         //System.out.println();
         
        //System.out.println("***After left factoring***");
         //System.out.println();
        //read.print();
        //EliminateLeftRecursion eliminateLeftRecursion=new EliminateLeftRecursion();
        //eliminateLeftRecursion.eliminateleftrecursion();
         //System.out.println();
        //System.out.println("***After eliminating left recursion***");
         //System.out.println();
        //read.print();
        NodeFactory factory=new NodeFactory();
        Node dollar=factory.getTerminal("$");
        ReadFile.terminals.add((Terminal)dollar);
        ReadFile.terminalsMap.put("$", dollar);
        System.out.println();
         System.out.println("***First***");
         System.out.println();
        for(Node nt: ReadFile.nonTerminals) {
            ((NonTerminal)nt).printFirst();
        }
        System.out.println();
        System.out.println("***Follow***");
        System.out.println();

        for(Node nt: ReadFile.nonTerminals) {
            ((NonTerminal)nt).printFollow();
        }
        
        ParsingTable.constructParsingTable();
        System.out.println();
        System.out.println("***Parsing Table***");
        ParsingTable.printParsingTable();
         
        
        System.out.println("***Parsing result***");
        Parser.parse("int id ;", "METHOD_BODY");
        for(int i=0;i<Parser.output.size();i++)
        {
            System.out.println(Parser.output.get(i));
        }
    }
}

    

