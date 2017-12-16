import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NonTerminal implements Node {
    
    private Set<Terminal> First = null;
   // private Set<Terminal> Follow = new HashSet<>();
    private Set<Terminal> Follow = null;
    ArrayList<ProductionRule> productions = new ArrayList<ProductionRule>();
    private Boolean StartSymbol;
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
    for(ProductionRule prod:productions){
        ArrayList<Node>seq=prod.getSequence();
        for(int i=0;i<seq.size();i++){
                //flag to check if the NonTerminal is a start symbol to add $ in the follow list
                NonTerminal node=new NonTerminal();
                node=(NonTerminal)seq.get(i);
                if(node.getStartSymbol()){
                    Terminal dollar=new Terminal("$");
                      Follow.add(dollar);
                
                }
              
                    NonTerminal NextNode=new NonTerminal();
                    Set <Terminal> FirstNoEps= null;
                    NextNode=(NonTerminal)seq.get(i+1);
                    for(Terminal ter:NextNode.getFirst()){
                     if(!ter.isEps()){
                    Follow.addAll(NextNode.getFirst());}
                      //else if(ter.getValue()=="\\L"){
                     else if(ter.isEps()){    
                       // FirstNoEps=new HashSet()<>;
                         FirstNoEps.addAll(NextNode.getFirst());
                         FirstNoEps.remove(ter);
                         Follow.addAll(FirstNoEps);
                      }
                     
                          //NextNode.getFirst().remove(ter);
                    //Follow.addAll(NextNode.getFirst().remove(ter));}
                    }
//                    if(i==seq.get){
//                    }
                    if(node==seq.get(seq.size()-1)||NextNode.FirstgoesToEps(NextNode.getFirst())){
                    Follow.addAll(prod.getLHS().getFollow());
                    }
                    
                   
                
        }
    }
   return Follow;
    }
    

    public void setStartSymbol(Boolean StartSymbol) {
        this.StartSymbol = StartSymbol;
    }

    public Boolean getStartSymbol() {
        return StartSymbol;
    }
    
    // Returns true if this non-terminal X has production rule X -> eps
    public Boolean goesToEps() {
        for(ProductionRule prod: productions) {
            if(prod.isEps())
                return true;
        }
        return false;
    }
     public Boolean FirstgoesToEps(Set<Terminal>first) {
        for(Terminal t: first) {
          //  if(t.isEps())//msh 3arfa sa7 wala 3'alat momken akteb
                if("//L".equals(t.getValue()))
                return true;
        }
        return false;
    }
}
