package sol;

import java.util.LinkedList;

public class ListObjsDataTest {
    // setup for Candidate and/or Vegetable
    // attribute = new LinkedList(gender, leadership)
    public ListObjsData<Candidate> setup(){
        LinkedList<String> attribute = new LinkedList<String>();
        attribute.add("gender");
        attribute.add("leadershipExperience");
        attribute.add("lastPositionDuration");
        Candidate c = new Candidate("women", '','','');
        Candidate c1 = new Candidate("male", '','','');
        LinkedList<Candidate> candidates = new LinkedList<>();
        candidates.add(c);
        candidates.add(c1);
        //more than 1 less than 4
        ListObjsData<Candidate> list = new ListObjsData<>(candidates, attribute);
        return list;
    }

    public
}
