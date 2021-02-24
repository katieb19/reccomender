package sol;

import src.IAttributeDatum;
import src.INode;

import java.util.LinkedList;

public class Node implements INode {
        String attribute;
        LinkedList<Edge> edges;

        public Node(String at, LinkedList<Edge> edg){
                this.attribute = at;
                this.edges = edg;
        }

        // traverse tree based on attribute values to retrieve decision
        public Object lookupDecision(IAttributeDatum attrVals){
               return attrVals.getValueOf(this.attribute);
        }

        // print tree
        public void printNode(String leadspace){
                System.out.print("attribute" + this.attribute);
                System.out.print(leadspace + "edges" + this.edges);
        }
}
