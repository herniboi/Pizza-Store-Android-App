package com.example.myapplication;
/**
 * creates the enum class Size
 * @author Andy Li, Henry lin
 */
public enum Size {
    Small, Medium, Large;

    /**
     * converts the size from enum to a string
     * @param size
     * @return string form of the size
     */
    public String toString(Size size) {
        if (size.equals(Small)){
            return "small";
        } else if (size.equals(Medium)) {
            return "medium";
        } else if (size.equals(Large)) {
            return "large";
        } else {
            return "";
        }
    }
}
