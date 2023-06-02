/** 
 * This class represents the player which will play the game. Player contains name, objectCoin or the currency of the game,
 * and dayCount which tracks the days inside the game.
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class Player{

    private String name;
    private double objectCoin;
    private int playerLevel;
    private double Exp;
    private int dayCount;

    private FarmerType farmerType;

    /**
     * This is the constructor for class Player
     * @param farmerType is the farmer type of the player
     */
    public Player(FarmerType farmerType){
        this.objectCoin = 10000;
        this.dayCount = 1;
        this.farmerType = farmerType;
        this.playerLevel = 0;
        this.Exp = 10000;
    }

    /**
     * getName() gets the name of the player
     * @return String - the name of the player
     */
    public String getName(){
        return name;
    }

    /**
     * setName() sets the name of the player
     * @param name - name specified by player
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * getObjectCoin() gets the objectCoin amount
     * @return double - the amount of objectCoin the player has
     */
    public double getObjectCoin(){
        return objectCoin;
    }

    /**
     * setObjectCoin() sets the objectCoin
     * @param objectCoin - new value of objectCoin
     */
    public void setObjectCoin(double objectCoin){
        this.objectCoin = objectCoin;
    }

    /**
     * setDayCount() sets the day count
     * @param dayCount - new value of day count
     */
    public void setDayCount(int dayCount){
        this.dayCount = dayCount;
    }

    /**
     * getDayCount() gets the day count
     * @return an integer - the day count
     */
    public int getDayCount(){
        return dayCount;
    }

    /**
     * getFarmerTypeName() gets the farmer type name
     * @return String - the name of the farmer type
     */
    public String getFarmerTypeName(){
        return farmerType.getTypeName();
    }

    /**
     * getFarmerType() gets the FarmerType object
     * @return FarmerType object
     */
    public FarmerType getFarmerType() {
        return farmerType;
    }

    /**
     * setFarmerType() sets the FarmerType object
     * @param farmerType - the new FarmerType
     */
    public void setFarmerType(FarmerType farmerType) {
        this.farmerType = farmerType;
    }

    /**
     * getPlayerLevel() gets the level of the player
     * @return an integer - the player level
     */
    public int getPlayerLevel() {
        return playerLevel;
    }

    /**
     * setPlayerLevel() sets the level of the player
     * @param playerLevel - new level of the player
     */
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    /**
     * getExp() gets the player exp
     * @return exp - the exp of player
     */
    public double getExp() {
        return Exp;
    }

    /**
     * setExp() sets the exp of the player
     * @param exp - the new exp of the player
     */
    public void setExp(double exp) {
        Exp = exp;
    }

    @Override
    public String toString(){
        return "\n[Player Information] \nName: " + getName() + "\nobjectCoins = " + String.format("%.2f", getObjectCoin()) + "\n[DAY #" + getDayCount() + "]\n";

    }

}
