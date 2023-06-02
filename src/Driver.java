/**
 * This class Driver is where all components (GUI, Model, Dialogs, Controller) are being run.
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class Driver {
    /**
     * main() is the main function of the farming game
     * @param args - contains command lines of the game
     */
    public static void main(String[] args){
        MyGUI gui = new MyGUI();
        Model1 model = new Model1();
        Dialogs dialogs = new Dialogs(gui);

        new Controller(gui, model, dialogs);

    }
}
