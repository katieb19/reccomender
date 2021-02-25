package sol;

import java.util.LinkedList;
import java.util.List;

import tester.Tester;

public class ListObjsDataTest {
    public ListObjsData<Vegetable> setupVeg(){
        LinkedList<String> attribute = new LinkedList<>();
        attribute.add("name");
        attribute.add("color");
        attribute.add("lowCarb");
        attribute.add("likesToEat");
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
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

    public ListObjsData<Vegetable> setuponeVeg(){
        LinkedList<String> attribute = new LinkedList<>();
        attribute.add("name");
        attribute.add("color");
        attribute.add("lowCarb");
        attribute.add("likesToEat");
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
        LinkedList<Vegetable> vegetables = new LinkedList<>();
        vegetables.add(v1);
        ListObjsData<Vegetable> vegList = new ListObjsData<Vegetable>(
                vegetables, attribute);
        return vegList;
    }


    public void testGetAttributes(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();

        //Multiple attributes
        LinkedList<String> attribute = new LinkedList<>();
        attribute.add("name");
        attribute.add("color");
        attribute.add("lowCarb");
        attribute.add("likesToEat");

        t.checkExpect(veg.getAttributes(), attribute);

        //Only one attribute
        veg.removeAtt("name");
        veg.removeAtt("color");
        veg.removeAtt("likesToEat");

        t.checkExpect(veg.getAttributes(), new LinkedList<>().add("lowCarb"));

        //Empty Attributes list
        veg.removeAtt("lowCarb");
        LinkedList<String> emptyList = new LinkedList<>();
        t.checkExpect(veg.equals(emptyList));
        t.checkExpect(veg.size() == 0);
    }

    public void testAllSameValue(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        t.checkExpect(veg.allSameValue("likesToEat"), true);
        t.checkExpect(veg.allSameValue("LIKESTOEAT"), false);
        t.checkExpect(veg.allSameValue("color"), false);
        t.checkExpect(oneVeg.allSameValue("color"), true);
    }

    public void testSize(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        t.checkExpect(veg.size(), 3);
        t.checkExpect(oneVeg.size(), 1);
        oneVeg.rows.remove(v1);
        t.checkExpect(oneVeg.size(), 0);
    }

    public void testDistinct(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();
        Vegetable v4 = new Vegetable("spinach", "green",
                true, true);
        veg.rows.add(v4);

        //Multiple elements
        Vegetable v1 = new Vegetable("spinach", "green",
                true, true);
        Vegetable v2 = new Vegetable("pea", "green", false,
                false);
        Vegetable v3 = new Vegetable("carrot", "orange",
                true, false);
        LinkedList<Vegetable> finalList = new LinkedList<>();
        finalList.add(v1);
        finalList.add(v2);
        finalList.add(v3);
        t.checkExpect(veg.distinct(), finalList);

        //One element
        veg.rows.remove(v4);
        veg.rows.remove(v3);
        veg.rows.remove(v1);
        t.checkExpect(veg.distinct(), new LinkedList<>().add(v2));

        //Empty List
        veg.rows.remove(v2);
        t.checkExpect(veg.distinct(), new LinkedList<>());

    }



    public static void main(String[] args) {
        Tester.run(new ListObjsDataTest());
    }
}
