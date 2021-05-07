package edu.ucalgary.ensf409;
import java.util.*;

/**
 * <h1>OptionCalculation</h1>
 * This class is used to calculate the cheapest combination of
 * inventory items that can be used to fulfill an order as specified
 * by the user. This class calculates the most cost effective
 * way of assembling a certain item using components from other items.
 *
 * @author  Beau McCartney, Apostolos Scondrianis, Quentin Jennings, Jacob Lansang
 * @version 2.4
 */


/**
 * Calculates the cheapest combination of items needed to fulfill the requested order, indicating if its not possible with the items in the database.
 *
 * @param <T> One of the four furniture types available: CHAIR, DESK, LAMP, FILING.
 */
public class OptionCalculation <T extends Furniture> {

    /**
     * Indicates what type of furniture: CHAIR, DESK, LAMP, or FILING
     */
    private final String TYPE;

    /**
     * The number of items in the order.
     */
    private final int NUMOFITEMS;

    /**
     * The total price of the cheapest possible order. -1 means there is currently no valid order.
     */
    private int totalLowestPrice = -1;

    /**
     * A list of the items used for the lowest price order.
     */
    private ArrayList<T> lowestPriceItems;


    /**
     * Instantiates a new Option calculation.
     *
     * @param TYPE       the type of furniture.
     * @param NUMOFITEMS the number of items.
     */
    public OptionCalculation(String TYPE, int NUMOFITEMS) {
        this.TYPE = TYPE;
        this.NUMOFITEMS = NUMOFITEMS;
    }

    /**
     * Finds the cheapest combination of furniture needed to fulfill the order, updating the class fields with a
     * list of the furniture and its overall price.
     *
     * @param furnList the list of furniture available to order.
     * @return Boolean indicating if all necessary items are in stock to fulfill the order or not
     */
    public boolean calculateCheapestPrice(ArrayList<T> furnList) {
	    furnList = getFurnitureOfType(furnList);
	    if(furnList.isEmpty()){
	        return false; //no valid items = no order
        }
	    else {
	        //if each furniture item only has 1 usable part, this is the max number of items in an order
            int maxNumOfItems = furnList.get(0).getValidParts().length * NUMOFITEMS;

            //runs the combination recursive algorithm for each number of possible combinations
            for (int i = 1; i <= maxNumOfItems; i++) {
                if (i <= furnList.size()) {
                    calculateCheapestCombo(furnList, i); //calls the combination function for each possible value r
                }
            }
            return !(lowestPriceItems == null);
        }
    }

    /**
     * Takes a list of furniture and returns a list with only one type of furniture.
     *
     * @param furnList the list of a category of furniture.
     * @return the list of one type of the input category of furniture
     */
    private ArrayList<T> getFurnitureOfType(ArrayList<T> furnList){
        ArrayList<T> furnitureOfType = new ArrayList<>();
        for(T furniture : furnList){
            if(furniture.getType().equals(TYPE)){
                furnitureOfType.add(furniture);
            }
        }
        return furnitureOfType;
    }

    /**
     * Finds the cheapest combination of items needed to provide all the necessary components to fulfill the order.
     * Calls a recursive function to accomplish this.
     *
     * @param furnitureList List of available furniture.
     * @param r the number of elements to pull from the furnitureList
     */
    private void calculateCheapestCombo(ArrayList<T> furnitureList, int r){
        ArrayList<T> currentCombo = new ArrayList<>();
        //initializes currentCombo to r empty elements
        for(int i = 0; i < r; i++){
            currentCombo.add(null);
        }

        findCombinations_Recursion(furnitureList, currentCombo, 0, 0, r);
    }

    /**
     * Implementation of a recursive algorithm that finds the cheapest combination of furniture with the necessary components.
     *
     * @param furnList
     * @param currCombo
     * @param currIndex
     * @param level
     * @param r
     */
    private void findCombinations_Recursion(ArrayList<T> furnList, ArrayList<T> currCombo, int currIndex, int level, int r){
        if(level == r){ //exits the recursion when we are r levels deep
            processCombination(currCombo); //processes the current combo
            return;
        }
        for(int i = currIndex; i < furnList.size(); i++){ //the recursive part of the function
            currCombo.set(level, furnList.get(i)); //builds the current combo
            findCombinations_Recursion(furnList, currCombo, i+1, level+1, r);
        }
    }

    /**
     * Processes a combination of furniture objects. Calculates the total price and counts the total number
     * of valid parts across the combination. If there are enough parts to complete the order across the objects,
     * it then checks for the lowest price.
     *
     * @param combo The combination of furniture objects to be processed.
     */
    private void processCombination(ArrayList<T> combo){
        int[] numOfParts = new int[combo.get(0).getValidParts().length];
        int totalPrice = 0;
        ArrayList<T> lowestItems = new ArrayList<>();

        //gathers total item price, total items, and total parts
        for(T item : combo){
            totalPrice += item.getPrice();
            lowestItems.add(item);
            for(int i = 0; i < item.getValidParts().length; i++){
                if(item.getValidParts()[i]){
                    numOfParts[i]++;
                }
            }
        }

        /*
        //Testing stuff
        for(T item : combo){
            System.out.print(item.getId()+", ");
        }
        System.out.print(": ");
        for(int partNum : numOfParts){
            System.out.print(partNum);
        }
        System.out.println();
        */

        //checks to see if there are enough of each part for the order
        boolean hasAllParts = true;
        for(int numOfThisPart : numOfParts){
            if(numOfThisPart < NUMOFITEMS){
                hasAllParts = false;
                break;
            }
        }
        //if it has all the parts and the total price is lower (or the first valid one),
        //set the total lowest price and its items to the combination processed
        if(hasAllParts && (totalPrice < totalLowestPrice || totalLowestPrice == -1)){
            lowestPriceItems = lowestItems;
            totalLowestPrice = totalPrice;
        }
    }

    /**
     * Returns the IDs of furniture items needed to fulfill the order.
     *
     * @param furnList Array of the appropriate furniture type, from which the IDs of the furniture will be fetched.
     */
    public ArrayList<String> genItemIDs(ArrayList<T> furnList){
	    ArrayList<String> itemIDs = new ArrayList<>();
        for(T item : furnList){
	        itemIDs.add(item.getId());
        }
        return itemIDs;
    }

    /**
     * Gets the list of Furniture objects containing the lowest price items.
     *
     * @return the list.
     */
    public ArrayList<T> getLowestPriceItems() { return lowestPriceItems; }

    /**
     * Gets the list of IDs of the items needed to fulfill the order
     *
     * @return the list.
     */
    public ArrayList<String> getLowestPriceIDs() { return genItemIDs(lowestPriceItems); }

    /**
     * Gets total lowest price.
     *
     * @return the total lowest price
     */
    public int getTotalLowestPrice() { return totalLowestPrice; }

    /**
     * Gets type of furniture.
     *
     * @return the type
     */
    public String getType() { return this.TYPE; }

    /**
     * Gets number of items.
     *
     * @return the number of items
     */
    public int getNumOfItems() { return this.NUMOFITEMS; }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * Gets numofitems.
     *
     * @return the numofitems
     */
    public int getNUMOFITEMS() {
        return NUMOFITEMS;
    }

    /**
     * Sets total lowest price.
     *
     * @param totalLowestPrice the total lowest price
     */
    public void setTotalLowestPrice(int totalLowestPrice) {
        this.totalLowestPrice = totalLowestPrice;
    }
}