package sol;

import src.INode;

public class Edge {
        String attribute;
        Object value;
        INode decision; //node or another class with an object (int, string)

        //constructor
        public Edge(String att, Object val, INode des){
             this.attribute = att;
             this.value = val;
             this.decision = des;
        }

}
