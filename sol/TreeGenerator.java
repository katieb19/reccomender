package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.IGenerator;
import src.INode;

import java.util.LinkedList;
import java.util.Random;

/*
 * Class for creating and interacting with a decision tree given a dataset.
 *
 * T is the type of object that we are trying to classify.
 * (like src.Vegetable)
 */
public class TreeGenerator<T extends IAttributeDatum> implements IGenerator {

    public IAttributeDataset<T> data;
    public INode root;

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

        // Empty data
        if (this.data.size() == 0){
            FinalDecision finalDecision = new FinalDecision(this.data.mostCommonValue(targetAttr));
            return finalDecision;
        }

        //Setup
        IAttributeDataset<T> dataSet = this.data;
        LinkedList<String> attributes = dataSet.getAttributes();
        attributes.remove(targetAttr);

        Random num = new Random();
        String holdingAttribute = attributes.get(num.nextInt(attributes.size()));

        LinkedList<Edge> edgeList = new LinkedList<>();

        INode finalNode = new Node(holdingAttribute, edgeList);


        //All elements same value for attribute
        if (this.data.allSameValue(targetAttr)){
            FinalDecision finalDecision = new FinalDecision(this.data.getSharedValue(targetAttr));
            Edge newEdge = new Edge(targetAttr, this.data.getSharedValue(holdingAttribute), finalDecision);
            edgeList.add(newEdge);
            this.root = finalNode;
            return finalNode;
        }

         // Not an edge case
        else{

            //Partition data based on attribute looking at
            LinkedList<IAttributeDataset<T>> partitionedData =
                    this.data.partition(holdingAttribute);

            //Create Edges
            for (IAttributeDataset<T> inner: partitionedData){
                IAttributeDataset<T> recurData = this.data;
                recurData = inner;
                Edge edge1 = new Edge(holdingAttribute, inner.getSharedValue(holdingAttribute),
                        this.buildClassifier(holdingAttribute));
                edgeList.add(edge1);
                INode copyRoot = this.root;
                this.root = finalNode;

            }
            return finalNode;
        }
    }

    // produce the decision predicted for the given datum
    @Override
    public Object lookupRecommendation(IAttributeDatum forVals) {
        return this.root.lookupDecision(forVals);
    }

    // print the decision tree
    @Override
    public void printTree() {
        this.root.printNode("           ");
    }


}