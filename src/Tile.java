import java.util.Random;

/** 
 * This class Tile represents the tile used in the game where the player can plant their chosen seed
 * after plowing then can water, fertilize, and harvest once the planted Crop is harvestable. It also
 * display the reason for withering when the game has detected that the planted crop has withered
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class Tile {

    private Crop plantedCrop = null;
    private boolean isPlowed = false;
    private boolean isHarvestable = false;
    private boolean hasRocks = false;
    private String witherStatus = "";

    private static final Random RDM = new Random();

    /**
     * getPlantedCrop() gets the crop planted in the tile
     * @return Crop - the crop planted
     */
    public Crop getPlantedCrop() {
        return plantedCrop;
    }

    /**
     * setPlantedCrop() sets the crop planted in the tile
     * @param plantedCrop - the crop to be planted
     */
    public void setPlantedCrop(Crop plantedCrop) {
        this.plantedCrop = plantedCrop;
    }

    /**
     * isPlowed() checks if the tile is plowed or not
     * @return boolean - 0 if plowed, else if not
     */
    public boolean isPlowed() {
        return isPlowed;
    }

    /**
     * setPlowed() sets the boolean for isPlowed
     * @param isPlowed - the boolean, whether true or false
     */
    public void setPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }

    /**
     * isHarvestable() checks if the tile is harvestable
     * @return boolean - 0 if harvestable, else if not
     */
    public boolean isHarvestable() {
        return isHarvestable;
    }

    /**
     * setHarvestable() sets the boolean for isHarvestable
     * @param harvestable - the boolean, whether true or false
     */
    public void setHarvestable(boolean harvestable) {
        this.isHarvestable = harvestable;
    }

    /**
     * getHasRocks() checks whether a tile has a rock
     * @return boolean - 0 if has rocks, else if none
     */
    public boolean getHasRocks() {
        return hasRocks;
    }

    /**
     * setHasRocks sets the boolean for hasRocks
     * @param hasRocks - the boolean, whether true or false
     */
    public void setHasRocks(boolean hasRocks) {
        this.hasRocks = hasRocks;
    }

    /**
     * getWitherStatus() gets the witherStatus or the reason for withering
     * @return String - reason for withering
     */
    public String getWitherStatus() {
        return witherStatus;
    }

    /**
     * setWitherStatus() sets the witherStatus or reason for withering
     * @param witherStatus - a text that states the withering reason
     */
    public void setWitherStatus(String witherStatus) {
        this.witherStatus = witherStatus;
    }

    /**
     * farmerBonus() applies bonuses on the seeds depending on the farmer type of the player
     * @param seed is crop planted in the farm lot
     * @param player is the player of the game
     * @return the crop with applied bonuses
     */
    public Crop farmerBonus(Crop seed, Player player) {
        Model1 cModel = new Model1();
        var cList = cModel.getCrop();
        var crop = seed;
        for(Crop c : cList) {
            if(crop.getName().equals(c.getName())) {
                var earnings = c.getSellPrice() + player.getFarmerType().getBonusEarnings();
                var cost = c.getSeedCost() + player.getFarmerType().getSeedCostReduction();
                var waterLim = c.getBonusWaterLimit() + player.getFarmerType().getWaterBonusLimitIncr();
                var fertLim = c.getBonusFertiLimit() + player.getFarmerType().getFertilizerBonusLimitIncr();
                crop.setSellPrice(earnings);
                crop.setSeedCost(cost);
                crop.setBonusFertiLimit(fertLim);
                crop.setBonusWaterLimit(waterLim);
            }
        }
        return crop;
    }

    /**
     * plant() prompts the player to plant its chosen seed to the tile
     * @param seed is the plant seed that the player chose to plant
     * @param player is the player of the game
     * @return the status of the Planting Action
     */
    public ActionStatus plant(Crop seed, Player player) {
        var status = false;
        var message = "";

        // if player could afford seed
        if (player.getObjectCoin() >= seed.getSeedCost()) {
            //SUCCESS
            this.plantedCrop = farmerBonus(seed, player);
            player.setObjectCoin(player.getObjectCoin() - seed.getSeedCost());
            status = true;
            message = seed.getName() + " has been planted. " + seed.getSeedCost() + " objectCoins was deducted from your account.";
        } else
            message = "There are no enough ObjectCoins to buy seed";

        return new ActionStatus(status, message);
    }

    /**
     * usageOfToolUpdate() updates the attributes of the players whenever a tool was used
     * @param cost is the cost of the tool usage
     * @param exp is the exp earned from using the tool
     * @param player is the player of the game
     */
    public void usageOfToolUpdate(int cost, double exp, Player player){
        player.setObjectCoin(player.getObjectCoin() - cost);
        player.setExp(player.getExp() + exp);
    }

    /**
     * water() prompts the player to water the tile
     * @param tool is the tool item to be used by the player for watering
     * @param player is the player of the game
     * @return the status of the Watering Action
     */
    public ActionStatus water(Tool tool, Player player) {
        var status = false;
        var message = "";
        // if tile is occupied (SUCCESS)
        var water = plantedCrop.getCurrentWater();
        plantedCrop.setCurrentWater(water + 1);
        usageOfToolUpdate(tool.getCost(), tool.getExpGain(), player);
        status = true;
        message = "Crop has been watered";
        return new ActionStatus(status, message);
    }

    /**
     * fertilize() prompts the player to fertilize the tile
     * @param tool is the tool item to be used by the player for fertilizing
     * @param player is the player of the game
     * @return the status of the Fertilizing Action
     */
    public ActionStatus fertilize(Tool tool, Player player) {
        var status = false;
        var message = "";

        // if player affords fertilizer
        if (player.getObjectCoin() >= tool.getCost()) {
            status = true;
            usageOfToolUpdate(tool.getCost(), tool.getExpGain(), player);
            plantedCrop.setCurrentFertilizer(plantedCrop.getCurrentFertilizer() + 1);
            message = "fertilizing successful! " + tool.getCost() + " ObjectCoins was deducted from your account";
        } else
            message = "There are no enough ObjectCoins to fertilize plants";

        return new ActionStatus(status, message);
    }

    /**
     * plow() prompts the player to plow the tile
     * @param tool is the tool item to be used by the player for plowing
     * @param player is the player of the game
     * @return the status of the Plowing Action
     */
    public ActionStatus plow(Tool tool, Player player) {
        // tile is unplowed and unoccupied (SUCCESS)
        var status = true;
        isPlowed = true;
        usageOfToolUpdate(tool.getCost(), tool.getExpGain(), player);
        var message = "Plowing successful!";

        return new ActionStatus(status, message);
    }

    /**
     * pickaxe() prompts the player to pick a tile with rocks
     * @param tool is the tool item to be used by the player for picking rocks
     * @param player is the player of the game
     * @return report of the Pick Action
     */
    public ActionStatus pickaxe(Tool tool, Player player){
        var status = false;
        var message = "";

        if(player.getObjectCoin() >= tool.getCost()) {
            setHasRocks(false);
            usageOfToolUpdate(tool.getCost(), tool.getExpGain(), player);
            message = "Removal of rock was successful";
            status = true;
        }
        else  message = "Removal of rock was unsuccessful. Not enough objectCoins to do the action.";

        return new ActionStatus(status, message);
    }

    /**
     * dig() prompts the player to dig a tile
     * @param tool is the tool item to be used by the player for digging tile
     * @param player is the player of the game
     * @return report of the Dig Action
     */
    public ActionStatus dig(Tool tool, Player player) {
        var status = false;
        var message = "";

        // if player affords fertilizer
        if (player.getObjectCoin() >= tool.getCost()) {
            status = true;
            usageOfToolUpdate(tool.getCost(), tool.getExpGain(), player);
            if(plantedCrop != null) {
                if(plantedCrop.isWithered()) {
                    plantedCrop.setWithered(false);
                }
                setHarvestable(false);
                plantedCrop = null;
            }
            setPlowed(false);
            message = "You used the Shovel! " + tool.getCost() + " ObjectCoins was deducted from your account";
        } else
            message = "There are no enough ObjectCoins to use the Shovel";

        return new ActionStatus(status, message);
    }

    /**
     * harvest() prompts the player to harvest the planted crop and have the player get objectcoins from the profit
     * @param player is the player of the game
     * @return report of the Harvest Action
     */
    public HarvestReport harvest(Player player) {
        //Generates random number of crops depending to its Min and Max Gain
        var cropGain = RDM.nextInt(plantedCrop.getMinGain(), plantedCrop.getMaxGain() + 1);
        //Computation for the Profit of the Harvest
        var produced = cropGain * plantedCrop.getSellPrice();
        var waterExtra = plantedCrop.getCurrentWater();
        if (waterExtra >= plantedCrop.getBonusWaterLimit())
            waterExtra = plantedCrop.getBonusWaterLimit();

        var fertExtra = plantedCrop.getCurrentFertilizer();
        if (fertExtra >= plantedCrop.getBonusFertiLimit())
            fertExtra = plantedCrop.getBonusFertiLimit();

        var waterBonus = produced * 0.2 * (waterExtra - 1);
        var fertBonus = produced * 0.5 * fertExtra;
        var profit = produced + waterBonus + fertBonus;
        if(plantedCrop.getCropType().equals("Flower"))
            profit = profit * 1.1;
        //Gives the player the profit from Harvesting
        player.setObjectCoin(player.getObjectCoin() + profit);
        player.setExp(player.getExp() + plantedCrop.getExpGainCrop());
        //Set the Tile to its default state
        isPlowed = false;
        isHarvestable = false;
        plantedCrop.setAge(0);
        plantedCrop.setCurrentWater(0);
        plantedCrop.setCurrentFertilizer(0);
        return new HarvestReport(plantedCrop.getName(), cropGain, profit);
    }

    /**
     * witherReason() gives the player the reason of withering if the planted Crop has withered
     */
    public void witherReason() {
        var reason = "Your crop has withered because of the following reason/s: \n";
        //If the planted crop wasn't harvest on its Harvest Day
        if (plantedCrop.getAge() > plantedCrop.getHarvestTime())
            reason += "Overripe\n";
            //If the planted crop isn't watered or fertilized properly
        else if (plantedCrop.getAge() == plantedCrop.getHarvestTime()) {
            if (plantedCrop.getCurrentWater() < plantedCrop.getNeededWater())
                reason += "Lack of Water ";
            if (plantedCrop.getCurrentFertilizer() < plantedCrop.getNeededFertilizer())
                reason += "Lack of Fertilizer";
        }
        //Sets the status of the plantedCrop to default
        plantedCrop.setAge(0);
        plantedCrop.setCurrentWater(0);
        plantedCrop.setCurrentFertilizer(0);
        setHarvestable(false);
        setWitherStatus(reason);
    }

    @Override
    public String toString() {
        return "[Crop Information: " + plantedCrop + "]";
    }

}