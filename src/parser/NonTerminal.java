package parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NonTerminal implements Node {
    
    private Set<Terminal> First = null;

   // private Set<Terminal> Follow = new HashSet<>();
    private Set<Terminal> Follow = new HashSet<>();;
    ArrayList<ProductionRule> productions = new ArrayList<ProductionRule>();
    private Boolean isStartSymbol = false;
    private String name;
    
    @Override
    public Boolean isTerminal() {
    	return false;
    }

    public void setIsStartSymbol(boolean isStartSymbol) {
        this.isStartSymbol = isStartSymbol;
    }

    public boolean isIsStartSymbol() {
        return isStartSymbol;
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
    
    public Set<Terminal> getFollow() {
        return Follow;
    }
    
    public Set<Terminal> calcFollow(){
        if(this.isStartSymbol()){
            Terminal dollar=new Terminal("$");
            Follow.add(dollar);
        }
        for(NonTerminal nt: ReadFile.nonTerminals) {
            for(ProductionRule prod: nt.getProductions()) {
                ArrayList<Node> seq = prod.getSequence();
                for(int i = 0; i < seq.size(); i++) {
                    if(seq.get(i) != this)
                        continue;
                    if(i != seq.size()-1) {
                        Node next = seq.get(i+1);
                        Set <Terminal> nextFirst= new HashSet<Terminal>(next.getFirst());
                        this.Follow.addAll(nextFirst);
                    } else {
                        // flag
                        this.Follow.addAll(nt.getFollow());
                    }
                }
            }
        }

        for(Terminal n: Follow) {
            if(n.isEps()) {
                Follow.remove(n);
                break;
            }
        }

        for(ProductionRule prod:productions){
            ArrayList<Node>seq=prod.getSequence();
            for(int i = seq.size()-1; i>= 0; i--) {
                Node cur = seq.get(i);
                if(cur.isTerminal())
                    break;
                ((NonTerminal)cur).getFollow().addAll(Follow);
                if(!((NonTerminal)cur).goesToEps())
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
    
    public void printFirst() {
        this.getFirst();
        System.out.print("FIRST(" + this.toString()+"): { ");
        for(Terminal t: First) {
            System.out.print(t.toString() + ", ");
        }
        System.out.println("}");
    }
    public void printFollow() {
        this.calcFollow();
        System.out.print("FOLLOW(" + this.toString()+"): { ");
        for(Terminal t: Follow) {
            System.out.print(t.toString() + ", ");
        }
        System.out.println("}");
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
