package sol;

import src.IAttributeDatum;

public class Vegetable implements IAttributeDatum {

    public String name;
    public String color;
    public boolean lowCarb;
    public boolean likesToEat;


    public Vegetable(String name, String color, boolean lowCarb,
                     boolean likestoEat){

        this.name = name;
        this.color = color;
        this.lowCarb = lowCarb;
        this.likesToEat = likestoEat;
    }

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
