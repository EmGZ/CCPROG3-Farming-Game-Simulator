/** 
 * This class represents the tools which will be used by the player in the game. It contains name to identify each tool
 * and the cost of each tool.
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class Tool{

    private String name;
    private int cost;
    private double expGain;

	/**
	 * This is the constructor for the class Tool
	 * @param name is the name of the tool
	 * @param cost is the cost of using the tool
	 * @param exp is the exp gained from using the tool
	 */
	public Tool(String name, int cost, double exp){
        this.name = name;
        this.cost = cost;
        this.expGain = exp;
    }
	
    /**
     * getName() gets the name of the tool
     * @return name of the tool
     */
    public String getName() {
        return name;
    }

    /**
     * setName() sets the name of the tool
     * @param name of the tool
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getCost() gets the cost of tool usage
     * @return cost of the tool usage
     */
    public int getCost() {
        return cost;
    }

    /**
     * setCost() sets the cost of tool usage
     * @param cost of teh tool usage
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    /**
     * getExpGain() gets the exp gained from using the tool
     * @return expGain of the tool
     */
    public double getExpGain() {
		return expGain;
	}
    
	/**
	 * setExpGain() gets the exp gained from using the tool
	 * @param expGain of the tool
	 */
	public void setExpGain(double expGain) {
		this.expGain = expGain;
	}
}