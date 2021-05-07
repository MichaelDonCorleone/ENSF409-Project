package edu.ucalgary.ensf409;

/**
 * <h1>Desk</h1>
 * 
 * Child class that inherits from class Furniture.
 * Used to implement the Furniture that can be
 * classified as Desks.
 * 
 * @author  Beau McCartney, Apostolos Scondrianis, Quentin Jennings, Jacob Lansang
 * @version 2.4
 */
 
public class Desk extends Furniture {
    /**
     * boolean that indicates if the desk legs are usable
     */
    private boolean legs;

    /**
     * boolean that indicates if the desk top is usable
     */
    private boolean top;

    /**
     * boolean that indicates if the chair drawer is usable
     */
    private boolean drawer;

    /**
     * Constructor for Child Class Desk
     *
     * @param id     ID of the Desk
     * @param type   Type of the Desk
     * @param price  Price tag of the Desk
     * @param manuID Manufacturer ID of the Desk
     * @param legs   Boolean that indicates if the Legs are usable
     * @param top    Boolean that indicates if the Top is usable
     * @param drawer Boolean that indicates if the Drawers are usable
     */
    public Desk(String id, String type, int price, String manuID, boolean legs, boolean top, boolean drawer) {
        super(id, type, price, manuID);
        this.legs = legs;
        this.top = top;
        this.drawer = drawer;
    }
    //Desk class setter Functions

    /**
     * Sets legs
     *
     * @param legs  If the legs are usable or not
     */
    public void setLegs(boolean legs) {
        this.legs = legs;
    }

    /**
     * Sets top
     *
     * @param top   If the top is usable or not
     */
    public void setTop(boolean top) {
        this.top = top;
    }

    /**
     * Sets drawer
     *
     * @param drawer    If the drawer is usable or not
     */
    public void setDrawer(boolean drawer) {
        this.drawer = drawer;
    }
    //Desk Class getter functions

    /**
     * Gets the status of the legs if they are usable or unusable
     *
     * @return the status of the legs
     */
    public boolean getLegs() {
        return legs;
    }

    /**
     * Gets the status of the top if it is usable or unusable
     *
     * @return the status of the top
     */
    public boolean getTop() {
        return top;
    }

    /**
     * Gets the status of the drawer if it is usable or unusable
     *
     * @return the status of the drawer
     */
    public boolean getDrawer() {
        return drawer;
    }

    /**
     * Gets valid parts
     *
     * @return the valid parts array of desk.
     */
    public boolean[] getValidParts() {
        return new boolean[] {legs, top, drawer};
    }
}