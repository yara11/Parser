package parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductionRule {
    private NonTerminal LHS;
    private ArrayList<Node> sequence = new ArrayList<>();

    public NonTerminal getLHS() {
        return LHS;
    }

    public void setLHS(NonTerminal LHS) {
        this.LHS = LHS;
    }

    public void setSequence(ArrayList<Node> sequence) {
        this.sequence = sequence;
    }
    
    public ArrayList<Node> getSequence() {
        return sequence;
    }
    
    // Returns true if this rule is X -> eps
    public Boolean isEps() {
        Node first = sequence.get(0);
        return first.isTerminal() && ((Terminal)first).isEps();
    }
    
    @Override
    public String toString() {
        String ret = LHS + "->";
        for(Node n: sequence) {
            ret += n.toString();
        }
        return ret;
    }
    
    public Set<Terminal> getFirst() {
        Set<Terminal> first = new HashSet<>();
        for(Node node: sequence) {
            // Either this is the first node, or the preceding nodes
            // go to epsilon

            // Compute FIRST of this node
            first.addAll(node.getFirst());
            // If this node goes to epsilon, continue to the next node
            // if (X -> Y1Y2..Yn and Yj = a and Y1..j-1->eps)
            // then a is in FIRST(X)
            if(node.isTerminal() || !((NonTerminal) node).goesToEps())
                break;
        }
        return first;
    }
    
}
