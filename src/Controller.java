import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class Controller represents the executer of actions made by the player within the farming game.
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 */
public class Controller implements ActionListener, DocumentListener {
    MyGUI gui;
    Model1 model;
    Dialogs myDialogs;

    private int prev = 0;
    private boolean select = false;
    private final List<Integer> randomGenList = new ArrayList<>();


    /**
     * Controller() is controller for the farming game
     * @param gui is the GUI of the game
     * @param model contains the game model (Tile, Player, Tools and Seeds)
     * @param myDialogs contains the pop-up windows used in the GUI
     */
    public Controller(MyGUI gui, Model1 model, Dialogs myDialogs) {
        this.gui = gui;
        this.model = model;
        this.myDialogs = myDialogs;


        gui.setActionListener(this);
        gui.setDocumentListener(this);
        myDialogs.insertListener(this);
        tileInit();
        rocksInit();
        updateView();
    }

    /***
     * gameInit() initializes instances of MyGUI, Model1,myDialogs, and fields of Controller
     */
    public void gameInit(){
        gui.dispose();
        gui = new MyGUI();

        model = new Model1();

        myDialogs.dispose();
        myDialogs = new Dialogs(gui);

        select = false;
        randomGenList.clear();

        gui.setActionListener(this);
        gui.setDocumentListener(this);
        myDialogs.insertListener(this);
        tileInit();
        rocksInit();
    }

    /**
     * tileInit initializes new Tiles and wither message (if there is)
     */
    public void tileInit() {
        Tile[] tile = new Tile[50];
        for(int i = 0; i < 50; i++) {
            tile[i] = new Tile();
        }
        model.setTile(tile);
    }

    /**
     * getRandomNumber() generates a random number
     * @param min is the minimum number prompted
     * @param max is the maximum number prompted
     * @return the generated randomized number
     */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * rocksInit() initializes the rock layout of the tiles depending on the text file input
     * and also generates new rock layout text file for the next usage
     */
    public void rocksInit(){
        try {
            JButton[][] tileA = gui.getTileButton();
            Tile[] tileC = model.getTile();
            List<Integer> rocklist = new ArrayList<>();
            //determines how many tiles will be blocked
            int rockNumber = getRandomNumber(10, 31);

            String line;
            BufferedReader reader = new BufferedReader(new FileReader("rocks.txt"));
            while((line = reader.readLine()) != null){
                int value = Integer.parseInt(line);
                //populate array with random values from text file
                rocklist.add( value);
            } reader.close();

            // for random number of rocks "rockNumber", generate a random index "get" that will fetch corresponding line value in text file (stored in an array: rockList)
            for (int rock = 0; rock < rockNumber; rock++) {
                // repeat random generation of number if the generated value was repeated or used already.
                int get;
                do {
                    get = getRandomNumber(1, rocklist.size());
                } while (randomGenList.contains(rocklist.get(get)));
                //stores "get" values to track available numbers
                randomGenList.add(rocklist.get(get));

                tileA[(rocklist.get(get) - 1) / 5][(rocklist.get(get) - 1) % 5].setBackground(new Color(119, 1, 23));
                tileA[(rocklist.get(get) - 1) / 5][(rocklist.get(get)- 1) % 5].setOpaque(true);
                tileA[(rocklist.get(get) - 1) / 5][(rocklist.get(get) - 1) % 5].setBorderPainted(false);
                //change tile hasRocks to true
                tileC[(rocklist.get(get)) - 1].setHasRocks(true);
                //updates tile in tile array
                model.setTile(tileC);
                gui.setTileButton(tileA);

            }
            updateView();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            //overwrite the text file with new random numbers to be used on next run
            BufferedWriter writer = new BufferedWriter(new FileWriter("rocks.txt"));
            for (int count = 0; count < 50; count++){
                int save = getRandomNumber(1,51);
                writer.write("" + save + "\n");
            } writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * updateJDialog updates the icons in Dialogs and the numbering system in seedBook.
     */
    public void updateJDialog() {
        checkButton();
        myDialogs.seedBookNumberingUpdate(model.getCurrNum() + 1);
        // changes the image in seedbook when buttons are clicked
        myDialogs.setSeed(model.getCurrCrop().getIcon());
        myDialogs.setSeed1(model.getCurrCrop().getIcon());
    }


    /**
     * updateView() updates the view of the GUI depending on the actions of the player
     */
    public void updateView() {
        // PANEL 3
        gui.displayPlayerInfo(model.getPlayerName(), model.getObjectCoin(), model.getPlayerLevel(), model.getPlayerExp(), model.getPlayerFarmerTypeName(), model.getDayCount());
        levelUp(model.getPlayerExp());
        checkButton();

        //Farming Lot
        // if no tiles are selected, disable all tools
        if (!this.select) {
            disableAll();
        } else {
            // checks if selected tile is plowed or not
            checkTools(this.prev - 1);
        }
        //Enabler for Register Buttons
        setEnabler();

        var result = checkStatus();
        if(result.getStatus() == false) {
            var choice = myDialogs.newGameConfirmation(result.getMessage());
            if (choice == 0)
                gameInit();
            else
                gui.dispose();
        }
    }


    /**
     * checkStatus() checks condition to end game
     * @return ActionStatus -  a class which prints a message when returned
     */
    public ActionStatus checkStatus(){
        var result = !(!checkPlayerObjectCoinsEnough() && !checkAllTileNotOccupied()) && checkAllTilesNotWithered();
        var message = "";
        if ((!checkPlayerObjectCoinsEnough() && !checkAllTileNotOccupied()))
            message = "You do not have enough coins to plant more seed.";
        else if (!checkAllTilesNotWithered())
            message = "All tiles contained withered crop.";
        return new ActionStatus(result, message);
    }


    /**
     * checkAllTileNotOccupied is a method that checks whether all tiles are occupied
     * @return false when all tiles are occupied
     */
    public boolean checkAllTileNotOccupied(){
        if (model.getPlayer().getDayCount() >= 1){
            Tile[] tile = model.getTile();
            int count = 0;
            for (int i = 0; i < 50; i++){
                if (tile[i].getPlantedCrop() == null){
                    count++;
                }
                else if (tile[i].getPlantedCrop() != null){
                    if(tile[i].getPlantedCrop().isWithered())
                        count ++;
                }
            }
            if (count == 50){
                return false;
            }
        }
        return true;
    }


    /**
     * checkPlayerObjectCoinsEnough() checks if the player's coins is enough to play the game further
     * @return true if the coins are enough
     */
    public boolean checkPlayerObjectCoinsEnough(){
        return (model.getPlayer().getObjectCoin() >= 5);
    }


    /**
     * checkAllTilesNotWithered() checks all tiles has withered crop
     * @return true if all tiles hasn't withered
     */
    public boolean checkAllTilesNotWithered(){
        if (model.getPlayer().getDayCount() > 1) {
            Tile[] tile = model.getTile();
            int count = 0;
            for (int i = 0; i < 50; i++){
                if (tile[i].getPlantedCrop() != null)
                    if(tile[i].getPlantedCrop().isWithered())
                        count++;
            }
            if (count == 50){
                return false;
            }
        }
        return true;
    }


    //reference from  driver to controller is the same for MyGui, Model class sooo i can just call the instance here and initialize again or set it to null

    /**
     * checkButton() checks if the player is on the first/last pages of the seedbook and
     * disables left button if on the first page; disables right button if on the last page
     */
    public void checkButton() {
        if (model.isFirstSeed()){
            myDialogs.getPrevSBButton().setEnabled(false);
            myDialogs.getPrevSBButton2().setEnabled(false);
            myDialogs.getNextSBButton().setEnabled(true);
            myDialogs.getNextSBButton2().setEnabled(true);
        }
        else if (model.isLastSeed()) {
            myDialogs.getNextSBButton().setEnabled(false);
            myDialogs.getNextSBButton2().setEnabled(false);
            myDialogs.getPrevSBButton().setEnabled(true);
            myDialogs.getPrevSBButton2().setEnabled(true);
        }
        else {
            myDialogs.getNextSBButton().setEnabled(true);
            myDialogs.getPrevSBButton().setEnabled(true);
            myDialogs.getNextSBButton2().setEnabled(true);
            myDialogs.getPrevSBButton2().setEnabled(true);
        }
    }
    /**
     * disableAll() disables all the tool buttons
     */
    public void disableAll() {
        gui.getPlantButton().setEnabled(false);
        gui.getWaterButton().setEnabled(false);
        gui.getPlowButton().setEnabled(false);
        gui.getFertilizeButton().setEnabled(false);
        gui.getShovelButton().setEnabled(false);
        gui.getPickaxeButton().setEnabled(false);
        gui.getHarvestButton().setEnabled(false);
    }

    /**
     * checkTools() enables the tool buttons that the player can use depending on the conditions
     * @param i is the index of the tile that has been selected by the player
     */
    public void checkTools(int i) {
        Tile[] tile = model.getTile();
        disableAll();
        if(this.select == true) {
            gui.getShovelButton().setEnabled(true);
        }
        if(!tile[i].isPlowed()) {
            gui.getPlowButton().setEnabled(true);
        }
        if(tile[i].getHasRocks()){
            gui.getPlowButton().setEnabled(false);
            gui.getPickaxeButton().setEnabled(true);
        }
        if(tile[i].isPlowed() && tile[i].getPlantedCrop() == null) {
            gui.getPlantButton().setEnabled(true);
        }
        if(tile[i].isPlowed() && tile[i].getPlantedCrop() != null) {
            if (!tile[i].getPlantedCrop().isWithered()) {
                gui.getWaterButton().setEnabled(true);
                gui.getFertilizeButton().setEnabled(true);
            }
            if (tile[i].isHarvestable() && !tile[i].getPlantedCrop().isWithered()) {
                gui.getHarvestButton().setEnabled(true);
            }
        }

    }
    /**
     * setEnabler() enables the button of the farmer types depending if the player is eligible to promote
     */
    public void setEnabler() {
        myDialogs.getRegisterButtREG().setEnabled(checkAll("Registered Farmer"));
        myDialogs.getRegisterButtDIS().setEnabled(checkAll("Distinguished Farmer"));
        myDialogs.getRegisterButtLEG().setEnabled(checkAll("Legendary Farmer"));
    }

    /**
     * plantColor() sets a color of the tile depending on the planted crop
     * @param tile is the selected tile from the farm lot
     * @param tileB is the button of the tile
     */
    public void plantColor(Tile tile, JButton tileB){
        if(tile.getPlantedCrop().getName().equals("Turnip"))
            tileB.setBackground(new Color(145,204,110));
        else if(tile.getPlantedCrop().getName().equals("Carrot"))
            tileB.setBackground(new Color(250,131,0));
        else if(tile.getPlantedCrop().getName().equals("Potato"))
            tileB.setBackground(new Color(246,205,78));
        else if(tile.getPlantedCrop().getName().equals("Rose"))
            tileB.setBackground(new Color(206,101,106));
        else if(tile.getPlantedCrop().getName().equals("Tulips"))
            tileB.setBackground(new Color(255,88,45));
        else if(tile.getPlantedCrop().getName().equals("Sunflower"))
            tileB.setBackground(new Color(166,127,5));
        else if(tile.getPlantedCrop().getName().equals("Mango"))
            tileB.setBackground(new Color(240,159,17));
        else if(tile.getPlantedCrop().getName().equals("Apple"))
            tileB.setBackground(new Color(184,0,22));
    }

    /**
     * checkLot() checks all the tiles if its planted crop is harvestable or has withered
     * @param tile is the whole Farm Lot tiles
     * @param tileB is the tile button in the GUI
     */
    public void checkLot(Tile[] tile, JButton[][] tileB) {
        for(int i = 0; i < 50; i++) {
            if (tile[i].getPlantedCrop() != null) {
                if (tile[i].getPlantedCrop().getAge() == tile[i].getPlantedCrop().getHarvestTime() &&
                        (tile[i].getPlantedCrop().getCurrentWater() >= tile[i].getPlantedCrop().getNeededWater() &&
                                tile[i].getPlantedCrop().getCurrentFertilizer() >= tile[i].getPlantedCrop().getNeededFertilizer())) {
                    tile[i].setHarvestable(true);
                    tileB[i / 5][i % 5].setBackground(new Color(190, 255, 160));

                } else if ((tile[i].getPlantedCrop().getAge() >= tile[i].getPlantedCrop().getHarvestTime() && !tile[i].isHarvestable()) ||
                        (tile[i].isHarvestable() && tile[i].getPlantedCrop().getAge() > tile[i].getPlantedCrop().getHarvestTime())) {
                    tile[i].getPlantedCrop().setWithered(true);
                    tile[i].witherReason();
                    tileB[i / 5][i % 5].setBackground(new Color(130, 130, 130));
                }
            }
        }
    }


    /**
     * checkRegistrationFee() checks if player has enough objectCoins to register
     * @param type is the type of farmer that the player had prompted
     * @return true/false if the conditions are met
     */
    public boolean checkRegistrationFee(String type){
        return  model.getObjectCoin() >= model.getPlayerFarmerType(type).getRegistrationFee();
    }

    /**
     * checkLevel() checks if the player level is greater than/ equal the level required.
     * @param type is the farmer type
     * @return true/false if the conditions are met
     */
    public boolean checkLevel (String type){
        return model.getPlayerLevel() >= model.getFarmerTypeLevel(type);
    }
    /**
     * checkRegistration() checks if player is already registered in one of the available promotion (if false, then negate to return as true in checkAll)
     * @param type is the type of farmer that the player had prompted
     * @return true/false if the conditions are met
     */
    public boolean checkRegistration(String type){
        return !model.getPlayerFarmerTypeName().equals(type);
    }

    /**
     * checkPastRegistration() checks if player follows hierarchy of promotion
     * @param type is the type of farmer that the player had prompted
     * @return true/false if the conditions are met
     */
    public boolean checkPastRegistration(String type){
        // if player reached level 5 (registered player) enter condition
        if (type.equals("Registered Farmer") && !(model.getPlayerFarmerTypeName().equals("Distinguished Farmer") || model.getPlayerFarmerTypeName().equals("Legendary Farmer"))) {
            return checkLevel("Registered Farmer");
        }
        else if(type.equals("Distinguished Farmer")){
            return model.getPlayerFarmerTypeName().equals("Registered Farmer");
        }
        else if (type.equals("Legendary Farmer")){
            return model.getPlayerFarmerTypeName().equals("Distinguished Farmer");
        }
        return false;
    }

    /**
     * checkAll() checks all the requirement needed to promote the player to new farmer type
     * @param type is the type of farmer that the player had prompted
     * @return true/false if the conditions are met
     */
    public  boolean checkAll(String type){
        return (checkLevel(type) && checkRegistration(type) && checkRegistrationFee(type) && checkPastRegistration(type));
    }

    /**
     * levelUp() computes the level of the player depending on its experience pts
     * @param exp is the exp of the player
     */
    public void levelUp(double exp){
        var solve = exp/100;
        model.setPlayerLevel((int) Math.floor(solve));
    }

    /**
     * selectTile() displays the tile that the player had selected
     * @param num is the tile number that the player selected
     */
    public void selectTile(int num) {
        var status = false;
        Tile[] tile = model.getTile();
        if (tile[num - 1].getPlantedCrop() != null) {
            if (!tile[num - 1].getPlantedCrop().isWithered()) {
                status = true;
            } else if (tile[num - 1].getPlantedCrop() != null && tile[num - 1].getPlantedCrop().isWithered()) {
                gui.displayStatusMessage(tile[num - 1].getWitherStatus());
            }
            gui.displaySelectedTile(num, status, tile[num - 1]);
        } else {
            // clears message whenever Next Day is clicked
            gui.displayStatusMessage("");
            gui.displaySelectedTile(num, status, tile[num - 1]);
        }
    }

    /**
     * nameChange() prompts the player to remain in the Enter Name panel if they didn't type anything
     */
    public void nameChanged(){
        // if user does not type in anything end clicked "enter", do not show "next" button  [https://stackoverflow.com/a/58614074/20623821]
        if (!gui.getTheName().isEmpty())
            gui.getCardLO().show(gui.getPanelContainer(), "panel 3");
    }

    /**
     * registerPlayer() registers player to a farmertype promotion
     * @param type is the type of farmer that the player prompted to apply to
     */
    public void registerPlayer (String type){
        if (myDialogs.promotionConfirmation(model.getPlayerFarmerType(type).getRegistrationFee()) == 0){
            model.setPlayerFarmerType(type);
            model.setObjectCoin(model.getObjectCoin() - model.getPlayerFarmerType(type).getRegistrationFee());
            Tile[] tile = model.getTile();
            for(int i = 0; i < 50; i++) {
                if(tile[i].getPlantedCrop() != null) {
                    if(!tile[i].getPlantedCrop().isWithered()) {
                        tile[i].setPlantedCrop(tile[i].farmerBonus(tile[i].getPlantedCrop(), model.getPlayer()));
                    }
                }
            }
            updateView();
        }
    }

    /**
     * treeCheck() checks if the crop to be planted is a tree, not on the edge of the farm lot
     * and doesn't have any occupied tile near where its going to be planted
     * @param tile if the farm
     * @param num is the tile number of the selected tile
     * @param cType is the type of the crop to be checked
     * @return true/false if the tree crop can be planted
     */
    public boolean treeCheck(Tile[] tile, int num, String cType) {
        if(cType.equals("Fruit Tree")) {
            var edge = num % 10;
            System.out.println(edge);
            if(num < 11 || num > 40 || edge == 1 || edge == 0) {
                return true;
            } else {
                var i = num - 1;
                int[] adj = new int[8];
                adj[0] = i - 10;
                adj[1] = i + 10;
                adj[2] = i - 1;
                adj[3] = i + 1;
                adj[4] = i - 11;
                adj[5] = i - 9;
                adj[6] = i + 9;
                adj[7] = i + 11;
                for(int j = 0; j < 8; j++) {
                    if(tile[adj[j]].getPlantedCrop() != null || tile[adj[j]].getHasRocks())
                        return true;
                }
            }
        }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String type;
        //checker only - DELETE LATER
        String cmd = e.getActionCommand();
        System.out.printf("command received is %s\n ", cmd);

        if (e.getActionCommand().equals("Start Game")) {
            gui.getCardLO().show(gui.getPanelContainer(), "panel 2");
        }

        if (e.getActionCommand().equals("Enter")){
            model.setPlayerName(gui.getTheName());
            gui.setTheName(model.getPlayerName());
            nameChanged();
            updateView();
        }

        if(e.getActionCommand().equals("Guide")){
            myDialogs.showGuide();
        }

        if(e.getActionCommand().equals("Seed Book")){
            myDialogs.showSeedBook(model.getCurrCrop().getIcon());
        }
        if(e.getActionCommand().equals("New Game")){
            gameInit();
        }

        if (e.getActionCommand().equals("Close Seed Book")) {
            myDialogs.getSeedBookPopup().dispose();
            model.setCurrNum(0);
            updateView();
        }

        if(e.getSource() == myDialogs.getClosePCButton()){
            myDialogs.getPlantChoices().dispose();
            model.setCurrNum(0);
            updateView();
        }

        if(e.getSource() == myDialogs.getPrevSBButton()){
            model.prevSeed();
            updateJDialog();
        }

        if(e.getSource() == myDialogs.getNextSBButton()){
            model.nextSeed();
            updateJDialog();
        }
        if(e.getSource() == myDialogs.getPrevSBButton2()){
            model.prevSeed();
            updateJDialog();
        }
        if(e.getSource() == myDialogs.getNextSBButton2()){
            model.nextSeed();
            updateJDialog();
        }

        // ACTION EVENT: Selecting tiles
        JButton[][] tileB = gui.getTileButton();
        for(int i = 1; i <= 50; i++) {
            if(e.getActionCommand().equals("" + i)){
                //if first selected tile is already selected, disable button
                if(this.prev == 0) {
                    tileB[(i-1) / 5][(i-1) % 5].setEnabled(false);
                }
                else {
                    // if another tile is selected, enable the previous selected tile and disable the newly selected tile
                    tileB[(prev-1) / 5][(prev-1) % 5].setEnabled(true);
                    tileB[(i-1) / 5][(i-1) % 5].setEnabled(false);
                }
                // display selected tile of JLabel above tools
                gui.displayStatusMessage("");
                selectTile(i);

                // uncomment if mac user :))
                tileB[(i-1) / 5][(i-1) % 5].setOpaque(true);
                tileB[(i-1) / 5][(i-1) % 5].setBorderPainted(false);

                //update GUI buttons to conditions above
                gui.setTileButton(tileB);
                // update prev to the index + 1 of selected tile
                this.prev = i;
                //updates the checker for whether tile is plowed/unplowed
                this.select = true;

                updateView();
            }
        }

        if(e.getActionCommand().equals("Plant")){
            myDialogs.showPlantChoices(model.getCurrCrop().getIcon());
        }

        //ACTION EVENT - PLOW
        Tile[] tile = model.getTile();
        if(e.getActionCommand().equals("Plow")) {
            var plow = tile[prev - 1].plow(model.getTool(0), model.getPlayer());
            //updates tile in tile array
            model.setTile(tile);
            tileB[(prev-1) / 5][(prev-1) % 5].setBackground(new Color(150,75,0));

            //for MAC users
            tileB[(prev-1) / 5][(prev-1) % 5].setOpaque(true);
            tileB[(prev-1) / 5][(prev-1) % 5].setBorderPainted(false);
            //updates button in GUI
            gui.setTileButton(tileB);
            gui.displayStatusMessage(plow.getMessage());
            updateView();
        }

        //ACTION EVENT - PLANT
        if(e.getActionCommand().equals("Plant Seed")){
            Crop seed = new Crop(model.getCurrCrop());

            selectTile(prev);
            if(!treeCheck(tile, prev, seed.getCropType())) {
                var plant = tile[prev - 1].plant(seed, model.getPlayer());
                gui.displayStatusMessage(plant.getMessage());
                selectTile(prev);
            } else {
                gui.displayStatusMessage("Fruit Trees can't be near an occupied tile or on edge");
            }
            if(tile[prev - 1].getPlantedCrop() != null)
                plantColor(tile[prev - 1], tileB[(prev-1) / 5][(prev-1) % 5]);

            model.setCurrNum(0);
            myDialogs.getPlantChoices().dispose();
            updateView();
        }

        // ACTION EVENT: WATER
        if(e.getActionCommand().equals("Water")){
            var water = tile[prev - 1].water(model.getTool(1), model.getPlayer());
            selectTile(prev);
            gui.displayStatusMessage(water.getMessage());
            updateView();
        }

        // ACTION EVENT: FERTILIZER
        if(e.getActionCommand().equals("Fertilize")){
            var fert = tile[prev - 1].fertilize(model.getTool(2), model.getPlayer());
            selectTile(prev);
            gui.displayStatusMessage(fert.getMessage());
            updateView();
        }

        // ACTION EVENT: PICKAXE
        if(e.getActionCommand().equals("Pickaxe")){
            var pick = tile[prev - 1].pickaxe(model.getTool(3), model.getPlayer());
            if (pick.getStatus() == true) {
                tileB[(prev - 1) / 5][(prev - 1) % 5].setBackground(new Color(230, 157, 84));
            }
            selectTile(prev);
            gui.displayStatusMessage(pick.getMessage());
            updateView();
        }

        // ACTION EVENT: SHOVEL
        if(e.getActionCommand().equals("Shovel")){
            var dig = tile[prev - 1].dig(model.getTool(4), model.getPlayer());
            if(!tile[prev - 1].getHasRocks()) {
                tileB[(prev-1) / 5][(prev-1) % 5].setBackground(new Color(230, 157, 84));
            }
            selectTile(prev);
            gui.displayStatusMessage(dig.getMessage());
            updateView();
        }

        // ACTION EVENT: HARVEST
        if(e.getActionCommand().equals("Harvest")){
            var harvest = tile[prev - 1].harvest(model.getPlayer());
            tile[prev - 1].setPlantedCrop(null);
            tileB[(prev-1) / 5][(prev-1) % 5].setBackground(new Color(230, 157, 84));
            selectTile(prev);
            gui.displayStatusMessage(harvest.getMessage());

            updateView();
        }

        // ACTION EVENT: NEXT DAY
        if(e.getActionCommand().equals("Next Day")){
            model.setDayCount(model.getDayCount() + 1);
            for(int i = 0; i < 50; i++) {
                if(tile[i].getPlantedCrop() != null) {
                    if(!tile[i].getPlantedCrop().isWithered())
                        tile[i].getPlantedCrop().setAge(tile[i].getPlantedCrop().getAge() + 1);
                }
            }
            checkLot(tile, tileB);
            updateView();
            gui.displayStatusMessage("");
            if(select == true)
            	selectTile(prev);
        }

        if (e.getActionCommand().equals("<html><center>Promote<br />Farmer</center></html>")){
            myDialogs.showPromoteFarmer();
            updateView();
        }

        if (e.getActionCommand().equals("Requirements")){
            myDialogs.showRequirements();
        }

        if (e.getActionCommand().equals("Close")){
            myDialogs.getRequirementsPopup().dispose();
        }

        if(e.getSource() == myDialogs.getRegisterButtREG()){
            type = "Registered Farmer";
            registerPlayer(type);
        }
        if(e.getSource() == myDialogs.getRegisterButtDIS()){
            type = "Distinguished Farmer";
            registerPlayer(type);
        }
        if(e.getSource() == myDialogs.getRegisterButtLEG()){
            type = "Legendary Farmer";
            registerPlayer(type);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}