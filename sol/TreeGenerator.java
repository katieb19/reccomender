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

    // build a decision tree to predict the named attribute
    @Override
    public INode buildClassifier(String targetAttr) {
        //if it's empty
        //pick first attribute - random  - create node that contains attribute

        //Creating first node with attribute
        Node firstNode = new Node();
        firstNode.attribute = targetAttr;
        firstNode.edges = null;

        //Partition data
        LinkedList<IAttributeDataset<T>> partitionedData = this.data.partition(targetAttr);
        LinkedList<T> distinctData = this.data.distinct(targetAttr);

        //Create Edges
        Edge firstEdges = new Edge();

        for ()

            firstEdges.value = null;

        if (!firstEdges.decision == FinalDecision){

        }


        firstEdges.decision = null;
        String value;
        INode decision; //node or another class with an object (int, string)


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