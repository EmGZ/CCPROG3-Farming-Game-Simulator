import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class Dialogs represents the dialog pop-ups within the game
 * @author Mary Joselle M. Cabungcal
 * @author Mary Erika L. Culala	
 *
 */
public class Dialogs extends JDialog {
  private final JButton nextSBButton = new JButton(">");
  private final JButton prevSBButton = new JButton("<");
  private final JButton nextSBButton2 = new JButton(">");
  private final JButton prevSBButton2 = new JButton("<");
  private final JButton reqrmntButton = new JButton("Requirements");
  private final JButton okayButton = new JButton("Close");
  private final JButton registerButtREG = new JButton("Register");
  private final JButton registerButtDIS = new JButton("Register");
  private final JButton registerButtLEG = new JButton("Register");
  private final JButton seedButton = new JButton("Plant Seed");
  private final JButton closeSBButton = new JButton("Close Seed Book");
  private final JButton closePCButton = new JButton("Close");
  JLabel seed = new JLabel();
  JLabel seed1 = new JLabel();
  JDialog requirementsPopup = new JDialog();
  private final JLabel SBCounter = new JLabel();
  private JDialog seedBookPopup = new JDialog();
  private JDialog plantChoices = new JDialog();

  /**
   * This is the constructor for the Class Dialogs
   * @param gui is the graphical user interface of the game
   */
  public Dialogs(MyGUI gui) {
    super(gui, true);
  }

  /**
   * showGuide is a JDialog that pops up when guide button is clicked. It contains
   * Instructions for the game.
   */
  public void showGuide() {
    JDialog tutorialPopup = new JDialog();

    // JDialog specs:
    tutorialPopup.setSize(new Dimension(450, 1000));
    // centers the window
    tutorialPopup.setLocationRelativeTo(null);
    tutorialPopup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // prevents user opening another JDialog when one is opened.
    // [https://stackoverflow.com/a/6750561/20623821]
    tutorialPopup.setModal(true);

    // Panel within the jdialog (to layout elements easily)
    JPanel TPPanel1 = new JPanel(new BorderLayout());

    ImageIcon guide = new ImageIcon("guide.jpg");
    JLabel guideLbl = new JLabel(guide);

    TPPanel1.add(guideLbl, BorderLayout.CENTER);
    tutorialPopup.add(TPPanel1);
    tutorialPopup.setResizable(false);
    tutorialPopup.setVisible(true);
  }

  /**
   * showSeedBook() displays the list of available seeds in the game
   * @param icon is the icon associated with each seed
   */
  public void showSeedBook(ImageIcon icon) {
    seedBookPopup = new JDialog();

    // JDialog specs:
    seedBookPopup.setSize(new Dimension(400, 600));
    seedBookPopup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // centers the windows
    seedBookPopup.setLocationRelativeTo(null);
    seedBookPopup.setUndecorated(true);

    // main panel
    JPanel SBPanel = new JPanel(new BorderLayout());
    SBPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    SBPanel.setBackground(new Color(255, 247, 212));

    // sub-panel within the JDialog to easily layout elements
    JPanel SBSubPanel = new JPanel(new GridBagLayout());
    GridBagConstraints GBC = GBC();
    SBSubPanel.setBackground(new Color(255, 247, 212));
    SBSubPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

    // heading
    JLabel SBTitle = new JLabel("The 8 Seed of MyFarm");
    JLabel SBTitle2 = new JLabel("by The Profs");

    SBSubPanel.add(SBTitle, GBC);
    SBSubPanel.add(SBTitle2, GBC);
    SBSubPanel.add(SBCounter, GBC);
    SBPanel.add(SBSubPanel, BorderLayout.NORTH);

    // seedbook pages pictures
    seed = new JLabel(icon);
    SBPanel.add(seed, BorderLayout.CENTER);
    SBPanel.add(closeSBButton, BorderLayout.SOUTH);

    // sub-panel2 for buttons
    JPanel SBSUbPanel2West = new JPanel(new GridBagLayout());
    JPanel SBSUbPanel2East = new JPanel(new GridBagLayout());
    SBSUbPanel2East.setBackground(new Color(255, 247, 212));
    SBSUbPanel2West.setBackground(new Color(255, 247, 212));

    SBSUbPanel2East.add(nextSBButton);
    SBSUbPanel2West.add(prevSBButton);

    SBPanel.add(SBSUbPanel2East, BorderLayout.EAST);
    SBPanel.add(SBSUbPanel2West, BorderLayout.WEST);

    seedBookPopup.add(SBPanel);
    seedBookPopup.setResizable(false);

    // prevents user opening another JDialog when one is opened.
    // [https://stackoverflow.com/a/6750561/20623821]
    seedBookPopup.setModal(true);
    seedBookPopup.setVisible(true);

  }

  /**
   * showPromoteFarmer() displays the registration window where players can
   * register and promote their status
   */
  public void showPromoteFarmer() {
    JDialog registerPopup = new JDialog();

    // JDialog specs:
    registerPopup.setSize(new Dimension(300, 250));
    registerPopup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // centers the window
    registerPopup.setLocationRelativeTo(null);

    // main panel of registerPopup
    JPanel RPanel = new JPanel(new BorderLayout());
    RPanel.setBackground(new Color(255, 247, 212));

    // registration image
    ImageIcon icon = new ImageIcon("farmertype.jpg");

    JLabel label1 = new JLabel(icon);
    label1.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // constraints for each button of "register"
    gbc.ipady = 2;
    gbc.insets = new Insets(0, 0, 25, 0);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 25;
    label1.add(reqrmntButton, gbc);

    gbc.insets = new Insets(0, 180, 5, 0);
    gbc.gridx = 5;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    label1.add(registerButtREG, gbc);

    gbc.insets = new Insets(0, 180, 5, 0);
    gbc.gridx = 5;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    label1.add(registerButtDIS, gbc);

    gbc.insets = new Insets(0, 180, 5, 0);
    gbc.gridx = 5;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    label1.add(registerButtLEG, gbc);

    RPanel.add(label1, BorderLayout.CENTER);

    // add main panel to jDialog
    registerPopup.add(RPanel);

    registerPopup.setResizable(false);
    registerPopup.setModal(true);
    registerPopup.setVisible(true);
  }

  /**
   * showRequirements() contains the requirements needed to register on specific
   * farmer types.
   */
  public void showRequirements() {
    // JDialog specs:
    requirementsPopup.setSize(new Dimension(300, 450));
    requirementsPopup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // centers the window
    requirementsPopup.setLocationRelativeTo(null);

    // main panel of requirementPopup
    JPanel SRPanel = new JPanel(new BorderLayout());
    SRPanel.setBackground(new Color(255, 251, 238));

    ImageIcon icon = new ImageIcon("farmerrequirements.jpg");
    JLabel label1 = new JLabel(icon);
    label1.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // constraints for each button of "register"
    gbc.ipady = 2;
    gbc.insets = new Insets(400, 0, 5, 0);
    gbc.gridx = 5;
    gbc.gridy = 3;
    gbc.gridwidth = 2;

    label1.add(okayButton, gbc);

    SRPanel.add(label1, BorderLayout.CENTER);

    requirementsPopup.add(SRPanel);
    requirementsPopup.setResizable(false);
    requirementsPopup.setModal(true);
    requirementsPopup.setVisible(true);
  }

  /**
   * promotionConfirmation() pops up to verify registration to players
   * @param cost is the cost of registration for specific farmer type
   * @return 0 if users confirm,
   */
  // shows up to confirm if player really wants to register
  public int promotionConfirmation(double cost) {
    Object[] options = { "Yes, I am sure", "No, do not register" };
    ImageIcon icon = new ImageIcon("farmingicon.jpg");
    // returns 0 if yes, 1 if no.
    return JOptionPane.showOptionDialog(this, "Are you sure you want to register? Registration costs " + cost,
        "confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
  }

  /**
   * newGameConfirmation() confirms to player whether they like to "new game" or
   * quit the game
   * 
   * @param text returns the reason for being game over
   * @return 0 if user confirms
   */
  public int newGameConfirmation(String text) {
    Object[] options = { "Yes, I want to play again", "No, quit game" };
    ImageIcon icon = new ImageIcon("farmingicon.jpg");
    // returns 0 if yes, 1 if no.
    return JOptionPane.showOptionDialog(this, text + " Game Over! Do you want to play again? ", "confirmation",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
  }

  /**
   * showPlantChoices() displays the available seeds to the players and allow them
   * to plant it
   * 
   * @param image is the ImageIcon corresponding to the plant choice.
   */
  public void showPlantChoices(ImageIcon image) {
    plantChoices = new JDialog();
    // JDialog specs:
    plantChoices.setSize(new Dimension(400, 600));
    plantChoices.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // centers the window
    plantChoices.setLocationRelativeTo(null);
    plantChoices.setUndecorated(true);

    // main panel
    JPanel SPCPanel = new JPanel(new BorderLayout());
    SPCPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    SPCPanel.setBackground(new Color(255, 247, 212));

    // sub-panel within the JDialog to easily layout elements
    JPanel SPCSubPanel = new JPanel(new GridBagLayout());
    GridBagConstraints GBC = GBC();
    SPCSubPanel.setBackground(new Color(255, 247, 212));
    SPCSubPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

    // heading
    JLabel SPTitle = new JLabel("The 8 Seed of MyFarm");
    JLabel SPTitle2 = new JLabel("by The Profs");

    SPCSubPanel.add(SPTitle, GBC);
    SPCSubPanel.add(SPTitle2, GBC);
    SPCSubPanel.add(SBCounter, GBC);
    SPCPanel.add(SPCSubPanel, BorderLayout.NORTH);

    // seedbook pages pictures
    seed1 = new JLabel(image);

    JPanel PCButtonsPanel = new JPanel(new FlowLayout());
    PCButtonsPanel.setBackground(new Color(255, 247, 212));
    PCButtonsPanel.add(seedButton);
    PCButtonsPanel.add(closePCButton);
    SPCPanel.add(seed1, BorderLayout.CENTER);
    SPCPanel.add(PCButtonsPanel, BorderLayout.SOUTH);

    // sub-panel2 for buttons
    JPanel SPCSUbPanel2West = new JPanel(new GridBagLayout());
    JPanel SPCSUbPanel2East = new JPanel(new GridBagLayout());

    SPCSUbPanel2East.setBackground(new Color(255, 247, 212));
    SPCSUbPanel2West.setBackground(new Color(255, 247, 212));

    SPCSUbPanel2East.add(nextSBButton2);
    SPCSUbPanel2West.add(prevSBButton2);

    SPCPanel.add(SPCSUbPanel2East, BorderLayout.EAST);
    SPCPanel.add(SPCSUbPanel2West, BorderLayout.WEST);

    plantChoices.add(SPCPanel);
    plantChoices.setResizable(false);
    // prevents user opening another JDialog when one is opened.
    // [https://stackoverflow.com/a/6750561/20623821]
    plantChoices.setModal(true);
    plantChoices.setVisible(true);
  }

  /**
   * insertListener() is a method to assign listeners to the buttons
   * @param listener is an ActionListener
   */
  public void insertListener(ActionListener listener) {
    nextSBButton.addActionListener(listener);
    prevSBButton.addActionListener(listener);
    nextSBButton2.addActionListener(listener);
    prevSBButton2.addActionListener(listener);
    reqrmntButton.addActionListener(listener);
    okayButton.addActionListener(listener);
    registerButtREG.addActionListener(listener);
    registerButtDIS.addActionListener(listener);
    registerButtLEG.addActionListener(listener);
    seedButton.addActionListener(listener);
    closeSBButton.addActionListener(listener);
    closePCButton.addActionListener(listener);
  }

  /**
   * GBC() is a method that specifies the constrains of panels with GridBag layout
   * @return GridBagConstraints
   */
  public GridBagConstraints GBC() {
    GridBagConstraints GBC = new GridBagConstraints();

    GBC.gridy = GridBagConstraints.RELATIVE;
    GBC.gridwidth = GridBagConstraints.REMAINDER;

    return GBC;
  }

  /**
   * seedBookNumberingUpdate() updates the seedBook numbering
   * @param currNum the current number of page/crop
   */
  public void seedBookNumberingUpdate(int currNum) {
    SBCounter.setText("Seed " + currNum + " of 8");
  }

  /**
   * setSeed() sets the icon of seed
   * @param icon an ImageIcon
   */
  public void setSeed(ImageIcon icon) {
    seed.setIcon(icon);
  }

  /**
   * setSeed1() sets the icon of seed1
   * @param icon an ImageIcon
   */
  public void setSeed1(ImageIcon icon) {
    seed1.setIcon(icon);
  }

  /**
   * getNextSBButton() gets the NextSBButton
   * 
   * @return JButton
   */
  public JButton getNextSBButton() {
    return nextSBButton;
  }

  /**
   * getPrevSBButton() gets the PrevSBButton
   * 
   * @return JButton
   */
  public JButton getPrevSBButton() {
    return prevSBButton;
  }

  /**
   * getNextSBButton2() gets the NextSBButton2
   * 
   * @return JButton
   */
  public JButton getNextSBButton2() {
    return nextSBButton2;
  }

  /**
   * getPrevSBButton2() gets the getPrevSBButton2
   * @return JButton
   */
  public JButton getPrevSBButton2() {
    return prevSBButton2;
  }

  /**
   * getRequirementsPopup() gets the getRequirementsPopup
   * @return JDialog
   */
  public JDialog getRequirementsPopup() {
    return requirementsPopup;
  }

  /**
   * getRegisterButtREG() gets the getRegisterButtREG
   * 
   * @return JButton
   */
  public JButton getRegisterButtREG() {
    return registerButtREG;
  }

  /**
   * getRegisterButtDIS() gets the getRegisterButtDIS
   * 
   * @return JButton
   */
  public JButton getRegisterButtDIS() {
    return registerButtDIS;
  }

  /**
   * getRegisterButtLEG() gets the getRegisterButtLEG
   * 
   * @return JButton
   */
  public JButton getRegisterButtLEG() {
    return registerButtLEG;
  }

  /**
   * getPlantChoices() gets the getPlantChoices
   * 
   * @return JDialog
   */
  public JDialog getPlantChoices() {
    return plantChoices;
  }

  /**
   * getSeedBookPopup() gets the getSeedBookPopup
   * @return JDialog
   */
  public JDialog getSeedBookPopup() {
    return seedBookPopup;
  }

  /**
   * getClosePCButton() gets the getClosePCButton
   * @return JButton
   */
  public JButton getClosePCButton() {
    return closePCButton;
  }
}
