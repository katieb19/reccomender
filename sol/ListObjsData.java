package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;

import java.util.LinkedList;

/*
 * Class for a specific representation of rows in a data table. This uses a list
 * of objects (one object per row).
 * The type T is the object that forms the "rows" of the data table
 */
public class ListObjsData<T extends IAttributeDatum>
        implements IAttributeDataset<T> {

    public LinkedList<T> table; // list of rows
    public LinkedList<String> attribute;

    public ListObjsData(LinkedList<T> rows, LinkedList<String> attribute){
        this.table = rows;
        this.attribute = attribute;
    }
    // data.table.get(1)

    public LinkedList<Object> getColumn(String attribute){
        LinkedList<Object> column = new LinkedList<>();
        for(T item: this.table) {
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
        Object item = (this.table.get(0)).getValueOf(ofAttribute);
        for(T obj: this.table) {
            if (obj.getValueOf(ofAttribute) != item){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.table.size();
    }



    @Override
    public LinkedList<IAttributeDataset<T>> partition(String onAttribute) {
        // output a linked list w new tables, one of which has rows w green; one has rows with orange
        // TOOD: Implement.
        LinkedList<IAttributeDataset<T>> result = new LinkedList<>();
        Object values = this.table.get(0).getValueOf(onAttribute);

        if (this.allSameValue(onAttribute)){
            return result.addFirst(this.table);
        } else{
            for(T obj: this.table) {
                if (obj.getValueOf(onAttribute).equals(values)){
                    LinkedList<Object> newList = new LinkedList<>();
                    newList.add(obj);
                    result.add(newList);
                    //return newList;
                } else {
                    values = obj.getValueOf(onAttribute);
                    LinkedList<Object> newList2 = new LinkedList<>();
                    newList2.add(obj);
                    result.add(newList2);
                }
            }
            return result;
        }
    }

    @Override
    public Object getSharedValue(String ofAttribute) {
        if (this.allSameValue(ofAttribute)){
            return this.table.get(0).getValueOf(ofAttribute);
        }
        return null;
    }

    @Override
    public Object mostCommonValue(String ofAttribute) {
        IAttributeDataset<T> longest = this.partition(ofAttribute).get(0);
        for (IAttributeDataset<T> obj: this.partition(ofAttribute)){
            if (longest.size() < obj.size()){
                longest = obj;
            }
        }
        return longest.table.get(0).getValueOf(ofAttribute);
    }
}
