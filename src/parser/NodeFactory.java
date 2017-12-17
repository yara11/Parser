package parser;
public class NodeFactory {

	public Node getTerminal(String val) {
		return new Terminal(val);
	}
	public Node getNonTerminal() {
		return new NonTerminal();
	}
}