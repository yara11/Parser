package parser;
import java.util.Set;

public interface Node {
    public String string="";
    public Boolean isTerminal();
    public Set<Terminal> getFirst();
   
}
