package parser;

public class Parser {

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
        LeftFactoring factor=new LeftFactoring();
        factor.leftFactorAll();
         System.out.println();
         
        System.out.println("***After left factoring***");
         System.out.println();
        read.print();
        EliminateLeftRecursion eliminateLeftRecursion=new EliminateLeftRecursion();
        eliminateLeftRecursion.eliminateleftrecursion();
         System.out.println();
        System.out.println("***After eliminating left recursion***");
         System.out.println();
        read.print();
        System.out.println("\n\n");
        
        for(Node nt: ReadFile.nonTerminalsMap.values()) {
            ((NonTerminal)nt).printFirst();
        }
//        for(Terminal t:ReadFile.terminals){
//            System.out.println(t.getValue());
//        }
        for(NonTerminal nt:ReadFile.nonTerminals){
            System.out.println(nt.getName()+" "+nt.isIsStartSymbol());
        }
    }
    
}
