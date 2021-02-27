package sol;

import src.IAttributeDatum;

/**
 * A class that represents a vegetable which implements IAttributeDatum
 */
public class Vegetable implements IAttributeDatum {

    public String name;
    public String color;
    public boolean lowCarb;
    public boolean likesToEat;


    /**
     * A constructor for the Vegetable class
     * @param name - A string that represents the name of a vegetable
     * @param color - A string that represents the color of a vegetable
     * @param lowCarb - A boolean that represents if a vegetable is low carb
     * @param likestoEat - A boolean that represents if a person likes to eat
     *                   a vegetable
     */
    public Vegetable(String name, String color, boolean lowCarb,
                     boolean likestoEat){

        this.name = name;
        this.color = color;
        this.lowCarb = lowCarb;
        this.likesToEat = likestoEat;
    }

    /**
     * A method that gets the value of a vegetable object using a string that
     * represents an attribute
     * @param attributeName - String - wanted attribute
     * @return the value of the attribute based on the field in the vegetable
     * object
     */
    public Object getValueOf(String attributeName) {
        switch (attributeName) {
            case "name":
                return this.name;
            case "color":
                return this.color;
            case "lowCarb":
                return this.lowCarb;
            case "likesToEat":
                return this.likesToEat;
            default:
                throw new RuntimeException("Unknown field" + attributeName +
                        "in Vegetable class");
        }
    }
}
