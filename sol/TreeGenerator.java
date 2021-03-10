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

        //Setup
        IAttributeDataset<T> dataSet = this.data;
        LinkedList<String> attributes = dataSet.getAttributes();
        attributes.remove(targetAttr);

        // Empty data
        if (this.data.size() == 0){
            throw new RuntimeException("data doesn't exist");
        }

        // Empty attribute list
        if (attributes.size() == 0){
            FinalDecision finalDecision = new
                    FinalDecision(this.data.mostCommonValue(targetAttr));
            this.root = finalDecision;
            return finalDecision;
        }


        Random num = new Random();
        String holdingAttribute =
                attributes.get(num.nextInt(attributes.size()));

        LinkedList<Edge> edgeList = new LinkedList<>();

        INode finalNode = new Node(holdingAttribute, edgeList,
                this.data.mostCommonValue(targetAttr));


        //All elements same value for attribute
        if (this.data.allSameValue(targetAttr)){
            FinalDecision finalDecision =
                    new FinalDecision(this.data.getSharedValue(targetAttr));
            this.root = finalDecision;
            return finalDecision;
        }

         // Not an edge case
        else{

            //Partition data based on attribute looking at
            LinkedList<IAttributeDataset<T>> partitionedData =
                    this.data.partition(holdingAttribute);

            //Create Edges
            for (IAttributeDataset<T> inner: partitionedData){
                this.data = inner;
                Edge edge1 = new Edge(holdingAttribute,
                        inner.getSharedValue(holdingAttribute),
                        this.buildClassifier(targetAttr));
                edgeList.add(edge1);

            }
            this.root = finalNode;
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