package StateCapitals;

import java.awt.List;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 * Class        FamousArtistsGUI.java
 * Description  A class representing the GUI used in a State Capitals quiz 
 *              application.
 * Project      Project 1 - State Capitals Quiz
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
 * Course       CS 143
 * Hourse       30 hours and 45 minutes
 * Date         4/22/2021
 * @author	<i>Leanne Vu</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see        java.awt.Toolkit 
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class StateCapitalsQuizGUI extends javax.swing.JFrame {
   //FileNames
    private String playersFileName = "src/StateCapitals/Players.txt";
    private String capitalsFileName = "src/StateCapitals/Capitals.txt";
        
    //Used in different methods to keep track of images displayed or results
    int correctNum = 0;
    private ArrayList<Capital> seen = new ArrayList<Capital>();
    private ArrayList<Boolean> tries = new ArrayList<Boolean>();
     
    //Data structures used for objects of Capitals, Players, and then hashMap
    private LinkedList<Capital> stateCapitalsList = new LinkedList<Capital>();
    private Map<String,String> capitalsHashMap = new HashMap<String,String>();
    private BinarySearchTree playersTree = new BinarySearchTree();
 
    //Used alongside the playerstree to make it easier to access the names and
    //potentially find the individual object of that name (in the searchPlayer())
    private ArrayList<String> playersNames = new ArrayList<String>();
    
    public StateCapitalsQuizGUI() {
        initComponents();
//        this.getRootPane().setDefaultTextField(questionsJTextField);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(
                "src/CapitalImages/State Capitals Small.png"));
        // center the application
        setLocationRelativeTo(null);
        //Read form an external text file Players.txt and populate the TreeSet of player names then display
        readFromFile(playersFileName);
        capitalReadFromFile(capitalsFileName);
        displayPlayers();
        setHashValues();
        setDate();  //call a private method to set date in title bar
    }

     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        setDate()
    * Description   Private method to set current date in the title bar of main
    *               form in mm/dd/yyyy style
    * @author       <i>Leanne Vu</i>
    * Date          4/22/2021
    * @see          java.text.DateFormat
    * @see          java.text.SimpleDateFormat
    * @see          java.util.Date
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void setDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        
        //Include date (including format) in title
        this.setTitle("Project 1-- State Capitals Quiz--" + dateFormat.format(date));
    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       readFromFile
     * Description  Reads players from a text file that is comma delimited and
     *              creates an instance of the Player class with the data read.
     *              Read players from an external file and save the Player object
     *              into a BinarySearchTree data type.
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * @param       file String
     * @see         java.util.Scanner
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
      private void readFromFile(String fileName){
        //Clear BinaryTreeSet and JList
                playersTree.removeAll(); // remove all from TreeSet
                playersNames.clear();   // clear array list with names
        
        try {
            //read while there is data
            Scanner input = new Scanner (new File(fileName));
            String line = "";
            
            while (input.hasNextLine()){
                line = input.nextLine();
                Player inf = new Player(); //create a inf 
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");

                // create an array of String using split method
                while(stringTokenizer.hasMoreElements()) { //String the file names
                    inf.setName(stringTokenizer.nextElement().toString());
                    inf.setAge(Integer.parseInt(stringTokenizer.nextElement().toString()));
                    inf.setCorrectAnswers(Integer.parseInt(stringTokenizer.nextElement().toString()));
                    inf.setNumOfQuestions(Integer.parseInt(stringTokenizer.nextElement().toString()));
                }
            //add information to the players BinaryTreeSet
                playersTree.insertNode(inf);
                playersNames.add(inf.getName());      
            }
            
            input.close();
        } 
        catch (FileNotFoundException e){
              JOptionPane.showMessageDialog(null, "File Does Not Exit",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
            //Bring up JFileChooser chooser to select file in current directory
            JFileChooser chooser = new JFileChooser("src/Player");
            //Filter only txt files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt Files", "txt");
            chooser.setFileFilter(filter);
            
            int choice = chooser.showOpenDialog(null);
            //return value if say yes or ok
            if(choice == JFileChooser.APPROVE_OPTION){
                File chosenFile = chooser.getSelectedFile();
                fileName = "src/Player/"+ chosenFile.getName();
                readFromFile(fileName);
            } else{
                JOptionPane.showMessageDialog(null, "Unable to read file", 
                        "File Input Error", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            } //weird I/O error
        }
    }
      
 /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       capitalReadFromFile
     * Description  Reads capital variables from a text file that is comma delimited and
     *              creates an instance of the Capital class with the data read.
     *              Read capitals from an external file and save the Capital object
     *              into a LinkedList data type.
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * @param       fileName String
     * @see         java.util.Scanner
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
      private void capitalReadFromFile(String fileName){
        //Clear TreeSet and JList
        //playersTree.clear(); playersJList.removeAll();
        //check if there is data
                stateCapitalsList.clear(); // remove all from TreeSet
        
        try {
            //read while there is data
            Scanner input = new Scanner (new File(fileName));
            String line = "";
            
            while (input.hasNextLine()){
                line = input.nextLine();
                Capital inf = new Capital(); //create a inf 
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");

                // create an array of String using split method
                while(stringTokenizer.hasMoreElements()) { //String the file names
                    inf.setState(stringTokenizer.nextElement().toString());
                    inf.setCapital(stringTokenizer.nextElement().toString());
                    inf.setYear(Integer.parseInt(stringTokenizer.nextElement().toString()));
                    inf.setArea(Double.parseDouble(stringTokenizer.nextElement().toString()));
                    inf.setPopulation(Integer.parseInt(stringTokenizer.nextElement().toString()));
                    inf.setRank(Integer.parseInt(stringTokenizer.nextElement().toString()));
                }
            //add information to the Capital LinkedList
                stateCapitalsList.add(inf);
            }
            
            input.close();
        } 
        catch (FileNotFoundException e){
              JOptionPane.showMessageDialog(null, "File Does Not Exit",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
            //Bring up JFileChooser chooser to select file in current directory
            JFileChooser chooser = new JFileChooser("src/Player");
            //Filter only txt files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt Files", "txt");
            chooser.setFileFilter(filter);
            
            int choice = chooser.showOpenDialog(null);
            //return value if say yes or ok
            if(choice == JFileChooser.APPROVE_OPTION){
                File chosenFile = chooser.getSelectedFile();
                fileName = "src/Player/"+ chosenFile.getName();
                readFromFile(fileName);
            } else{
                JOptionPane.showMessageDialog(null, "Unable to read file", 
                        "File Input Error", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            } //weird I/O error
        }
    }
      
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setHashValues()
     * Description  Add keys and values into the HashMap datatype which will be
     *              used to determine if user correctly enters the right capital
     *              associated with the given image.
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void setHashValues() {
        Capital capital = null;
        for(int i = 0; i < stateCapitalsList.size(); i++) {
            //Go through instances of Capital 
            capital = stateCapitalsList.get(i);
            //Use instances and access the state and capital of the same object
            capitalsHashMap.put(capital.getState(), capital.getCapital());
        }
    }
 
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       searchPlayer()
     * Description  Search for player by name and return node that contains it.
     * @param       name String
     * @return      Player node TreeNode
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode searchPlayer(String name)
    {
        return playersTree.nodeWith(name, playersTree.getRoot());
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       displayPlayers()
     * Description  Displays players in JList. Loop through the size of the 
     *              binarySearchTree and copy the playersName array list onto
     *              an array of playersList which will be used
     *              to set the JList.
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void displayPlayers(){
        int position = playersJList.getSelectedIndex(); // get index of current players
        String[] playersList = new String[playersNames.size()]; //array used to display names

        //loop through the BinarySearchTree to find player names and copying it into
        //an array that the playersJList will use to display names on playersJList
        for ( int index = 0; index < playersNames.size(); index++){
            playersList[index] = playersNames.get(index);     
        }
                //use the newly populated array (playersJList) to populate JList with names
        playersJList.setListData(playersList); //populate Jlist
        if (position < 0)
            playersJList.setSelectedIndex(0);
        else 
            playersJList.setSelectedIndex(position);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       savePlayers()
     * Description  Write Player to a text file that is pipe (,) delimited. Uses
     *              BinarySearchTree and accesses the object through the data
     *              structure.
     * @param       file String
     * @author      <i>Leanne Vu</i>
     * Date         5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void savePlayers(String file)
    {
      try {
          FileWriter filePointer = new FileWriter(file, false);
          PrintWriter writeFile = new PrintWriter(filePointer);
          String line = "";
          Player temp = new Player(); //Create temporary access variable to object Player
          BinarySearchTreeNode root = playersTree.getRoot(); //traverse TreeNode
          String output = playersTree.printTree(root);   
          
          for(int index = 0; index < playersNames.size();index++) {
              temp = searchPlayer(playersNames.get(index)).data; //Find the object using given name
              //Return name, age, correct answers, and number of questions through the instance
              line = temp.getName() + "," + temp.getAge() + "," + 
                      temp.getCorrectAnswers() + "," + temp.getNumOfQuestions();
              //Know when the file is at it's last line and would need to use \n
              if(index==playersNames.size() - 1)
                  writeFile.write(line);
              else
                  writeFile.write(line + "\n");
          }
          //TWO WAYS of writing the file 
//          writeFile.print(output);
          writeFile.close();
      }
      catch(IOException ex) {
          JOptionPane.showMessageDialog(this, "Unable to write to file", 
                  "Write File Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capitalImageJLabel = new javax.swing.JLabel();
        titleJLabel = new javax.swing.JLabel();
        questionsJLabel = new javax.swing.JLabel();
        stateJLabel = new javax.swing.JLabel();
        nameOfCapitalJLabel = new javax.swing.JLabel();
        answersJTextField = new javax.swing.JTextField();
        playersJPanel = new javax.swing.JPanel();
        playersJScrollPane = new javax.swing.JScrollPane();
        playersJList = new javax.swing.JList();
        controlJPanel = new javax.swing.JPanel();
        submitJButton = new javax.swing.JButton();
        nextJButton = new javax.swing.JButton();
        playJButton = new javax.swing.JButton();
        questionsJTextField = new javax.swing.JFormattedTextField();
        resultJLabel = new javax.swing.JLabel();
        stateCapitalsJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        openJMenuItem = new javax.swing.JMenuItem();
        clearJMenuItem = new javax.swing.JMenuItem();
        printJMenuItem = new javax.swing.JMenuItem();
        printPlayerJMenuItem = new javax.swing.JMenuItem();
        fileJSeparator = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        dataJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        editJMenuItem = new javax.swing.JMenuItem();
        deleteJMenuItem = new javax.swing.JMenuItem();
        searchJMenuItem = new javax.swing.JMenuItem();
        detailsJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        capitalImageJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CapitalImages/usa.jpg"))); // NOI18N
        capitalImageJLabel.setFocusable(false);

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 35)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(0, 102, 102));
        titleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleJLabel.setText("USA State Capitals Quiz");

        questionsJLabel.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        questionsJLabel.setText("Questions:");

        stateJLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        stateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stateJLabel.setToolTipText("State of Capital");
        stateJLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nameOfCapitalJLabel.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        nameOfCapitalJLabel.setText("Enter Name of Capital:");

        answersJTextField.setToolTipText("Capital guess here");
        answersJTextField.setEnabled(false);
        answersJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answersJTextFieldActionPerformed(evt);
            }
        });

        playersJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Players", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(51, 0, 0))); // NOI18N

        playersJList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        playersJList.setToolTipText("List of players is given");
        playersJList.setEnabled(false);
        playersJList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                playersJListPropertyChange(evt);
            }
        });
        playersJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                playersJListValueChanged(evt);
            }
        });
        playersJScrollPane.setViewportView(playersJList);

        javax.swing.GroupLayout playersJPanelLayout = new javax.swing.GroupLayout(playersJPanel);
        playersJPanel.setLayout(playersJPanelLayout);
        playersJPanelLayout.setHorizontalGroup(
            playersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playersJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        playersJPanelLayout.setVerticalGroup(
            playersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playersJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        );

        controlJPanel.setLayout(new java.awt.GridLayout(1, 3, 3, 0));

        submitJButton.setBackground(new java.awt.Color(255, 255, 204));
        submitJButton.setText("Submit");
        submitJButton.setToolTipText("Submit capital guess");
        submitJButton.setEnabled(false);
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(submitJButton);

        nextJButton.setBackground(new java.awt.Color(255, 255, 204));
        nextJButton.setText("Next");
        nextJButton.setToolTipText("Click for next state");
        nextJButton.setEnabled(false);
        nextJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(nextJButton);

        playJButton.setBackground(new java.awt.Color(255, 255, 204));
        playJButton.setText("Play Again");
        playJButton.setToolTipText("Play again if wanted");
        playJButton.setEnabled(false);
        playJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(playJButton);

        questionsJTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        questionsJTextField.setToolTipText("Enter how many questions wanted");
        questionsJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionsJTextFieldActionPerformed(evt);
            }
        });

        resultJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 22)); // NOI18N
        resultJLabel.setForeground(new java.awt.Color(0, 102, 102));
        resultJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        fileJMenu.setMnemonic('F');
        fileJMenu.setText("File");

        openJMenuItem.setMnemonic('N');
        openJMenuItem.setText("New");
        openJMenuItem.setToolTipText("Open a new set of states");
        openJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(openJMenuItem);

        clearJMenuItem.setMnemonic('C');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clear player, start a new quiz");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);

        printJMenuItem.setMnemonic('P');
        printJMenuItem.setText("Print Form");
        printJMenuItem.setToolTipText("Print Form as GUI");
        printJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printJMenuItem);

        printPlayerJMenuItem.setText("Print Player");
        printPlayerJMenuItem.setToolTipText("Prints specific player");
        printPlayerJMenuItem.setEnabled(false);
        printPlayerJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPlayerJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printPlayerJMenuItem);
        fileJMenu.add(fileJSeparator);

        exitJMenuItem.setMnemonic('X');
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("End application");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        stateCapitalsJMenuBar.add(fileJMenu);

        dataJMenu.setMnemonic('D');
        dataJMenu.setText("Database Operations");
        dataJMenu.setToolTipText("Add, Edit, Delete, Search and other operations");

        addJMenuItem.setMnemonic('A');
        addJMenuItem.setText("Add new player");
        addJMenuItem.setToolTipText("Add new player");
        addJMenuItem.setEnabled(false);
        addJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(addJMenuItem);

        editJMenuItem.setMnemonic('E');
        editJMenuItem.setText("Edit player");
        editJMenuItem.setToolTipText("Edit current player");
        editJMenuItem.setEnabled(false);
        editJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(editJMenuItem);

        deleteJMenuItem.setMnemonic('t');
        deleteJMenuItem.setText("Delete player");
        deleteJMenuItem.setToolTipText("Delete selected player");
        deleteJMenuItem.setEnabled(false);
        deleteJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(deleteJMenuItem);

        searchJMenuItem.setMnemonic('S');
        searchJMenuItem.setText("Search player");
        searchJMenuItem.setToolTipText("Search for player (resets quiz)");
        searchJMenuItem.setEnabled(false);
        searchJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(searchJMenuItem);

        detailsJMenuItem.setText("Player details");
        detailsJMenuItem.setToolTipText("Display picture and detail (age and balnce) of player");
        detailsJMenuItem.setEnabled(false);
        detailsJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(detailsJMenuItem);

        stateCapitalsJMenuBar.add(dataJMenu);

        helpJMenu.setText("Help");

        aboutJMenuItem.setMnemonic('A');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        stateCapitalsJMenuBar.add(helpJMenu);

        setJMenuBar(stateCapitalsJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(capitalImageJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(controlJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(questionsJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(questionsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(playersJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answersJTextField)
                    .addComponent(stateJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameOfCapitalJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionsJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleJLabel)
                    .addComponent(questionsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(stateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nameOfCapitalJLabel)
                        .addGap(7, 7, 7)
                        .addComponent(answersJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playersJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(capitalImageJLabel)
                        .addGap(18, 18, 18)
                        .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void answersJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answersJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_answersJTextFieldActionPerformed

    private void playersJListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_playersJListPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_playersJListPropertyChange

    private void playersJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_playersJListValueChanged
//        //notes whenever the index value for playerJList changes
//        int index = (playersJList.getSelectedIndex());
//        if (index >= 0) //calls showPlayersData
//        showPlayersData(index);
    }//GEN-LAST:event_playersJListValueChanged

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       openJMenuItemActionPerformed
     * Description  Event handler to chose a separate file of Players.
     * Date         4/22/2021
     * @param       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void openJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openJMenuItemActionPerformed
        //Bring up JFileChooser to select file in current directory
        JFileChooser fileJFileChooser = new JFileChooser("src/StateCapitals");
        //limit to txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt Files",
            "txt");
        fileJFileChooser.setFileFilter(filter);
        int returnVal = fileJFileChooser.showOpenDialog(this);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileJFileChooser.getSelectedFile();
            playersFileName = file.getName();
 
            clearJMenuItemActionPerformed(evt);
            displayPlayers();   //display first sign randomly
        }
        else {
            System.out.println("File access cancelled by user.");
        }
      
    }//GEN-LAST:event_openJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       clearJMenuItemActionPerformed()
     * Description  Event handler to clear the form and start anew.
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJMenuItemActionPerformed
        //Clear out the text fields and JList
        playersJList.setSelectedIndex(0);
        questionsJTextField.setText("");
        answersJTextField.setText("");
        
        //Set image back to default
        setImage(capitalImageJLabel, "Src/CapitalImages/", "usa");
        
        //Disable and enable back to what it first was
        playersJList.setEnabled(false);
        submitJButton.setEnabled(false);
        nextJButton.setEnabled(false);
        playJButton.setEnabled(false);
        questionsJTextField.setEnabled(true);
        answersJTextField.setEnabled(false);
        
        displayPlayers();
    }//GEN-LAST:event_clearJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printJMenuItemActionPerformed()
     * Description  Event handler to print the for as a GUI. Calls the
     *              PrintUtilities class printComponent method.
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * History Log  7/18/2018     
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJMenuItemActionPerformed
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       exitJMenuItemActionPerformed()
     * Description  Event handler to end the application.
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       addJButtonActionPerformed()
     * Description  Event handler for adding a player by invoking the AddPlayer
     *              form. The new player is added to the BST and saved in the 
     *              Players.txt file.
     * @parem       evt--ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJMenuItemActionPerformed
        try {
            //Invoke AddPlayer form
            AddPlayer add = new AddPlayer(this,true);
            add.setVisible(true);
            
            //This modal dailog takes upon regaining focus
            Player newPlayers = add.getPlayers();
            
            //Check if new player is null or if it already exists by invoking playerExists method
            if (newPlayers != null && !playersTree.contains(newPlayers)){
                //add the new player to the database
                playersTree.insertNode(add.getPlayers());
                playersNames.add(newPlayers.getName());
                displayPlayers();     //refreshes GUI
                
                
                //loop through players TreeSet database to find index of newly added player
                for (int index = 0 ; index < playersNames.size(); index++){ 
                    //check if any of the players in database matches the returned object
                    if (playersNames.get(index).equals(searchPlayer(newPlayers.getName()))) {
                        //highlight the chosen matched object and stop the loop from continuing
                        playersJList.setSelectedIndex(index);
                        break;
                    } 
                }
                    //save the updated BST into the text file
                    savePlayers(playersFileName);
            } else { //an already existing player will skip on an added creation
                playersJList.setVisible(true);
                playersJList.setSelectedIndex(0);
            }
        }
        catch(NullPointerException nullex){
            JOptionPane.showMessageDialog(null,"Player not added", "Player opera error",
                    JOptionPane.WARNING_MESSAGE);
            playersJList.setVisible(true);
            playersJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_addJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       editJButtonActionPerformed()
     * Description  Event handler for editing a player by invoking the EditOperas
     *              form. The edited player is edited to the BST and saved in
     *              the Players.txt file.
     * @parem       evt--ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void editJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJMenuItemActionPerformed
        //Edit Player
         try{
             //Invoked the EditPlayers form
             //pass player info to EditOperas constructor and view Edit form
            EditPlayer edit = new EditPlayer(this, true);
            edit.setVisible(true);
             
            //This modal dailog takes upon regaining focus
            Player editPlayers = edit.getPlayers();
            
            //get name of the selected players
            String playersName = playersJList.getSelectedValue().toString();
             System.out.println(playersName);
            //double checks to only measure the player name
            if (playersName.contains(","))
                playersName = playersName.substring(0, playersName.indexOf(','));
          
            //create a temp access the object of the selected player
            Player playersToEdit = (searchPlayer(playersName)).data;
            //int index = playersJList.getSelectedIndex();
          
            //get edited player and add to array List
            if (editPlayers != null){
              //remove old player from TreeSet
              playersTree.remove(playersToEdit);
              
              //add edited player to TreeSet
              playersTree.insertNode(edit.getPlayers());
              int index = playersNames.indexOf(playersName);
              playersNames.set(index, editPlayers.getName());
              
              //save players TreeSet to file and display new operas in JList
              displayPlayers();
              savePlayers(playersFileName);
          }
        }catch( NullPointerException nullex){
            JOptionPane.showMessageDialog(null,"Player not Edit", "Edit Name error",
                    JOptionPane.WARNING_MESSAGE);
            playersJList.setVisible(true);
            playersJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_editJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       deleteJButtonItemActionPerformed()
     * Description  Event handler for deletedJButton to delete selected player.
     *              Removes player to BST, updates the playersJList, and
     *              saves player.
     * @parem       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void deleteJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJMenuItemActionPerformed
        //Delete selected artist
        //Get the input
        String playersInputName = playersJList.getSelectedValue().toString();
        //Make sure user is sure (yes or no option in the showConfirmDialog)
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish"
            + " to delete " + playersInputName + "?", "Delete artist",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        //Proceed - 
        if(result == JOptionPane.OK_OPTION) //confirm delete selected Artist
        {
            playersNames.remove(playersInputName);    //remove name from playsNames ArrayList
            playersTree.remove(searchPlayer(playersInputName).data); //remove the object from playersTree

            displayPlayers(); //Refresh the display
            savePlayers(playersFileName); //Save the new playersTree
        }
    }//GEN-LAST:event_deleteJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method        searchJMenuItemActionPerformed()
     * Description   Searches player by using user input and sets focus on the
     *               selected player. 
     * @param        evt ActionWvent
     * @author       <i>Leanne Vu</i>
     * Date          4/22/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void searchJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJMenuItemActionPerformed
    String inputName = JOptionPane.showInputDialog ("Enter name of the player: ");
//        int position = playersJList.getSelectedIndex(); // get index of current players

        if (searchPlayer(inputName) != null) { //Check if the returned object is null
            
            //loop through players TreeSet database now that an object has passed to be searchable
            for (int index = 0 ; index < playersNames.size(); index++){ 
                //check if any of the players in database matches the returned object
                if (playersNames.get(index).equalsIgnoreCase(searchPlayer(inputName).data.getName())) {
                    //highlight the chosen matched object and stop the loop from continuing
                    System.out.println("hur");
                    playersJList.setSelectedIndex(index);
                    break;
                } 
            } 
        } else { // A null object means that the searched item does not match database records-
                JOptionPane.showMessageDialog(null, "Player " + inputName +
                        " not found ", " Search Result", JOptionPane.WARNING_MESSAGE);
                //set index to 0 when nothing else matches
                playersJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_searchJMenuItemActionPerformed

   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        aboutJMenuItemActionPerformed() 
    * Description   Event handler for aboutJMenuItem to show the About form
    * parem         evt ActionEvent
    * @author       <i>Leanne Vu</i>	
    * Date          4/22/2021
    *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJMenuItemActionPerformed
        About aboutWindow = new About(this, true);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		setImage()
    *	Description     Method made so creating images with multiple files would
    *                   be much more convenient. 
    *   @param          label JLabel
    *   @param          path String
    *   @param          name String
    *	@author         <i>Leanne Vu</i>
    *	Date            4/22/2021
    *   history log
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
        public void setImage(JLabel label, String path, String name) {
            //Create the path
        String image = path + name + ".jpg";
        label.setIcon(new ImageIcon(image)); //Set the icon with this path
    }
        
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          randomCapitalObject()
    *	Description     Returns a random object from the stateCapitalsList while
    *                   also taking into consideration duplicates. Uses the
    *                   seen arraylist
    *	@author         <i>Leanne Vu</i>
    *	Date            4/22/2021
    *   history log
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    public void displayState() {
        Capital picked;
        Random rand = new Random();

        //Get a random Capital object to use
        int num = rand.nextInt(50);
        picked = stateCapitalsList.get(num);
        
        //Make sure that the chosen random object hasn't already been chosen in the given round
        while (seen.contains(picked)) { //check if there are any states that have been drawed in this round
            num = rand.nextInt(50); //assign number to card until no drawed cards are matched
            picked = stateCapitalsList.get(num);
        }
        
        stateJLabel.setText(picked.getState()); //State the state of the given object
        seen.add(picked); //add picked card to drawed pile
        setImage(capitalImageJLabel, "Src/CapitalImages/", picked.getCapital());


    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          questionsJTextFieldActionPerformed()
    *	Description     Takes only integers from 1-50 and displays a random image.
    *                   Clears the seen array list as there are no duplicates 
    *                   the new roun
    *	@author         <i>Leanne Vu</i>
    *   @return         picked int
    *	Date            3/7/2021
    *   history log
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    private void questionsJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionsJTextFieldActionPerformed
            //Refresh array list used to detect any Capital object duplicates
            seen.clear();
            
            //Validation
            if (Integer.parseInt(questionsJTextField.getText()) < 1 || Integer.parseInt(questionsJTextField.getText()) > 50) {
                questionsJTextField.requestFocus();
                questionsJTextField.setToolTipText ("--Invalid integer range. Please enter a range from 1-50");
            } else {
                //Enable necessary controls
                submitJButton.setEnabled(true);
                answersJTextField.setEnabled(true);
                playersJList.setEnabled(true);
                questionsJTextField.setEnabled(false);
                
                //Menu Items controls
                addJMenuItem.setEnabled(true);
                editJMenuItem.setEnabled(true);
                deleteJMenuItem.setEnabled(true);
                searchJMenuItem.setEnabled(true);
                detailsJMenuItem.setEnabled(true);
                printPlayerJMenuItem.setEnabled(true);
                
                //Display random state
                displayState();
        }
    }//GEN-LAST:event_questionsJTextFieldActionPerformed

   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          updateResults()
    *	Description     Updates the correct answers and total number of questions
    *                   after the play of each individual player into the playersTree.
    *                   Called once the user has used up their max number of tries
    *                   in that current round.
    *	@author         <i>Leanne Vu</i>
    *   @param          questions int
    *   @param          correctNum
    *	Date            4/22/2021
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    public void updateResults(int questions, int correctNum) {
        //Get the index of the selected player to use to find the object using searchPlayer()
        int index = playersJList.getSelectedIndex();
        Player aPlayer = searchPlayer(playersNames.get(index)).data;
        
        //update the number of questions for the player by adding the new questions
        //sent by parameter
        int newNumOfQuestions = aPlayer.getNumOfQuestions() + questions;
        aPlayer.setNumOfQuestions(newNumOfQuestions);
        
        //update the number of correct answers for the player by adding the new answers
        //sent by parameter
        int newCorrectAnswers = aPlayer.getCorrectAnswers() + correctNum;
        aPlayer.setCorrectAnswers(newCorrectAnswers);
        //refresh the players displayed
        displayPlayers();
    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          displayResults()
    *	Description     Displays the result of the given round after each
    *                   try the user takes. Keeps track of the number of tries,
    *                   answers correct, and whether that round was correct or
    *                   incorrect
    *	@author         <i>Leanne Vu</i>
    *   @param          result Boolean
    *   @param          correctNum int
    *   @param          tries int
    *	Date            4/22/2021
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    public void displayResults(Boolean result, int correctNum, int tries) {
        if (result == true) {
            resultJLabel.setText("Correct! " + correctNum + "/" + tries);
        } else {
            resultJLabel.setText("Incorrect! " + correctNum + "/" + tries);
        }
        
    }
            
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       submitJButtonActionPerformed
     * Description  Event handler to check if the user's answer is correct. The
     *              correct answer is found out by using HashMap. Calls to
     *              displayResults() and updateResults(). Also checks validation
     * Date         3/12/2021
     * History Log  7/18/2018
     * @param       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        //keep track of the user's favored number of tries
        int questions = Integer.parseInt(questionsJTextField.getText());
        
        //validation of the answersJTextField
        if (Validation.isEmpty(answersJTextField) || !Validation.isValidName(answersJTextField.getText())) {
                answersJTextField.requestFocus();
                answersJTextField.setToolTipText ("--Invalid input. Please enter a state capital");
            } else if (tries.size() == (questions - 1)){ //once tries are done, enable and display as necessary
                submitJButton.setEnabled(false);
                nextJButton.setEnabled(false);
                playJButton.setEnabled(true);
                updateResults(questions, correctNum); //update results to playersTree
                displayPlayers(); //refresh the players displayed
                
                //Menu controls
                playersJList.setEnabled(true);
                addJMenuItem.setEnabled(true);
                editJMenuItem.setEnabled(true);
                deleteJMenuItem.setEnabled(true);
                searchJMenuItem.setEnabled(true);
                detailsJMenuItem.setEnabled(true);
                printPlayerJMenuItem.setEnabled(true);
                
                //Check if the key matches with the value in HashMap 
                if (capitalsHashMap.get(stateJLabel.getText()).equals(answersJTextField.getText())) { 
                    tries.add(true);
                    correctNum +=1; //increment the correct nubers if matched
                    displayResults(true, correctNum, tries.size()); //display results
                } else {
                    tries.add(false); //keep the correct variable the same, but still keep track of the tries
                    displayResults(false, correctNum, tries.size()); //display results
                }      
  
            } else {
                //Enable necessary controls
                submitJButton.setEnabled(false);
                nextJButton.setEnabled(true);
                playersJList.setEnabled(false);
                
                //Menu Items controls
                addJMenuItem.setEnabled(false);
                editJMenuItem.setEnabled(false);
                deleteJMenuItem.setEnabled(false);
                searchJMenuItem.setEnabled(false);
                detailsJMenuItem.setEnabled(false);
                printPlayerJMenuItem.setEnabled(false);
                
                
                //Check if the key matches with the value in HashMap 
                if (capitalsHashMap.get(stateJLabel.getText()).equals(answersJTextField.getText())) { 
                    tries.add(true);
                    correctNum +=1; //increment the correct nubers if matched
                    displayResults(true, correctNum, tries.size()); //display results
                } else {
                    tries.add(false); //keep the correct variable the same, but still keep track of the tries
                    displayResults(false, correctNum, tries.size()); //display results
                }      
            }
    }//GEN-LAST:event_submitJButtonActionPerformed

   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       nextJButtonActionPerformed
     * Description  Event handler to guide the user through the program of 
     *              their next question displayed.
     * Date         4/22/2021
     * @param       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nextJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextJButtonActionPerformed
        //enable and disable the necessary controls
        submitJButton.setEnabled(true);
        nextJButton.setEnabled(false);
           
        //Display random state
        displayState();
    }//GEN-LAST:event_nextJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       detailsJMenuItemActionPerformed()
     * Description  Event handler for displaying player information by invoking
     *              DetailPlayers. Calls the DetailPlayers and passes the
     *              selected player object in BST. 
     * @parem       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         4/22/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void detailsJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsJMenuItemActionPerformed
            try{
            //find index of selected player to find name, which finds the object
            int index = playersJList.getSelectedIndex();
            if(index >=0) {
            //assign object to player variable 
            Player player = searchPlayer(playersNames.get(index)).data;
            //pass selected player information into the PlayerDetails which prints known information
            PlayerDetails playerQuote = new PlayerDetails(player);
            playerQuote.setVisible(true);
            }
        }
        catch ( NullPointerException nullex){
            JOptionPane.showMessageDialog(null,"Details error", "Details error",
                    JOptionPane.WARNING_MESSAGE);
            playersJList.setVisible(true);
            playersJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_detailsJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       printPlayerJMenuItem1ActionPerformed()
     * Description  Event handler to print details of selected player by invoking 
     *              printJMenuItem. Accesses the name, age, correct answers, and
     *              total number of questions through the chosen object- player.
     * @parem       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     * Date         3/5/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void printPlayerJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPlayerJMenuItemActionPerformed
        /// Print selected player
        int index = playersJList.getSelectedIndex(); //detect player through index
        JTextArea printPlayer = new JTextArea(); //the JTextArea creation that will be printed
        if (index >= 0){
            try{
                //Player name, age, and balance
                String output = "Name: " + searchPlayer(playersNames.get(index)).data.getName() + "\n" +
                        "Age: " + searchPlayer(playersNames.get(index)).data.getAge() +"\n" +
                        "Correct Answers: " + searchPlayer(playersNames.get(index)).data.getCorrectAnswers() 
                        + "\nTotal Number of Questions: " + searchPlayer(playersNames.get(index)).data.getNumOfQuestions();
                printPlayer.setText(output); //sets printPlayer to the created output
                System.out.println("Printed player: " + output);
                //print the created String (printPlayer)
                printPlayer.print();
            } 
            catch(PrinterException ex){
            JOptionPane.showMessageDialog(null,"Player not printed", "Print Error",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_printPlayerJMenuItemActionPerformed

   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       playJButtonActionPerformed
     * Description  Event handler to start the game all over again.
     * Date         4/22/2021
     * @param       evt ActionEvent
     * @author      <i>Leanne Vu</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    private void playJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playJButtonActionPerformed
                //update questions for player
                playJButton.setEnabled(false);
                answersJTextField.setEnabled(false);
                questionsJTextField.setEnabled(true);
                questionsJTextField.setText("");
                answersJTextField.setText("");
                resultJLabel.setText("");
                tries.clear();
                correctNum = 0;
                
                //set image back to default (makes it easier to visually understand)
                setImage(capitalImageJLabel, "Src/CapitalImages/", "usa");

                //Player's score already updated in BST- so save it in external file
                savePlayers(playersFileName);
    }//GEN-LAST:event_playJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Splash mySplash = new Splash(5000);     // duration = 4 seconds
        mySplash.showSplash();                  // show splash screen
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StateCapitalsQuizGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StateCapitalsQuizGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StateCapitalsQuizGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StateCapitalsQuizGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StateCapitalsQuizGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JTextField answersJTextField;
    private javax.swing.JLabel capitalImageJLabel;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JMenu dataJMenu;
    private javax.swing.JMenuItem deleteJMenuItem;
    private javax.swing.JMenuItem detailsJMenuItem;
    private javax.swing.JMenuItem editJMenuItem;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JPopupMenu.Separator fileJSeparator;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JLabel nameOfCapitalJLabel;
    private javax.swing.JButton nextJButton;
    private javax.swing.JMenuItem openJMenuItem;
    private javax.swing.JButton playJButton;
    private javax.swing.JList playersJList;
    private javax.swing.JPanel playersJPanel;
    private javax.swing.JScrollPane playersJScrollPane;
    private javax.swing.JMenuItem printJMenuItem;
    private javax.swing.JMenuItem printPlayerJMenuItem;
    private javax.swing.JLabel questionsJLabel;
    private javax.swing.JFormattedTextField questionsJTextField;
    private javax.swing.JLabel resultJLabel;
    private javax.swing.JMenuItem searchJMenuItem;
    private javax.swing.JMenuBar stateCapitalsJMenuBar;
    private javax.swing.JLabel stateJLabel;
    private javax.swing.JButton submitJButton;
    private javax.swing.JLabel titleJLabel;
    // End of variables declaration//GEN-END:variables
}
