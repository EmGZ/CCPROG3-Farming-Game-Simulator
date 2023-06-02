/** 
 * This class HarvestReport represents the report that will be displayed which includes
 * name of the crop, numbers of crop to be gained and the profit of the harvest.
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class HarvestReport{

    private String cropName;
    private int cropGain;
    private double profit;

    /**
     * This is the constructor for HarvestReport Class
     * @param cropName is the name of the crop that is harvested
     * @param cropGain is the number of crop yields
     * @param profit is the amount of objectCoin the player gained from harvesting
     */
    public HarvestReport(String cropName, int cropGain, double profit) {
        this.cropName = cropName;
        this.cropGain = cropGain;
        this.profit = profit;
    }


    /**
     * @return Harvest Report of the harvesting
     */
    public String getMessage() {
        return "[ You have gained " + cropGain + " of " + cropName + " with a profit of " + profit + " coins !!! ]\n";
    }

}