import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NonTerminal implements Node {
    
    private Set<Terminal> First = null;
    private Set<Terminal> Follow = new HashSet<>();
    
    ArrayList<ProductionRule> productions = new ArrayList<ProductionRule>();

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
    
    // Returns true if this non-terminal X has production rule X -> eps
    public Boolean goesToEps() {
        for(ProductionRule prod: productions) {
            if(prod.isEps())
                return true;
        }
        return false;
    }
}
