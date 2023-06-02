import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class Model1 centers on the declaration of main classes of the game. 
 * This is where all the important components such as the Player, Crop, Tool, and FarmerType are initialized. 
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class Model1 {

    private static int currNum = 0;
    private Tile[] tile = new Tile[50];
    private final List<Crop> crop = new ArrayList<>(Arrays.asList(
            new Crop("Turnip", "Root crop", 5, 2, 1, 2, 0, 1, 1, 2, 6, 5, new ImageIcon("turnip.jpg")),
            new Crop("Carrot", "Root crop", 10, 3, 1, 2, 0, 1, 1,2, 9, 7.5, new ImageIcon("carrot.jpg")),
            new Crop("Potato", "Root Crop", 20, 5, 3, 4, 1, 2, 1, 10, 3, 12.5, new ImageIcon("potato.jpg")),
            new Crop("Rose", "Flower", 5, 5, 1, 2,0, 1, 1, 1, 5, 2.5, new ImageIcon("rose.jpg")),
            new Crop("Tulips", "Flower", 10, 2, 2, 3, 0, 1, 1, 1, 9, 5, new ImageIcon("tulip.jpg")),
            new Crop("Sunflower", "Flower", 20, 3, 2, 3, 1, 2, 1, 1, 19, 7.5, new ImageIcon("sunflower.jpg")),
            new Crop("Mango", "Fruit Tree", 100, 10, 7, 7, 4, 4, 5, 15, 8, 25, new ImageIcon("mango.jpg")),
            new Crop("Apple", "Fruit Tree", 200, 10, 7, 7, 5, 7, 10, 15, 5, 25, new ImageIcon("apple.jpg"))

    ));
    private final List<Tool> toolList = new ArrayList<>(Arrays.asList(
            new Tool("Plow", 0, 0.5),
            new Tool("Watering Can", 0, 0.5),
            new Tool("Fertilizer", 10, 4),
            new Tool("Pickaxe", 50, 15),
            new Tool("Shovel", 7, 2)));


    private final List<FarmerType> farmerTypeList = new ArrayList<>(Arrays.asList(
            new FarmerType("Farmer", 0, 0, 0, 0, 0, 0),
            new FarmerType("Registered Farmer", 5, 1, -1, 0, 0, 200),
            new FarmerType("Distinguished Farmer", 10, 2, -2, 1, 0, 300),
            new FarmerType("Legendary Farmer", 15, 4, -3, 2, 1, 400)
    ));

    private final Player player = new Player(farmerTypeList.get(0));

    /**
     * setPlayerName() sets the name of the player
     * @param name the name set by user
     */
    public void setPlayerName (String name) {
        player.setName(name);
    }

    /**
     * getPlayerName() gets the player name
     * @return String - the name
     */
    public String getPlayerName(){
        return player.getName();
    }

    /**
     * setObjectCoin() sets the objectCoin
     * @param objectCoin - the currency of the game
     */
    public void setObjectCoin (double objectCoin){
        player.setObjectCoin(objectCoin);
    }

    /**
     * getObjectCoin() gets the amount of objectCoin of the player
     * @return double - the amount
     */
    public double getObjectCoin(){
        return player.getObjectCoin();
    }

    /**
     * getPlayerFarmerTypeName() gets the farmer type from player
     * @return String - the name of farmer type
     */
    public String getPlayerFarmerTypeName(){
        return player.getFarmerTypeName();
    }

    /**
     * getPlayerFarmerType() gets the FarmerType object
     * @param type string - farmer type name
     * @return FarmerType
     */
    public FarmerType getPlayerFarmerType(String type){
        for (FarmerType farmerType : farmerTypeList) {
            if (farmerType.getTypeName().equals(type)) {
                return farmerType;
            }
        }
        return null;
    }

    /**
     * setPlayerFarmerType() sets the farmer type of the player
     * @param typeName - the typename to be set on player's farmer type
     */
    public void setPlayerFarmerType(String typeName) {
        for (FarmerType farmerType : farmerTypeList) {
            if (farmerType.getTypeName().equals(typeName)) {
                player.setFarmerType(farmerType);
            }
        }
    }

    /**
     * getPlayer() gets the Player object
     * @return Player
     */

    public Player getPlayer() {
        return player;
    }

    /**
     * getPlayerExp() gets the player experience from Player object
     * @return double - the experience value
     */
    public double getPlayerExp(){
        return player.getExp();
    }

    /**
     * setPlayerExp() sets the player's experience
     * @param exp - the experience value
     */
    public void setPlayerExp(double exp){
        player.setExp(exp);
    }

    /**
     * getPlayerLevel() gets the player level from Player object
     * @return an integer - the player level
     */
    public int getPlayerLevel(){
        return player.getPlayerLevel();
    }

    /**
     * setPlayerLevel sets the player's level
     * @param level - the level
     */
    public void setPlayerLevel(int level){
        player.setPlayerLevel(level);
    }

    /**
     * getDayCount() gets the day count from Player object
     * @return an integer - the day count
     */
    public int getDayCount(){
        return player.getDayCount();
    }

    /**
     * setDayCount() sets the day count
     * @param day - the day/count
     */
    public void setDayCount(int day){
        player.setDayCount(day);
    }

    /**
     * getFarmerTypeLevel() gets the farmer type level requirements
     * @param farmerType - the farmer type
     * @return an integer - the level required
     */
    public int getFarmerTypeLevel(String farmerType){
        for (FarmerType type : farmerTypeList) {
            if (type.getTypeName().equals(farmerType)) {
                return type.getLvlRequirement();
            }
        }
        return 0;
    }

    //TILES SETTER & GETTER

    /**
     * getTile() gets the tile object
     * @return Tile array
     */
    public Tile[] getTile() {
        return tile;
    }

    /**
     * setTile() sets the tile
     * @param tile - the tile
     */
    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    // SEED BOOK

    /**
     * getCurrCrop() gets the current crop depending on the current number
     * @return the Crop object
     */
    public Crop getCurrCrop() {
        return crop.get(currNum);
    }

    /**
     * isFirstSeed() checks whether user is in first page
     * @return boolean
     */
    public boolean isFirstSeed(){
        return currNum == 0;
    }

    /**
     * isLastSeed() checks whether user is in last page
     * @return boolean
     */
    public boolean isLastSeed(){
        return currNum == crop.size() - 1;
    }

    /**
     * nextSeed() increments currNum if not lastSeed
     */
    public void nextSeed() {
        if (!isLastSeed())
            currNum++;
    }

    /**
     * prevSeed() decrements currNum if not firstSeed
     */
    public void prevSeed(){
        if (!isFirstSeed())
            currNum--;
    }

    /**
     * getCurrNum() gets the current number
     * @return an integer
     */
    public int getCurrNum() {
        return currNum;
    }

    /**
     * setCurrNum() sets the current number
     * @param num - the new value
     */
    public void setCurrNum(int num) {
        currNum = num;
    }

    /**
     * getTool() gets the Tool object from arraylist of Tool
     * @param n - the index of tool
     * @return the tool on specific index
     */
    public Tool getTool(int n) {
        return toolList.get(n);
    }

    /**
     * getCrop() gets the Crop object from arraylist of Crop
     * @return the crop object
     */
    public List<Crop> getCrop() {
        return crop;
    }
}
