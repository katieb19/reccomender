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
               for (Edge edge: this.edges){
                   LinkedList<IAttributeDatum> row = new LinkedList<>();
                   row.add(attrVals);
                   LinkedList<String> str = new LinkedList<>();
                   str.add(this.attribute);
                   ListObjsData<IAttributeDatum> list = new
                           ListObjsData<IAttributeDatum>(row,
                           str);
                   if (edge.value.equals(attrVals.getValueOf(this.attribute))){
                       return edge.decision.lookupDecision(attrVals);
                   }
                   return list.mostCommonValue(this.attribute);
               }
               //what goes here?
        }
        //currently just returning color for example but we want it to return the whole thing so we need to go through the tree

        // print tree
        public void printNode(String leadspace){
                System.out.print("attribute" + this.attribute);
                System.out.print(leadspace + "edges" + this.edges);
                //for each node in the tree
        }
}
