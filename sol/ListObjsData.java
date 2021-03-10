package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;

import javax.print.attribute.standard.JobKOctets;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * A class that implements ListObjsData
 * @param <T> - represents an IAttributeDatum which reflects the objects
 *           within the rows field
 */
public class ListObjsData<T extends IAttributeDatum>
        implements IAttributeDataset<T> {

    public LinkedList<T> rows; // list of rows
    public LinkedList<String> attribute;

    /**
     * A constructor for ListObjsData
     * @param attribute - a list of the attributes in an IAttributeDatum
     * @param rows - represents a list of IAttributeDatum
     */
    public ListObjsData(LinkedList<String> attribute, LinkedList<T> rows){
        this.rows = rows;
        this.attribute = attribute;
    }

    @Override
    public LinkedList<String> getAttributes() {
        return this.attribute;
    }

    @Override
    public boolean allSameValue(String ofAttribute) {
        Object item = (this.rows.get(0)).getValueOf(ofAttribute);
        for(T obj: this.rows) {
            if (obj.getValueOf(ofAttribute) != item){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.rows.size();
    }


    /**
     * A method that returns a distinct list of the values of an attribute
     * @param onAttribute - the attribute that we are looking for values of
     * @return a list of the distinct values of an attribute
     */
    public LinkedList<Object> distinct(String onAttribute){
        LinkedList<Object> distinct = new LinkedList<>();
        for (T obj: this.rows){
            Object val = obj.getValueOf(onAttribute);
            if (!distinct.contains(val)){
                distinct.add(val);
            }
        }
        return distinct;
    }


    @Override
    public LinkedList<IAttributeDataset<T>> partition(String onAttribute) {
        LinkedList<IAttributeDataset<T>> result = new LinkedList<>();
        LinkedList<String> attList = new LinkedList<>(this.attribute);
        attList.remove(onAttribute);

        if (this.rows.size() <= 0){
            throw new IndexOutOfBoundsException("less than or = to 0");
        }
        else if (this.allSameValue(onAttribute)){
            ListObjsData<T> newList = new ListObjsData<T>
                    (this.attribute, this.rows);
            result.addFirst(newList);
        } else{
            for(Object obj: this.distinct(onAttribute)) {
                LinkedList<T> newList = new LinkedList<>();
                for (T row: this.rows) {
                    if (obj.equals(row.getValueOf(onAttribute))) {
                        newList.add(row);
                    }
                }
                ListObjsData<T> finalList = new ListObjsData<T>(attList,
                        newList);
                result.add(finalList);
            }
        }
        return result;
    }

    @Override
    public Object getSharedValue(String ofAttribute) {
            return this.rows.get(0).getValueOf(ofAttribute);
    }


    @Override
    public Object mostCommonValue(String ofAttribute) {
        IAttributeDataset<T> longest = this.partition(ofAttribute).get(0);
        for(IAttributeDataset<T> obj: this.partition(ofAttribute)){
            if(longest.size() < obj.size()){
                longest = obj;
            }
        }
        return longest.getSharedValue(ofAttribute);
    }
}
