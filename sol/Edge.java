package sol;

import src.INode;

/**
 * A class that represents an edge of a Node
 */
public class Edge {
        String attribute;
        Object value;
        INode decision;

    /**
     * Constructor for the Edge class
     * @param att - A string that represents the attribute the edge is
     *            connected to
     * @param val - The value of att that the Edge represents
     * @param des - represents a Node or a FinalDecision class
     */
        public Edge(String att, Object val, INode des){
             this.attribute = att;
             this.value = val;
             this.decision = des;
        }

}
