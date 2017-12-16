package parser;
import java.util.Set;

public interface Node {
    public Boolean isTerminal();
    public Set<Terminal> getFirst();
}
