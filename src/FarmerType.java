/**
 * This class FarmerType represents the farmer type of the player in the game
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class FarmerType {
    private String typeName;
    private int lvlRequirement;
    private int bonusEarnings;
    private int seedCostReduction;
    private int waterBonusLimitIncr;
    private int fertilizerBonusLimitIncr;
    private double registrationFee;

    /**
     * This is the constructor for class FarmerType
     * @param typeName is the name of the farmer type
     * @param lvlRequirement is the level requirement for promoting to the farmer type
     * @param bonusEarnings is the increase of earnings depending on the farmer type
     * @param seedCostReduction is the seed cost reduction depending on the farmer type
     * @param waterBonusLimitIncr is the increase of bonus water limit depending on the farmer type
     * @param fertilizerBonusLimitIncr is the increase of fertilizer limit depending on the farmer type
     * @param registrationFee is the registration fee for promoting in the chosen farmer type
     */
    public FarmerType(String typeName, int lvlRequirement, int bonusEarnings, int seedCostReduction, int waterBonusLimitIncr, int fertilizerBonusLimitIncr, int registrationFee) {
        this.typeName = typeName;
        this.lvlRequirement = lvlRequirement;
        this.bonusEarnings = bonusEarnings;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusLimitIncr = waterBonusLimitIncr;
        this.fertilizerBonusLimitIncr = fertilizerBonusLimitIncr;
        this.registrationFee = registrationFee;
    }

    /**
     * getTypeName() gets the name of the farmer type
     * @return typeName of the farmer type
     */
    public String getTypeName() {
        return typeName;
    }
    
    /**
     * getLvlRequirement() gets the level requirement for promoting to the farmer type
     * @return lvlRequirement of the farmer type
     */
    public int getLvlRequirement() {
        return lvlRequirement;
    }

    /**
     * getBonusEarnings() gets the increase of earnings depending on the farmer type
     * @return bonusEarnings of the farmer type
     */
    public int getBonusEarnings() {
        return bonusEarnings;
    }

    /**
     * getSeedCostReduction() gets the seed cost reduction depending on the farmer type
     * @return seedCostReduction of the farmer type
     */
    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    /**
     * getWaterBonusLimitIncr() gets the increase of bonus water limit depending on the farmer type
     * @return waterBonusLimitIncr of the farmer type
     */
    public int getWaterBonusLimitIncr() {
        return waterBonusLimitIncr;
    }

    /**
     * getFertilizerBonusLimitIncr() gets the increase of fertilizer limit depending on the farmer type
     * @return fertilizerBonusLimitIncr of the farmer type
     */
    public int getFertilizerBonusLimitIncr() {
        return fertilizerBonusLimitIncr;
    }

    /**
     * getRegistrationFee() gets the registration fee for promoting in the chosen farmer type
     * @return registrationFee of the farmer type
     */
    public double getRegistrationFee() {
        return registrationFee;
    }

}
