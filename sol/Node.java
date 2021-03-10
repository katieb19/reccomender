package sol;

import src.IAttributeDatum;
import src.INode;

import java.util.LinkedList;

/**
 * A class that represents a Node and implements INode interface
 */
public class Node implements INode {
        String attribute;
        LinkedList<Edge> edges;
        Object mostCommon;

    /**
     * A constructor for the Node class
     * @param at - represents the attribute that the node is evaluating
     * @param edg - represents the sub-trees based on the attribute,
     *            which is a list of edges
     */
    public Node(String at, LinkedList<Edge> edg, Object most){
                this.attribute = at;
                this.edges = edg;
                this.mostCommon = most;
        }

        @Override
        public Object lookupDecision(IAttributeDatum attrVals){
            LinkedList<IAttributeDatum> row = new LinkedList<>();
            row.add(attrVals);
            LinkedList<String> str = new LinkedList<>();
            str.add(this.attribute);
            ListObjsData<IAttributeDatum> list = new
                    ListObjsData<IAttributeDatum>(
                    str, row);
            for (Edge edge: this.edges){
                   if (edge.value.equals(attrVals.getValueOf(this.attribute))){
                       return edge.decision.lookupDecision(attrVals);
                   }
               }
            return this.mostCommon;
        }

        @Override
        public void printNode(String leadspace){
                String newline = System.lineSeparator();
                System.out.print("attribute " + this.attribute + newline);
                for (Edge edge : this.edges) {
                    System.out.print("    value    " + edge.value + leadspace +
                            newline);
                    edge.decision.printNode("   ");
                }
        }
}
