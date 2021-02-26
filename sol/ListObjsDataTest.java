package sol;

import java.util.LinkedList;
import java.util.List;

import src.IAttributeDataset;
import tester.Tester;

public class ListObjsDataTest {

    /**
     * Setup testing list with multiple vegetables
     *
     * @return a ListObjsData of vegetables
     *
     */
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

    /**
     * Setup testing list with one vegetables
     *
     * @return a ListObjsData of vegetable
     */

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

    /**
     * Setup testing list with no vegetables
     *
     * @return a ListObjsData of vegetable
     */

    public ListObjsData<Vegetable> setupnoVeg(){
        LinkedList<String> attribute = new LinkedList<>();
        LinkedList<Vegetable> vegetables = new LinkedList<>();
        ListObjsData<Vegetable> vegList = new ListObjsData<Vegetable>(
                vegetables, attribute);
        return vegList;

    }

    /**
     * A tester method to test the GetAttributes method
     * @param t - Tester
     */
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
        LinkedList<String> attribute2 = new LinkedList<>();
        attribute2.add("lowCarb");
        LinkedList<Vegetable> vegetables = new LinkedList<>();
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
        vegetables.add(v1);
        ListObjsData<Vegetable> vegList = new ListObjsData<Vegetable>(
                vegetables, attribute2);

        t.checkExpect(vegList.getAttributes(), attribute2);

        //Empty Attributes list
        ListObjsData<Vegetable> noVeg = setupnoVeg();
        t.checkExpect(noVeg.size(), 0);
    }

    /**
     * A tester method to test allSameValue method
     * @param t - Tester
     */
    public void testAllSameValue(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        t.checkExpect(veg.allSameValue("likesToEat"), true);
        t.checkExpect(veg.allSameValue("color"), false);
        t.checkExpect(oneVeg.allSameValue("color"), true);
    }

    /**
     * A tester method to test the size method
     * @param t - Tester
     */
    public void testSize(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        t.checkExpect(veg.size(), 3);
        t.checkExpect(oneVeg.size(), 1);
        oneVeg.rows = new LinkedList<>();
        t.checkExpect(oneVeg.size(), 0);
    }


    /**
     * A tester
     * @param t
     */
    public void testDistinct(Tester t){
        ListObjsData<Vegetable> veg = setupVeg();
        ListObjsData<Vegetable> oneveg = setuponeVeg();
        ListObjsData<Vegetable> empty = setupnoVeg();

        //Multiple elements
        LinkedList<String> color = new LinkedList<>();
        color.add("green");
        color.add("orange");
        t.checkExpect(veg.distinct("color"), color);

        //One element
        LinkedList<String> color1 = new LinkedList<>();
        color1.add("green");
        t.checkExpect(oneveg.distinct("color"), color1);

        //Empty element
        LinkedList<Vegetable> emptyList = new LinkedList<>();
        t.checkExpect(empty.distinct("color").size(), 0);
        t.checkExpect(empty.distinct("color").equals(emptyList));
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
        LinkedList<String> attribute = new LinkedList<>();
        attribute.add("name");
        attribute.add("lowCarb");
        attribute.add("likesToEat");

        //Green
        LinkedList<Vegetable> vegetables = new LinkedList<>();
        ListObjsData<Vegetable> green = new ListObjsData<>(
                vegetables, attribute);
        Vegetable v1 = new Vegetable("spinach", "green",
                true, false);
        Vegetable v2 = new Vegetable("pea", "green", false,
                false);
        green.rows.add(v1);
        green.rows.add(v2);
        green.rows.add(v4);

        //Red
        LinkedList<Vegetable> vegetables2 = new LinkedList<>();
        ListObjsData<Vegetable> red = new ListObjsData<>(vegetables2, attribute);
        red.rows.add(v5);
        red.rows.add(v6);

        //Orange
        LinkedList<Vegetable> vegetables3 = new LinkedList<>();
        ListObjsData<Vegetable> orange = new ListObjsData<Vegetable>(
                vegetables3, attribute);
        Vegetable v3 = new Vegetable("carrot", "orange",
                true, false);
        orange.rows.add(v3);

        LinkedList<ListObjsData> finalList = new LinkedList<>();
        finalList.add(green);
        finalList.add(orange);
        finalList.add(red);


        t.checkExpect(partitionedData.size(), 3);
        t.checkExpect(partitionedData, finalList);
        t.checkExpect(partitionedData.get(0), green);
        t.checkExpect(partitionedData.get(1), orange);
        t.checkExpect(partitionedData.get(2), red);

        //One element
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        LinkedList<IAttributeDataset<Vegetable>> singlePartitionedData =
                oneVeg.partition("color");

        LinkedList<Vegetable> vegetables4 = new LinkedList<>();
        ListObjsData<Vegetable> oneGreen = new ListObjsData<Vegetable>(
                vegetables4, attribute);
        Vegetable greenV1 = new Vegetable("spinach", "green",
                true, false);
        oneGreen.rows.add(greenV1);

        t.checkExpect(singlePartitionedData.size(), 1);
        t.checkExpect(singlePartitionedData.get(0), oneGreen);

        //Empty List
        LinkedList<Vegetable> emptyList = new LinkedList<>();
        LinkedList<String> attribute5 = new LinkedList<>();
        ListObjsData<Vegetable> newEmpty = new ListObjsData<Vegetable>(
                emptyList, attribute5);
        t.checkException(new IndexOutOfBoundsException("less than or = to 0"),
                newEmpty, "partition", "color");
    }

    public void testGetSharedValue(Tester t){
        //Multiple elements - shared value
        ListObjsData<Vegetable> oneVeg = setuponeVeg();
        Vegetable v2 = new Vegetable("pea", "green", true,
                false);
        Vegetable v3 = new Vegetable("lettuce", "green",
                true, true);

        oneVeg.rows.add(v2);
        oneVeg.rows.add(v3);

        t.checkExpect(oneVeg.getSharedValue("color"),
                "green");
        t.checkExpect(oneVeg.getSharedValue("lowCarb"), true);

        //Multiple elements, no shared value
        ListObjsData<Vegetable> noSharedVeg = setupVeg();
        Vegetable v4 = new Vegetable("carrot", "orange",
                true, false);
        Vegetable v5 = new Vegetable("beets", "red",
                true, true);
        noSharedVeg.rows.add(v4);
        noSharedVeg.rows.add(v5);

        //t.checkExpect(noSharedVeg.getSharedValue("color"), null);

        //Single Element
        ListObjsData<Vegetable> singleVeg = setuponeVeg();
        //t.checkExpect(noSharedVeg.getSharedValue("color"), "green");

    }

    public void testMostCommonValue(Tester t){
        //Multiple elements
        ListObjsData<Vegetable> veg = setupVeg();

        ListObjsData<Vegetable> veg2 = setupVeg();
        Vegetable v2 = new Vegetable("beets", "red",
                true, true);
        Vegetable v3 = new Vegetable("tomato", "red",
                true, true);
        Vegetable v4 = new Vegetable("berries", "red",
                true, false);
        veg2.rows.add(v2);
        veg2.rows.add(v3);
        veg2.rows.add(v4);

        t.checkExpect(veg.mostCommonValue("color"), "green");
        t.checkExpect(veg2.mostCommonValue("color"), "red");
        t.checkExpect(veg2.mostCommonValue("lowCarb"), true);
        t.checkExpect(veg2.mostCommonValue("likesToEat"),
                false);

        //One element
        ListObjsData<Vegetable> singleVeg = setuponeVeg();
        t.checkExpect(veg.mostCommonValue("color"), "green");
    }


    public static void main(String[] args) {
        Tester.run(new ListObjsDataTest());
    }
}
