package src;

public interface IGenerator {

    /**
     * Builds a decision tree to predict the named attribute.
     *
     * @param targetAttr - String - wanted attribute
     */
    public INode buildClassifier(String targetAttr);


    /**
     * Produces the decision predicted for the given datum.
     *
     * @param forVals - IAttributeDatum
     */
    public Object lookupRecommendation(IAttributeDatum forVals);


    /**
     * Prints the decision tree.
     *
     */
    public void printTree();
}