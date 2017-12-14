/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Comparator;

/**
 *
 * @author salma
 */
public class ProductionRuleComparator implements Comparator<ProductionRule> {
    @Override
    public int compare(ProductionRule p1, ProductionRule p2)
    {
        Node n1=p1.getSequence().get(0);
        Node n2=p2.getSequence().get(0);
        if(n1.isTerminal()&&n2.isTerminal())
        return ((Terminal)n1).getValue().compareTo(((Terminal)n2).getValue());
        else if(!n1.isTerminal()&&!n2.isTerminal())
            return ((NonTerminal)n1).getName().compareTo(((NonTerminal)n2).getName());
        else return -1;
    }
}
