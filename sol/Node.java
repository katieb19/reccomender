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
               if (this.edges == null){
                       return attrVals.getValueOf(this.attribute);
               }
               else{
                       //recursion
               }
        }
        //currently just returning color for esxample but we want it to return the whole thing so we need to go through the tree

        // print tree
        public void printNode(String leadspace){
                System.out.print("attribute" + this.attribute);
                System.out.print(leadspace + "edges" + this.edges);
                //for each node in the tree
        }
}
