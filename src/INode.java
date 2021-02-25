package src;

import src.IAttributeDatum;

/*
 * A node in the decision tree
 */
public interface INode {

    /**
     * Traverse tree based on attribute values to retrieve decision.
     *
     * @param attrVals - IAttributeDatum
     */
    public Object lookupDecision(IAttributeDatum attrVals);

    /**
     * Prints tree.
     *
     * @param leadspace - String containing tabbing
     */
    public void printNode(String leadspace);
}
