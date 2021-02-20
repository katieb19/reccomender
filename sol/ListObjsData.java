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

    public LinkedList<IAttributeDatum> table; // list of rows

    public ListObjsData(LinkedList<IAttributeDatum> rows){
        this.table = rows;
    }


    String gender;
    boolean leadershipExperience;
    String lastPositionDuration;
    String numWorkExperiences;
    String programmingLanguages;
    String gpa;
    String location;
    boolean hired;

    @Override
    public LinkedList<String> getAttributes() {
        LinkedList<Object> attribute = new LinkedList<>();
        for (int i = 0; i < this.size(); i++){

        }
        // TODO: Implement.
        return null;
    }

    @Override
    public boolean allSameValue(String ofAttribute) {
        // TODO: Implement.
        return false;
    }

    @Override
    public int size() {
        IAttribute Datum person = this.;
        int size = 0;
        while (person.next != null){
            size = size + 1;

        }
        return 0;
    }

    @Override
    public LinkedList<IAttributeDataset<T>> partition(String onAttribute) {
        // TODO: Implement.
        return null;
    }

    @Override
    public Object getSharedValue(String ofAttribute) {
        // TODO: Implement.
        return null;
    }

    @Override
    public Object mostCommonValue(String ofAttribute) {
        // TODO: Implement.
        return null;
    }
}
