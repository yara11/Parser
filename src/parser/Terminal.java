
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import parser.Literals;

public class Terminal implements Node {
    
    private String value;
    private Boolean isEpsilon = false;
    
    public Terminal(String value) {
    	this.value = value;
        if(this.value.equals(Literals.EPS)){
            this.isEpsilon = true;
        }
    }
    @Override
    public Boolean isTerminal() {
    	return true;
    }

    @Override
    public String getValue() {
        return value;
    }
    
    
    
    public Boolean isEps() {
        return this.isEpsilon;
    }

    @Override
    public Set<Terminal> getFirst() {
        return new HashSet<>(Arrays.asList(this));
    }
    
    @Override
    public String toString() {
        return value;
    }
}
