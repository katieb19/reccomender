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
        if (this.data == null){
            return this.root;
        }
        //Cases
        // empty Attribute List - out of attributes
        else if (this.data.getAttributes().size() == 1){
            IAttributeDataset<T> row = this.data;
            Object value = row.mostCommonValue(this.data.getAttributes().get(0));
            INode finalNode = new FinalDecision(value);
            return finalNode;

            //all options have same value for target attribute

         // Not an edge case
        } else{
            //Setup
            IAttributeDataset<T> dataSet = this.data;
            LinkedList<String> attributes = dataSet.getAttributes();
            attributes.remove(targetAttr); //doesn't work bc we don't have listObjsData
            Random num = new Random();
            String holdingAttribute = attributes.get(num.nextInt(attributes.size()));
            LinkedList<Edge> edgeList = new LinkedList<>();

            INode finalNode = new Node(holdingAttribute, edgeList);

            if (this.data.allSameValue(targetAttr)){

                FinalDecision finalDecision = new FinalDecision(this.data.getSharedValue(targetAttr));
                Edge newEdge = new Edge(targetAttr, this.data.getSharedValue(holdingAttribute), finalDecision);
                edgeList.add(newEdge);
                return finalNode;
            }

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