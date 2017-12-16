package parser;
import java.util.ArrayList;

public class ProductionRule {
    private ArrayList<Node> sequence = new ArrayList<>();
    private NonTerminal LHS;

    public NonTerminal getLHS() {
        return LHS;
    }

    public void setLHS(NonTerminal LHS) {
        this.LHS = LHS;
    }
    public ArrayList<Node> getSequence() {
        return sequence;
    }
    
    // Returns true if this rule is X -> eps
    public Boolean isEps() {
        Node first = sequence.get(0);
        return first.isTerminal() && ((Terminal)first).isEps();
    }
}
