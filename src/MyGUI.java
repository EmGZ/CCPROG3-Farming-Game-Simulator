import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class MyGUI represents the graphic interface of the game which contains 3 panels where
 * 1st Panel is the start game panel, 2nd Panel is the Inputting of Player name
 * and 3rd panel where the farming game take place
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 *
 */
public class MyGUI extends JFrame {
    private CardLayout cardLO;
    private JPanel panelContainer;
    private JPanel panelFirst;
    private JPanel panelSecond;
    private JPanel panelThird;

    //Start or first Panel
    private JButton welcButton;

    //second Panel
    private JTextField theName;
    private JButton enterButton;

    //third Panel

    //SUB-panel 1
    private JLabel displayPlayer;
    private JLabel displayCoins;
    private JLabel displayPlLevel;
    private JLabel displayPlExp;
    private JLabel displayPlType;
    private JLabel displayDay;

    //SUB-panel 2
    private JButton guideButton;
    private JButton seedBook;
    private JButton newGame;

    //SUB-panel 3
    private JLabel displaySelected;
    private JButton waterButton;
    private JButton fertilizeButton;
    private JButton plowButton;
    private JButton pickaxeButton;
    private JButton shovelButton;
    private JButton plantButton;
    private JButton harvestButton;
    private JLabel displayMessage;

    //SUB-panel 4
    private JButton nextdayButton;
    private JButton registerButton;

    //CENTER-panel
    private JButton[][] tileButts;
    private int tileCnt;

    // constructor
    public MyGUI(){
        super("My Farm");
        setLayout(new BorderLayout());

        // set icon of window
        ImageIcon icon = new ImageIcon("farmingicon.jpg");
        setIconImage(icon.getImage());

        // set size of window
        setSize(800, 500);

        // set default to EXIT when window is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set window at the center of screen
        setLocationRelativeTo(null);

        //method for the layout of elements
        initWelcome();
        //init();

        //explicitly set the visible to true and resizable to false
        setVisible(true);
        setResizable(false);
    }


    /**
     * intiWelcome() initializes the 3 panels of the GUI
     */
    private void initWelcome(){

        //First Panel
        panelContainer = new JPanel();
        cardLO = new CardLayout();
        panelContainer.setLayout(cardLO);

        //first Panel
        panelFirst = firstPanel();
        panelContainer.add(panelFirst, "panel 1");

        // second Panel
        panelSecond = secondPanel();
        panelContainer.add(panelSecond, "panel 2");

        // third Panel (Game screen)
        panelThird = thirdPanel();
        panelContainer.add(panelThird, "panel 3");
        cardLO.show(panelContainer, "panel 1");

        this.add(panelContainer, BorderLayout.CENTER);
    }

    /**
     * firstPanel() initializes the first panel of the gui where player can start the game
     * @return the first panel of the gui
     */
    public JPanel firstPanel(){
        panelFirst = new JPanel(new BorderLayout());
        //Background
        ImageIcon farmBG = new ImageIcon("farmingBG.gif");
        JLabel background = new JLabel(farmBG);
        panelFirst.add(background);
        background.setLayout(new GridBagLayout());
        //panelFirst.setBackground(Color.blue);

        //panel specs:
        GridBagConstraints GBC =  new GridBagConstraints();
        GBC.gridy = 1;
        GBC.insets = new Insets(10,10,10,10);
        GBC.ipady = 15;

        JLabel welcLbl = new JLabel("Welcome to MyFarm!");
        welcLbl.setFont(new Font("Impact", Font.PLAIN, 20));
        welcButton = new JButton("Start Game");

        background.add(welcLbl);
        background.add(welcButton, GBC);

        return panelFirst;

    }

    /**
     * secondPanel() initializes the second panel of the gui where player can input their name
     * @return the second panel of the gui
     */
    public JPanel secondPanel() {
        panelSecond = new JPanel(new GridBagLayout());
        panelSecond.setBackground(new Color(255,247,212));


        JLabel askName = new JLabel("Please type your name: ");
        theName = new JTextField(10);

        panelSecond.add(askName);
        panelSecond.add(theName);

        enterButton = new JButton("Enter");
        panelSecond.add(enterButton);

        return panelSecond;
    }

    /**
     * thirdPanel() initializes the third panel of the gui where player can play the farming game
     * @return the first panel of the gui
     */
    public JPanel thirdPanel(){
        panelThird = new JPanel(new BorderLayout());

        //SUB-PANEL 1 - NORTH of panelThird
        JPanel panelThirdSub1 = new JPanel(new GridLayout(2, 3, 20, 10));
        panelThirdSub1.setBackground(new Color(188, 253, 236));
        displayPlayer = new JLabel();
        displayCoins = new JLabel();
        displayPlLevel = new JLabel();
        displayPlExp = new JLabel();
        displayPlType = new JLabel();
        displayDay = new JLabel();

        panelThirdSub1.add(displayPlayer);
        panelThirdSub1.add(displayCoins);
        panelThirdSub1.add(displayPlLevel);
        panelThirdSub1.add(displayPlExp);
        panelThirdSub1.add(displayPlType);
        panelThirdSub1.add(displayDay);

        panelThird.add(panelThirdSub1, BorderLayout.NORTH);

        //SUB-PANEL 2 - EAST of panelThird
        JPanel panelThirdSub2 = new JPanel(new GridBagLayout());
        panelThirdSub2.setBackground(new Color(255,247,212));
        GridBagConstraints GBC = GBC();

        guideButton = new JButton("Guide");
        seedBook = new JButton("Seed Book");
        newGame = new JButton("New Game");

        panelThirdSub2.add(guideButton, GBC);
        panelThirdSub2.add(seedBook, GBC);
        panelThirdSub2.add(newGame, GBC);

        panelThird.add(panelThirdSub2, BorderLayout.EAST);

        //SUB-PANEL 3 - SOUTH of panelThird
        JPanel panelThirdSub3 = new JPanel(new GridBagLayout());
        panelThirdSub3.setBackground(new Color(196, 164, 132));

        displaySelected = new JLabel();
        displayMessage = new JLabel();
        waterButton = new JButton("Water");
        fertilizeButton = new JButton("Fertilize");
        plowButton = new JButton("Plow");
        pickaxeButton = new JButton("Pickaxe");
        shovelButton = new JButton("Shovel");
        plantButton = new JButton("Plant");
        harvestButton = new JButton("Harvest");

        panelThirdSub3.add(displayMessage, GBC);
        panelThirdSub3.add(displaySelected,GBC);
        panelThirdSub3.add(waterButton);
        panelThirdSub3.add(fertilizeButton);
        panelThirdSub3.add(plowButton);
        panelThirdSub3.add(plantButton);
        panelThirdSub3.add(harvestButton);
        panelThirdSub3.add(pickaxeButton);
        panelThirdSub3.add(shovelButton);


        panelThird.add(panelThirdSub3, BorderLayout.SOUTH);

        //SUB-PANEL 4 - WEST of panelThird
        JPanel panelThirdSub4 = new JPanel(new GridBagLayout());
        panelThirdSub4.setBackground(new Color(255,247,212));
        nextdayButton = new JButton("Next Day");
        registerButton = new JButton();
        registerButton.setText("<html><center>Promote<br />Farmer</center></html>");

        panelThirdSub4.add(nextdayButton, GBC);
        panelThirdSub4.add(registerButton, GBC);
        panelThird.add(panelThirdSub4, BorderLayout.WEST);

        //Center Panel for Tiles
        JPanel tilePanel = new JPanel();
        tilePanel.setPreferredSize(new Dimension(500, 250));
        tilePanel.setBackground(new Color(134,255,142));
        tilePanel.setLayout(new GridLayout(5,10));
        tileButts = new JButton [10][5];
        // tile button label (1-50)
        tileCnt = 0;
        int x = 0, y = 0;
        for(int r = 0; r < 10; r++) {
            for(int c = 0; c < 5; c++) {
                tileCnt ++;
                tileButts[r][c] = new JButton("" + tileCnt);
                tileButts[r][c].setBounds(x, y, 50, 50);
                tileButts[r][c].setBackground(new Color(230, 157, 84));
                // uncomment if mac user :>
                tileButts[r][c].setOpaque(true);
                tileButts[r][c].setBorderPainted(false);

                tilePanel.add(tileButts[r][c]);
                x = x + 50;
            }
            if(x == 500) {
                x -= 500;
                y += 50;
            }
        }

        panelThird.add(tilePanel, BorderLayout.CENTER);

        return panelThird;
    }

    /**
     * GBC() initializes the constraints for the grid bag layout
     * @return constraints for the grid bag layout
     */
    public GridBagConstraints GBC(){
        GridBagConstraints GBC = new GridBagConstraints();

        GBC.gridy = GridBagConstraints.RELATIVE;
        GBC.gridwidth = GridBagConstraints.REMAINDER;

        return GBC;
    }

    /**
     * displayPlayerInfo() displays the player in the GUI
     * @param name is the name of the player
     * @param objectCoins is the object coins of the player
     * @param playerLevel is level of the player
     * @param playerExp is the experience points of the player
     * @param farmerType is the farmer type of the player
     * @param dayCount is the number of days passed in the game
     */
    public void displayPlayerInfo(String name, double objectCoins, int playerLevel, double playerExp, String farmerType, int dayCount){
        displayPlayer.setText("Player Name: " + name);
        displayCoins.setText("Object Coins = " + objectCoins);
        displayPlLevel.setText("Farmer's Level: " + playerLevel );
        displayPlExp.setText("Farmer Exp: " + playerExp);
        displayPlType.setText("Farmer Type: " + farmerType );
        displayDay.setText("Day: " + dayCount);
    }

    /**
     * displaySelectedTile() displays the information about the selected tile
     * @param tileNum is tile number of the tile that has been selected
     * @param status is status if there's a planted crop in the selected tile
     * @param tile is the selected tile in the game
     */
    public void displaySelectedTile(int tileNum, boolean status, Tile tile){
        if (status == true){
            var myString = "Selected Tile: " + tileNum + "\n\nCrop " + tile.getPlantedCrop();
            displaySelected.setText("<html><center>" + myString.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>").replaceAll("\t", "&emsp;") + "</center></html>"); //[https://stackoverflow.com/a/36815232/20623821]
        }
        else
            displaySelected.setText("Selected Tile: " + tileNum);

    }

    /**
     * displayStatusMessage() displays the action made by the player
     * @param message is the message of the action done by the player
     */
    public void displayStatusMessage(String message){
        displayMessage.setText(message);
    }


    /**
     * setActionListener() initializes the action listener for each button in the GUI
     * @param listener is the action listener within the game
     */
    public void setActionListener(ActionListener listener){
        welcButton.addActionListener(listener);
        enterButton.addActionListener(listener);
        guideButton.addActionListener(listener);
        nextdayButton.addActionListener(listener);
        seedBook.addActionListener(listener);
        newGame.addActionListener(listener);
        registerButton.addActionListener(listener);
        for(int r = 0; r < 10; r++) {
            for(int c = 0; c < 5; c++) {
                tileButts[r][c].addActionListener(listener);
            }
        }
        plowButton.addActionListener(listener);
        plantButton.addActionListener(listener);
        waterButton.addActionListener(listener);
        fertilizeButton.addActionListener(listener);
        harvestButton.addActionListener(listener);
        shovelButton.addActionListener(listener);
        pickaxeButton.addActionListener(listener);

    }


    /**
     * setDocumentListener() initializes the document listener for the getting the player name within the GUI
     * @param listener is the document listener within the game
     */
    public void setDocumentListener(DocumentListener listener){
        theName.getDocument().addDocumentListener(listener);
    }

    // MAIN PANEL GETTER AND SETTER
    /**
     * getCardLO() gets the card layout from the GUI
     * @return card layout
     */
    public CardLayout getCardLO() {
        return cardLO;
    }


    /**
     * getPanelContainer() gets the panel container of the GUI
     * @return panel container of the GUI
     */
    public JPanel getPanelContainer() {
        return panelContainer;
    }

    // SECOND PANEL GETTER AND SETTER

    /**
     * setTheName() sets the name entered by the player
     * @param name is the name entered by the player
     */
    public void setTheName(String name){
        theName.setText(name);
    }

    /**
     * getTheName() gets the name entered by the player
     * @return name entered by the player
     */
    public String getTheName(){
        return theName.getText();
    }

    //THIRD PANEL

    /**
     * getWaterButton() gets the water button within the GUI
     * @return waterButton of the GUI
     */
    public JButton getWaterButton() {
        return waterButton;
    }


    /**
     * getFertilizeButton() gets the fertilize button within the GUI
     * @return fertilizeButton of the GUI
     */
    public JButton getFertilizeButton() {
        return fertilizeButton;
    }

    /**
     * getPlowButton() gets the plow button within the GUI
     * @return plowButton of the GUI
     */
    public JButton getPlowButton() {
        return plowButton;
    }

    /**
     * getPickaxeButton() gets the pick axe button within the GUI
     * @return pickaxeButton of the GUI
     */
    public JButton getPickaxeButton() {
        return pickaxeButton;
    }


    /**
     * getShovelButton() gets the shovel button within the GUI
     * @return shovelButton of the GUI
     */
    public JButton getShovelButton() {
        return shovelButton;
    }

    /**
     * getPlantButton() gets the plant button within the GUI
     * @return plantButton of the GUI
     */
    public JButton getPlantButton() {
        return plantButton;
    }

    /**
     * getHarvestButton() gets the harvest button within the GUI
     * @return harvestButton of the GUI
     */
    public JButton getHarvestButton() {
        return harvestButton;
    }

    /**
     * getTileButton() gets the tile buttons within the GUI
     * @return tileButts of the GUI
     */
    public JButton[][] getTileButton() {
        return tileButts;
    }

    /**
     * setTileButton() sets the tile buttons within the GUI
     * @param tileButts is the tile buttons within the GUI
     */
    public void setTileButton(JButton[][] tileButts) {
        this.tileButts = tileButts;
    }


}
