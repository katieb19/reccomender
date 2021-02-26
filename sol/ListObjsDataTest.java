package sol;

import java.util.LinkedList;
import java.util.List;

import src.IAttributeDataset;
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
        veg.attribute.remove("name");
        veg.attribute.remove("color");
        veg.attribute.remove("likesToEat");

        t.checkExpect(veg.getAttributes(), new LinkedList<>().add("lowCarb"));

        //Empty Attributes list
        veg.attribute.remove("lowCarb");
        LinkedList<String> emptyList = new LinkedList<>();
        t.checkExpect(veg.size(), 0);
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

    public void testPartition(Tester t){
        //Multiple elements
        ListObjsData<Vegetable> veg = setupVeg();

        Vegetable v4 = new Vegetable("lettuce", "green",
                true, true);
        Vegetable v5 = new Vegetable("beets", "red",
                true, true);
        Vegetable v6 = new Vegetable("tomato", "red",
                true, true);

        veg.rows.add(v4);
        veg.rows.add(v5);
        veg.rows.add(v6);

        LinkedList<IAttributeDataset<Vegetable>> partitionedData = veg.partition("color");

        //Green
        LinkedList<Vegetable> green = new LinkedList<>();
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
        Vegetable v2 = new Vegetable("pea", "green", false,
                false);
        green.add(v1);
        green.add(v2);
        green.add(v4);

        //Red
        LinkedList<Vegetable> red = new LinkedList<>();
        red.add(v5);
        red.add(v6);

        //Orange
        LinkedList<Vegetable> orange = new LinkedList<>();
        Vegetable v3 = new Vegetable("carrot", "orange",
                true, false);
        orange.add(v3);

        t.checkExpect(partitionedData.size(), 3);
        t.checkExpect(partitionedData.contains(green));
        t.checkExpect(partitionedData.contains(red));
        t.checkExpect(partitionedData.contains(orange));

        //One element
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        LinkedList<IAttributeDataset<Vegetable>> singlePartitionedData = oneVeg.partition("color");

        LinkedList<Vegetable> oneGreen = new LinkedList<>();
        Vegetable greenV1 = new Vegetable("spinach", "green",
                true, false);
        oneGreen.add(greenV1);

        t.checkExpect(singlePartitionedData.size(), 1);
        t.checkExpect(singlePartitionedData.contains(oneGreen));

        //Empty List
        LinkedList<Vegetable> emptyList = new LinkedList<>();
        LinkedList<String> attribute = new LinkedList<>();
        ListObjsData<Vegetable> newEmpty = new ListObjsData<Vegetable>(
                emptyList, attribute);
        newEmpty.partition("color");
        t.checkExpect(newEmpty.size(), 0);
    }

    public void testGetSharedValue(Tester t){
        //Multiple elements - shared value
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        Vegetable v2 = new Vegetable("pea", "green", false,
                false);
        Vegetable v3 = new Vegetable("lettuce", "green",
                true, true);

        oneVeg.rows.add(v2);
        oneVeg.rows.add(v3);

        t.checkExpect(oneVeg.getSharedValue("color"), "green");

        //Multiple elements, no shared value
        ListObjsData<Vegetable> noSharedVeg = setuponeVeg();
        Vegetable v4 = new Vegetable("carrot", "orange",
                true, false);
        Vegetable v5 = new Vegetable("beets", "red",
                true, true);
        t.checkExpect(noSharedVeg.getSharedValue("color"), null);

        //Single Element
        ListObjsData<Vegetable> singleVeg = setuponeVeg();
        t.checkExpect(noSharedVeg.getSharedValue("color"), "green");

        //Empty List
        ListObjsData<Vegetable> noVeg = setuponeVeg();
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
        noVeg.rows.remove(v1);
        t.checkExpect(noVeg.getSharedValue("color"), null);

    }

    public void testMostCommonValue(Tester t){
        //Multiple elements
        ListObjsData<Vegetable> veg = setupVeg();
        t.checkExpect(veg.mostCommonValue("color"), "green");

        ListObjsData<Vegetable> veg2 = setupVeg();
        Vegetable v2 = new Vegetable("beets", "red",
                true, true);
        Vegetable v3 = new Vegetable("tomato", "red",
                true, true);
        Vegetable v4 = new Vegetable("berries", "red",
                true, true);
        veg2.rows.add(v2);
        veg2.rows.add(v3);
        veg2.rows.add(v4);

        t.checkExpect(veg.mostCommonValue("color"), "red");


        //One element
        ListObjsData<Vegetable> singleVeg = setuponeVeg();
        t.checkExpect(veg.mostCommonValue("color"), "green");

        //Empty list
        ListObjsData<Vegetable> noVeg = setuponeVeg();
        Vegetable v1 = new Vegetable("spinach", "green",
                true, true);
        noVeg.rows.remove(v1);
        t.checkExpect(veg.mostCommonValue("color"), null);
    }


    public static void main(String[] args) {
        Tester.run(new ListObjsDataTest());
    }
}
