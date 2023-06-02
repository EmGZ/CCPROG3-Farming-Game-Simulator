import javax.swing.*;

/** 
 * This class Crop represents the Crop used in the game where it has its name, type, cost, harvest time,
 * needed water, needed fertilizer, the minimum and maximum amount of harvest the player can get,
 * the selling price and experience points the player can gain when it had been harvested. There is also
 * an isWithered property to represent if the crop has withered.
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class Crop {
    private String name;
    private String cropType;
    private double seedCost;
    private boolean isWithered;

    // Crop Status
    private int harvestTime;
    private int age;

    // Watering
    private int currentWater;
    private int neededWater;
    private int bonusWaterLimit;

    // Fertilizer
    private int currentFertilizer;
    private int neededFertilizer;
    private int bonusFertiLimit;

    // Crops to be gained
    private int minGain;
    private int maxGain;

    // Player Gain
    private double sellPrice;
    private double expGainCrop;
    private ImageIcon icon;

    /**
     * This is the constructor for class Crop
     * @param name is the name of the crop
     * @param cropType is the type of the crop
     * @param seedCost is the cost of planting the crop
     * @param harvestTime is the harvest time of the crop
     * @param neededWater is the number of needed water of the crop
     * @param bonusWaterLimit is the bonus water limit of the crop
     * @param neededFertilizer is the needed fertilizer of the crop
     * @param bonusFertiLimit is the bonus fertilizer limit of the crop
     * @param minGain is minimum gain of produce of the crop
     * @param maxGain is the maximum gain of produce of the crop
     * @param sellPrice is the selling price per piece of the crop
     * @param expGainCrop is the experience points gain from harvesting the crop 
     * @param icon is the image icon of the crop
     */
    public Crop(String name, String cropType,
                double seedCost, int harvestTime, int neededWater,
                int bonusWaterLimit, int neededFertilizer, int bonusFertiLimit,
                int minGain, int maxGain, double sellPrice, double expGainCrop, ImageIcon icon) {
        this.name = name;
        this.cropType = cropType;
        this.seedCost = seedCost;
        this.harvestTime = harvestTime;
        this.age = 0;
        this.currentWater = 0;
        this.neededWater = neededWater;
        this.bonusWaterLimit = bonusWaterLimit;
        this.currentFertilizer = 0;
        this.neededFertilizer = neededFertilizer;
        this.bonusFertiLimit = bonusFertiLimit;
        this.minGain = minGain;
        this.maxGain = maxGain;
        this.sellPrice = sellPrice;
        this.expGainCrop = expGainCrop;
        this.icon = icon;
    }

    /**
     * This the constructor for initializing values from the current crop selected
     * @param currCrop is the current crop selected from the game
     */
    public Crop(Crop currCrop) {
        this.name = currCrop.getName();
        this.cropType = currCrop.getCropType();
        this.seedCost = currCrop.getSeedCost();
        this.harvestTime = currCrop.getHarvestTime();
        this.age = currCrop.getAge();
        this.currentWater = currCrop.getCurrentWater();
        this.neededWater = currCrop.getNeededWater();
        this.bonusWaterLimit = currCrop.getBonusWaterLimit();
        this.currentFertilizer = currCrop.getCurrentFertilizer();
        this.neededFertilizer = currCrop.getNeededFertilizer();
        this.bonusFertiLimit = currCrop.getBonusFertiLimit();
        this.minGain = currCrop.getMinGain();
        this.maxGain = currCrop.getMaxGain();
        this.sellPrice = currCrop.getSellPrice();
        this.expGainCrop = currCrop.getExpGainCrop();
        this.icon = currCrop.getIcon();
    }

    /**
     * getName() gets the name of the crop
     * @return name of the crop
     */
    public String getName() {
        return name;
    }

    /**
     * setName() sets the name of the crop
     * @param name - the name of the crop
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getCropType() gets the type of the crop
     * @return cropType of the crop
     */
    public String getCropType() {
        return cropType;
    }

    /**
     * setCropType() sets the type of the crop
     * @param cropType - the type of the crop
     */
    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    /**
     * getSeedCost() gets the cost of the crop
     * @return seedCost of the crop
     */
    public double getSeedCost() {
        return seedCost;
    }

    /**
     * setSeedCost() sets the cost of the crop
     * @param seedCost is the cost of the crop
     */
    public void setSeedCost(double seedCost) {
        this.seedCost = seedCost;
    }

    /**
     * getHarvestTime() gets the harvest time of the crop
     * @return harvestTime of the crop
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    /**
     * setHarvestTime() sets the harvest time of the crop
     * @param harvestTime is the harvest time of the crop
     */
    public void setHarvestTime(int harvestTime) {
        this.harvestTime = harvestTime;
    }

    /**
     * getAge() gets the age of the crop
     * @return age of the crop
     */
    public int getAge() {
        return age;
    }

    /**
     * setAge() sets the age of the crop
     * @param age is the age of the crop
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getCurrentWater() gets the current number of times the crop was watered
     * @return currentWater of the crop
     */
    public int getCurrentWater() {
        return currentWater;
    }

    /**
     * setCurrentWater() sets the current number of times the crop was watered
     * @param currentWater is the current number of times the crop was watered
     */
    public void setCurrentWater(int currentWater) {
        this.currentWater = currentWater;
    }

    /**
     * getNeededWater() gets the number of times the crop need to be watered
     * @return neededWater of the crop
     */
    public int getNeededWater() {
        return neededWater;
    }

    /**
     * setNeededWater() sets the number of times the crop need to be watered
     * @param neededWater is the number of times the crop need to be watered
     */
    public void setNeededWater(int neededWater) {
        this.neededWater = neededWater;
    }


    /**
     * getBonusWaterLimit() gets the number of times the crop can to be watered
     * @return bonusWaterLimit of the crop
     */
    public int getBonusWaterLimit() {
        return bonusWaterLimit;
    }

    /**
     * setBonusWaterLimit() sets the number of times the crop can be watered
     * @param bonusWaterLimit is the number of times the crop can be watered
     */
    public void setBonusWaterLimit(int bonusWaterLimit) {
        this.bonusWaterLimit = bonusWaterLimit;
    }

    /**
     * getCurrentFertilizer() gets the current number of times the crop was fertilized
     * @return currentFertilizer of the crop
     */
    public int getCurrentFertilizer() {
        return currentFertilizer;
    }

    /**
     * setCurrentFertilizer() sets the current number of times the crop was fertilized
     * @param currentFertilizer is the current number of times the crop was fertilized
     */
    public void setCurrentFertilizer(int currentFertilizer) {
        this.currentFertilizer = currentFertilizer;
    }

    /**
     * getNeededFertilizer() gets the number of times the crop need to be fertilized
     * @return neededFertilizer of the crop
     */
    public int getNeededFertilizer() {
        return neededFertilizer;
    }

    /**
     * setNeededFertilizer() sets the number of times the crop need to be fertilized
     * @param neededFertilizer is the number of times the crop need to be fertilized
     */
    public void setNeededFertilizer(int neededFertilizer) {
        this.neededFertilizer = neededFertilizer;
    }

    /**
     * getBonusFertiLimit() gets the number of times the crop can to be fertilized
     * @return bonusFertiLimit of the crop
     */
    public int getBonusFertiLimit() {
        return bonusFertiLimit;
    }

    /**
     * setBonusFertiLimit() sets the number of times the crop can be fertilized
     * @param bonusFertiLimit is the number of times the crop can be fertilized
     */
    public void setBonusFertiLimit(int bonusFertiLimit) {
        this.bonusFertiLimit = bonusFertiLimit;
    }

    /**
     * getMinGain() gets the minimum number of products that the crop can produce
     * @return minGain of the crop
     */
    public int getMinGain() {
        return minGain;
    }

    /**
     * setMinGain() sets the minimum number of products that the crop can produce
     * @param minGain - the minimum number of products that the crop can produce
     */
    public void setMinGain(int minGain) {
        this.minGain = minGain;
    }

    /**
     * getMaxGain() gets the maximum number of products that the crop can produce
     * @return maxGain of the crop
     */
    public int getMaxGain() {
        return maxGain;
    }

    /**
     * setMaxGain() sets the maximum number of products that the crop can produce
     * @param maxGain - the maximum number of products that the crop can produce
     */
    public void setMaxGain(int maxGain) {
        this.maxGain = maxGain;
    }

    /**
     * getSellPrice() gets the selling price of the crop
     * @return sellPrice of the crop
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     * setSellPrice() sets the selling price of the crop
     * @param sellPrice - the selling price of the crop
     */
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * getExpGainCrop() gets the exp gained from harvesting the crop
     * @return expGainCrop of the crop
     */
    public double getExpGainCrop() {
        return expGainCrop;
    }

    /**
     * setExpGainCrop() sets the exp gained from harvesting the crop
     * @param expGainCrop - the exp gained from harvesting the crop 
     */
    public void setExpGainCrop(double expGainCrop) {
        this.expGainCrop = expGainCrop;
    }

    /**
     * isWithered() gets the wither status of the crop
     * @return isWithered of the crop
     */
    public boolean isWithered() {return isWithered;}

    /**
     * setWithered() sets the wither status of the crop
     * @param withered - the wither status of the crop
     */
    public void setWithered(boolean withered) {isWithered = withered;}

    /**
     * getIcon() gets the image icon of the crop
     * @return icon of the crop
     */
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "name: " + name + "\tage: " + age + "\tharvest time: " + harvestTime + "\nwater: " + currentWater + "\twaterNeeded: " + neededWater + "\tfertilizer: " + currentFertilizer
                + "\tfertilizerNeeded: " + neededFertilizer;
    }
}