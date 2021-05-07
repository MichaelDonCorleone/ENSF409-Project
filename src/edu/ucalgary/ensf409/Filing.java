package edu.ucalgary.ensf409;
import java.util.ArrayList;

/**
 * <h1>Filing</h1>
 * Child Class Filing that is used as a container for furniture of
 * the category Filing Cabinets.
 *
 * @author  Beau McCartney, Apostolos Scondrianis, Quentin Jennings, Jacob Lansang
 * @version 2.4
 */

public class Filing extends Furniture {
    /**
     * boolean that indicates if the filing rails are usable
     */
    private boolean rails;

    /**
     * boolean that indicates if the filing drawers are usable
     */ 
    private boolean drawers;

    /**
     * boolean that indicates if the filing cabinet is usable
     */
    private boolean cabinet;

    /**
     * Constructor for the Child Class Filing
     *
     * @param id      ID of the Filing Cabinet
     * @param type    Type of the Filing Cabinet
     * @param price   Price of the Filing Cabinet
     * @param manuID  Manufacturer ID of the Filing Cabinet
     * @param rails   Boolean that is used to indicate if rails are usable
     * @param drawers Boolean that is used to indicate if the drawers are usable
     * @param cabinet Boolean that is used to indicate if the cabinet is usable
     */
    public Filing(String id, String type, int price, String manuID, boolean rails, boolean drawers,
                 boolean cabinet) {
        super(id, type, price, manuID);
        this.rails = rails;
        this.drawers = drawers;
        this.cabinet = cabinet;
    }

    //Setter functions for the Filing Cabinets

    /**
     * Sets rails
     *
     * @param rails If the rails are usable or not
     */
    public void setRails(boolean rails) {
        this.rails = rails;
    }

    /**
     * Sets drawers
     *
     * @param drawers   If the drawers are usable or not
     */
    public void setDrawers(boolean drawers) {
        this.drawers = drawers;
    }

    /**
     * Sets cabinet
     *
     * @param cabinet   If the cabinet is usable or not
     */
    public void setCabinet(boolean cabinet) {
        this.cabinet = cabinet;
    }

    //Getter functions for the Filing Cabinets

    /**
     * Gets the status of the rails if they are usable or not
     *
     * @return the status of the rails
     */
    public boolean getRails() {
        return rails;
    }

    /**
     * Gets the status of the drawers if they are usable or not
     *
     * @return the status of the drawers
     */
    public boolean getDrawers() {
        return drawers;
    }

    /**
     * Gets the status of the cabinet if it is usable or not
     *
     * @return the status of the cabinet
     */
    public boolean getCabinet() {
        return cabinet;
    }

    /**
     * Gets valid parts
     *
     * @return the valid parts array of Filing
     */
    public boolean[] getValidParts() {
        return new boolean[] {rails, drawers, cabinet};
    }
}
