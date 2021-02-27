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

    /**
     * A method that looks up the value of the final decision
     * @param attrVals - IAttributeDatum
     * @return the object that represents the final decision
     */
    public Object lookupDecision(IAttributeDatum attrVals){
        return this.decision;
    }

    /**
     * A method that prints a Node
     * @param leadspace - String containing tabbing
     */
    public void printNode(String leadspace){
        System.out.print("final decision     " + leadspace + this.decision );
    }
}
