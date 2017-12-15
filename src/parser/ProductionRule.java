
import java.util.ArrayList;

public class ProductionRule {
    private String name;
    private ArrayList<Node> sequence = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
