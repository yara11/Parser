package parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NonTerminal implements Node {
    
    private Set<Terminal> First = null;
    private Set<Terminal> Follow = new HashSet<>();
    private String name;
    ArrayList<ProductionRule> productions = new ArrayList<ProductionRule>();

    @Override
    public Boolean isTerminal() {
    	return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ProductionRule> getProductions() {
        return productions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductions(ArrayList<ProductionRule> productions) {
        this.productions = productions;
    }
    
    @Override
    public Set<Terminal> getFirst() {
        // Avoid re-computation
        if(First != null) return First;
        
        First = new HashSet<>();
        
        for(ProductionRule prod: productions) {
            First.addAll(prod.getFirst());
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
    
    
    @Override
    public String toString() {
        return name;
    }

    
}
