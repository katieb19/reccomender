package sol;

import src.IAttributeDatum;
import src.INode;

public class FinalDecision implements INode {
    Object decision;

    public FinalDecision(Object decision){
        this.decision = decision;
    }

    // traverse tree based on attribute values to retrieve decision
    public Object lookupDecision(IAttributeDatum attrVals){
        return this.decision;
    };

    // print tree
    public void printNode(String leadspace);
}
