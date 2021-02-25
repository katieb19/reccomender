package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.IGenerator;
import src.INode;

import java.util.LinkedList;

/*
 * Class for creating and interacting with a decision tree given a dataset.
 *
 * T is the type of object that we are trying to classify.
 * (like src.Vegetable)
 */
public class TreeGenerator<T extends IAttributeDatum> implements IGenerator {

    public IAttributeDataset<T> data;
    /**
     * Constructor for this class.
     *
     * @param initTrainingData - IAttributeDataset of the data table
     */
   public TreeGenerator(IAttributeDataset<T> initTrainingData) {
        this.data = initTrainingData;
    }


// BASE CASE: out of attributes, in that case pick most common answer OR all options have same for targetAttr value (check if true FIRST)
    // build a decision tree to predict the named attribute
    @Override
    public INode buildClassifier(String targetAttr) {

       //Setup
        IAttributeDataset<T> dataset = this.data;
        dataset.removeAtt(targetAttr); //doesn't work bc we don't have listObjsData
        String holdingAttribute = dataset.attribute.get(0);
        LinkedList<Edge> edgeList = new LinkedList<>();
        INode finalNode = new Node(holdingAttribute, edgeList);

        //Cases
        // empty Attribute List - out of attributes
        if (dataset.attribute == null){ //do we need listObjsData
            return finalNode;

            //all options have same value for target attribute
        } else if (this.data.allSameValue(targetAttr)){
            FinalDecision finalDecision = new FinalDecision(this.data.getSharedValue(targetAttr));
            Edge newEdge = new Edge(targetAttr, this.data.getSharedValue(holdingAttribute), finalDecision);
            edgeList.add(newEdge);
            return finalNode;

         // Not an edge case
        } else{

            //Partition data based on attribute looking at
            LinkedList<IAttributeDataset<T>> partitionedData =
                    this.data.partition(holdingAttribute);

            //Create Edges
            for (IAttributeDataset<T> inner: partitionedData){
                this.data = inner;
                Edge edge1 = new Edge(holdingAttribute, inner.getSharedValue(holdingAttribute),
                        this.buildClassifier(holdingAttribute));
                edgeList.add(edge1);
            }
            return finalNode;
        }
    }

    // produce the decision predicted for the given datum
    @Override
    public Object lookupRecommendation(IAttributeDatum forVals) {
        // TODO: Implement.
        return null;
    }

    // print the decision tree
    @Override
    public void printTree() {
        // TODO: Implement.
    }

}