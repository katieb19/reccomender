package sol;

import src.IAttributeDatum;
import src.INode;

/**
 * A class representing the final decision of the tree generated from the
 * TreeGenerator class
 */
public class FinalDecision implements INode {
    Object decision;

    /**
     * A constructor for the Final Decision class
     * @param decision - represents the value of the final decision
     */
    public FinalDecision(Object decision){
        this.decision = decision;
    }

    @Override
    public Object lookupDecision(IAttributeDatum attrVals){
        return this.decision;
    }

    @Override
    public void printNode(String leadspace){
        System.out.print("final decision     " + leadspace + this.decision );
    }
}
