package src;

import java.util.LinkedList;

public interface IAttributeDataset<T extends IAttributeDatum> {

    /**
     * Gets all the attributes in the dataset.
     *
     */
    public LinkedList<String> getAttributes();

    /**
     * does every row/datum have the same value for the given attribute/column.
     *
     * @param ofAttribute - String - wanted attribute
     */
    public boolean allSameValue(String ofAttribute);


    /**
     * Number of data/rows in the dataset.
     *
     */
    public int size();


    //public LinkedList<String> removeAtt(String attribute);


    /**
     * partition rows into subsets such that each subset has same value of onAttribute.
     *
     * @param onAttribute - String - wanted attribute
     */
    public LinkedList<IAttributeDataset<T>> partition(String onAttribute);


    /**
     * Get the value for ofAttribute, which is assumed to be common across all rows.
     *
     * @param ofAttribute - String - wanted attribute
     */
    public Object getSharedValue(String ofAttribute);


    /**
     * Get the most common value for ofAttribute in the dataset.
     *
     * @param ofAttribute - String - wanted attribute
     */
    public Object mostCommonValue(String ofAttribute);
}
