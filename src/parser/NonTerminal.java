import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NonTerminal implements Node {
    
    Set<Terminal> First = new HashSet<Terminal>();
    Set<Terminal> Follow = new HashSet<Terminal>();
    
    ArrayList<ProductionRule> productions = new ArrayList<ProductionRule>();

    @Override
    public Boolean isTerminal() {
    	return false;
    }
}
