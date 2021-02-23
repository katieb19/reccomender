package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;

import java.util.LinkedList;
import java.util.List;

/*
 * Class for a specific representation of rows in a data table. This uses a list
 * of objects (one object per row).
 * The type T is the object that forms the "rows" of the data table
 */
public class ListObjsData<T extends IAttributeDatum>
        implements IAttributeDataset<T> {

    public LinkedList<T> rows; // list of rows
    public LinkedList<String> attribute;

    public ListObjsData(LinkedList<T> rows, LinkedList<String> attribute){
        this.rows = rows;
        this.attribute = attribute;
    }
    // data.table.get(1)

    public LinkedList<Object> getColumn(String attribute){
        LinkedList<Object> column = new LinkedList<>();
        for(T item: this.rows) {
            column.add(item.getValueOf(attribute));
        }
        return column;
    }

    //distinct function

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


    public LinkedList<T> distinct(LinkedList<T> items){
        LinkedList<T> distinct = new LinkedList<>();
        for (T obj: items){
            if (!distinct.contains(obj)){
                distinct.add(obj);
            }
        }
        return distinct;
    }


    public LinkedList<String> removeAtt(String attribute) {
        LinkedList<String> newList = this.attribute;
        for (String str : newList) {
            if (str.equals(attribute)) {
                newList.remove(attribute);
            }
        }
        return newList;
    }


    @Override
    public LinkedList<IAttributeDataset<T>> partition(String onAttribute) {
        // output a linked list w new tables, one of which has rows w green; one has rows with orange

        //helper method that creates a list of the unique values and use
        //within for loop with list of unique attributes to grab objects

        //WRITE REMOVE FUNCTION that returns new list
        LinkedList<IAttributeDataset<T>> result = new LinkedList<>();
        LinkedList<String> attList = this.removeAtt(onAttribute);

        if (this.allSameValue(onAttribute)){
            ListObjsData<T> newList = new ListObjsData<T> (this.rows, this.attribute);
            result.addFirst(newList);
        } else{
            for(T obj: distinct(this.rows)) {
                LinkedList<T> newList = new LinkedList<>();
                for (T row: this.rows) {
                    if (obj.equals(row.getValueOf(onAttribute))) {
                        newList.add(obj);
                    }
                }
                ListObjsData<T> finalList = new ListObjsData<T>(newList, attList);
                result.add(finalList);
            }
        }
        return result;
    }

    @Override
    public Object getSharedValue(String ofAttribute) {
        if (this.allSameValue(ofAttribute)){
            return this.rows.get(0).getValueOf(ofAttribute);
        }
        return null;
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
