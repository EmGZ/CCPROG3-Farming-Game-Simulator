/** 
 * This class ActionStatus represents the message that going to be displayed based on the actions
 * prompted by the player in the game.
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class ActionStatus{

    private boolean status;
    private String message;

    /**
     * This is constructor for class ActionStatus
     * @param status - true/false if the action is successful
     * @param message - message of the action initiated by the Player
     */
    public ActionStatus(boolean status, String message){
        this.status = status;
        this.message = message;
    }

    /**
     * getStatus() gets the status of the action
     * @return status of the action
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * setStatus() sets the status of the action
     * @param status of the action
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * getMessage() gets the message of the action
     * @return message of the action
     */
    public String getMessage() {
        return message;
    }

    /**
     * setMessage() sets the message of the action
     * @param message of the action
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "[ " + status + " | " + message + " ]\n";
    }

}