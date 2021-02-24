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

    public ListObjsData<T> data;
    public INode tree;
    /**
     * Constructor for this class.
     *
     * @param initTrainingData - IAttributeDataset of the data table
     */
   public TreeGenerator(ListObjsData<T> initTrainingData) {
        this.data = initTrainingData;
    }

    // build a decision tree to predict the named attribute
    @Override
    public INode buildClassifier(String targetAttr) {
        //if it's empty
        //pick first attribute - random  - create node that contains attribute

        //remove targetAttr
        INode tree = new Node(); //OR do we set equal to this.tree
        ListObjsData<T> dataset = this.data;
        dataset.removeAtt(targetAttr);

        //for (String att : this.data.attribute){
        //Creating first node with attribute
        Node firstNode = new Node();
        String holdingAttribute = dataset.attribute.get(0);
        firstNode.attribute = holdingAttribute;
        firstNode.edges = new LinkedList<>();

        //Partition data based on attribute looking at
            LinkedList<IAttributeDataset<T>> partitionedData =
                this.data.partition(holdingAttribute);

        //Create Edges
            for (IAttributeDataset<T> innerList: partitionedData){
                Edge edge = new Edge();


            }
            //LinkedList<IAttributeDataset<T>> distinct = new LinkedList<>();
            //maybe ListObjsData
            //distinct.add(innerList);



            //if it is the final node

        //create edges: set value and decision can be the rest of the tree on the inner list
        //condition to check if it's final decision or rest of tree
        // recursive

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