package sol;

import src.IAttributeDatum;
import src.INode;

import java.util.LinkedList;

public class Node implements INode {
        String attribute;
        LinkedList<Edge> edges;

        // traverse tree based on attribute values to retrieve decision
        public Object lookupDecision(IAttributeDatum attrVals){
                //going through each of the edges until reach a final decision
                ...
        }

        // print tree
        public void printNode(String leadspace){
                System.out.print("attribute" + this.attribute);
                //printNode("        ");
        }
}
