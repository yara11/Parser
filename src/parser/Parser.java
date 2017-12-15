package parser;

public class Parser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ReadFile read=new ReadFile();
        read.read();
        LeftFactoring factor=new LeftFactoring();
        factor.leftFactorAll();
        read.print();
    }
    
}
