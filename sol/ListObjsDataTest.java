package sol;

import java.util.LinkedList;

import tester.Tester;

public class ListObjsDataTest {
    public ListObjsData<Vegetable> setupVeg(){
        LinkedList<String> attribute = new LinkedList<>();
        attribute.add("name");
        attribute.add("color");
        attribute.add("lowCarb");
        attribute.add("likesToEat");
        Vegetable v1 = new Vegetable("spinach", "green",
                true, true);
        Vegetable v2 = new Vegetable("pea", "green", false,
                false);
        Vegetable v3 = new Vegetable("carrot", "orange",
                true, false);
        LinkedList<Vegetable> vegetables = new LinkedList<>();
        vegetables.add(v1);
        vegetables.add(v2);
        vegetables.add(v3);
        ListObjsData<Vegetable> vegList = new ListObjsData<Vegetable>(
                vegetables, attribute);
        return vegList;
    }


    public void testGetAttributes(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();
        LinkedList<String> attribute = new LinkedList<>();
        attribute.add("name");
        attribute.add("color");
        attribute.add("lowCarb");
        attribute.add("likesToEat");

        t.checkExpect(veg.getAttributes(), attribute);

        veg.removeAtt("name");
        veg.removeAtt("color");
        veg.removeAtt("likesToEat");

        t.checkExpect(veg.getAttributes(), new LinkedList<>().add("lowCarb"));
    }
    /*public ListObjsData<Candidate> setup(){
        LinkedList<String> attribute = new LinkedList<String>();
        attribute.add("gender");
        attribute.add("leadershipExperience");
        attribute.add("lastPositionDuration");
        attribute.add("numWorkExperiences");
        attribute.add("programmingLanguages");
        attribute.add("gpa");
        attribute.add("location");
        attribute.add("hired");
        Candidate c = new Candidate("women", true, "16 Months",
                "4", "Python", "3.9", "New York", true);
        Candidate c1 = new Candidate("male", true, "2 Months",
                "1", "Python, Scala", "3.8", "New York", false);
        LinkedList<Candidate> candidates = new LinkedList<>();
        candidates.add(c);
        candidates.add(c1);
        //more than 1 less than 4
        ListObjsData<Candidate> list = new ListObjsData<>(candidates, attribute);
        return list;
    }

    public LinkedList<String> testGetAttributes(t Tester){
        setup();

        //Empty attribute list
        LinkedList<String> emptyList = new LinkedList<String>();
        candidates.removeAttr("gender");
        attribute.removeAttr("leadershipExperience");
        attribute.removeAttr("lastPositionDuration");
        attribute.removeAttr("numWorkExperiences");
        attribute.removeAttr("programmingLanguages");
        attribute.removeAttr("gpa");
        attribute.removeAttr("location");
        attribute.removeAttr("hired");
        t.checkExpect(candidates.attribute.equals(emptyList));

        //Multiple elements list
        t.checkExpect(candidates.attribute.equals(LinkedList<String>("gender", "leadershipExperience", "lastPositionDuration",
                "numWorkExperiences", "programmingLanguages", "gpa", "location", "hired")));
    } */

    public static void main(String[] args) {
        Tester.run(new ListObjsDataTest());
    }
}
