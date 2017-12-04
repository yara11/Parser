public class Terminal implements Node {
    private String value;
    public Terminal(String value) {
    	this.value = value;
    }
    @Override
    public Boolean isTerminal() {
    	return true;
    }
}
