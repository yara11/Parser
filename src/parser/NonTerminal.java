package parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NonTerminal implements Node {
    
    private Set<Terminal> First = null;
   // private Set<Terminal> Follow = new HashSet<>();
    private Set<Terminal> Follow = null;
    ArrayList<ProductionRule> productions = new ArrayList<ProductionRule>();
    private Boolean isStartSymbol;
    private NonTerminal ProductionLHS;
    
    @Override
    public Boolean isTerminal() {
    	return false;
    }
    
    @Override
    public Set<Terminal> getFirst() {
        // Avoid re-computation
        if(First != null) return First;
        
        First = new HashSet<>();
        
        for(ProductionRule prod: productions) {
            ArrayList<Node> seq = prod.getSequence();
            for(Node node: seq) {
                // Either this is the first node, or the preceding nodes
                // go to epsilon
                
                // Compute FIRST of this node
                First.addAll(node.getFirst());
                // If this node goes to epsilon, continue to the next node
                // if (X -> Y1Y2..Yn and Yj = a and Y1..j-1->eps)
                // then a is in FIRST(X)
                if(node.isTerminal() || !((NonTerminal) node).goesToEps())
                    break;
            }
        }
        return First;
    }
    //////first without epsilon to cmpute the follow
    ///////////////////////function 3'alat msh 3arfa mofida wala la2
//    public ArrayList<Node>FirstNoEps(ArrayList<Node>first){
//    ArrayList<Node> FirstNoEps = new ArrayList<Node>();
//    FirstNoEps.addAll(first);
//    for(Node node:FirstNoEps){
//    if("\\L".equals(((Terminal)node).getValue())){
//    FirstNoEps.remove(node);
//    }
//    }
//    return FirstNoEps;
//    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //////////////Follow Computing
    //@Override
    public Set<Terminal> getFollow(){
    if(Follow !=null) return Follow;
    Follow=new HashSet<>();

    if(this.isStartSymbol()){
        Terminal dollar=new Terminal("$");
        Follow.add(dollar);
    }
    for(ProductionRule prod:productions){
        ArrayList<Node>seq=prod.getSequence();
        for(int i=0; i<seq.size()-1; i++){
                //flag to check if the NonTerminal is a start symbol to add $ in the follow list
                NonTerminal curNode=(NonTerminal)seq.get(i);
                NonTerminal NextNode=(NonTerminal)seq.get(i+1);
                
                Set <Terminal> FirstNoEps= new HashSet<Terminal>(NextNode.getFirst());
                    
                    for(Terminal ter:FirstNoEps){
                        if(ter.isEps())
                            FirstNoEps.remove(ter);
                    }
                    curNode.getFollow().addAll(FirstNoEps);
                    
//                    if(node==seq.get(seq.size()-1)||NextNode.goesToEps()){
//                        Follow.addAll(prod.getLHS().getFollow());
//                    }
        }
        for(int i = seq.size()-1; i>= 0; i--) {
            Node cur = seq.get(i);
            
            if(cur.isTerminal() || !((NonTerminal)cur).goesToEps())
                break;
        }
    }
   return Follow;
    }
    

    public void setStartSymbol(Boolean StartSymbol) {
        this.isStartSymbol = StartSymbol;
    }

    public Boolean isStartSymbol() {
        return isStartSymbol;
    }
    
    // Returns true if this non-terminal X has production rule X -> eps
    public Boolean goesToEps() {
        for(ProductionRule prod: productions) {
            if(prod.isEps())
                return true;
        }
        return false;
    }
//     public Boolean FirstgoesToEps(Set<Terminal>first) {
//        for(Terminal t: first) {
//          //  if(t.isEps())//msh 3arfa sa7 wala 3'alat momken akteb
//                if(t.isEps())
//                return true;
//        }
//        return false;
//    }
}
