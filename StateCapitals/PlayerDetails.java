/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StateCapitals;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        PlayerDetails.java
 * Project      Project 1--State Capitals Quiz
 * Description  Class used to display details for name, age, correct answers and
 *              total number of questions of the selected player, which can also
 *              display a picture.
 * File         PlayerDetails.java
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Date         4/22/2021
 * @author	<i>Leanne Vu</i>
 * @version 	%1% %2%
 * @see         java.util.Objects;
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class PlayerDetails extends javax.swing.JDialog {
    private Player myPlayer;

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor      PlayerDetails()-default constructor
     * Description      Create an instance of the GUI form, set the default 
     *                  JButton to be saveJButton, set icone image center form
     *                  and set the JDialog to modal.
     * @param           parent java.awt.Frame p
     * @param           modal  boolean 
     * @author          <i>Leanne Vu</i>
     * Date             3/7/2021
     * History Log     
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/
    public PlayerDetails(java.awt.Frame parent, boolean modal) {
        //super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(
                "src/CapitalImages/State Capitals Small.png"));
        // center the application
        setLocationRelativeTo(null);
        myPlayer = null;
        setAlwaysOnTop(true);
        setModal(true);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor      PlayerDetails() constructor
     * Description      Uses the passed parameter to display information of the 
     *                  given object, as well as create an instance of the GUI 
     *                  form.
     * @param           aPlayer Player
     * @author          <i>Leanne Vu</i>
     * Date             3/7/2021
     * History Log     
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/
    public PlayerDetails(Player aPlayer) {
//        this(this, true); //call default constructor
        myPlayer = aPlayer;
        String name = myPlayer.getName();
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(
                "src/CapitalImages/State Capitals Small.png"));
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setModal(true);
        this.setTitle("Details and picture of " + name);
        setPicture(imageJLabel, "src/PlayerImages/", name);
        displayInfo();
    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		setPicture()
    *	Description     Method made so creating images with multiple files would
    *                   be much more convenient.
    *   @param          label JLabel
    *   @param          folder String
    *   @param          name String
    *	@author         <i>Leanne Vu</i>
    *	Date            3/8/2021
    *   history log
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setPicture (JLabel label, String folder, String name) {
        String image = folder + name + ".jpg";
        //sets label to the corresponding player
        label.setIcon(new ImageIcon(image));
    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		displayInfo()
    *	Description     Method used to display the player's information onto the
    *                   form.
    *	@author         <i>Leanne Vu</i>
    *	Date            3/8/2021
    *   history log
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void displayInfo() {
        String output = "Player: " + myPlayer.getName() + "\nAge of player: " +
                myPlayer.getAge() + "\nNumber of Correct Answers: " + 
                myPlayer.getCorrectAnswers() + "\nNumber of Total Questions: " + 
                myPlayer.getNumOfQuestions();
                quoteJTextArea.setText(output);
                quoteJTextArea.setCaretPosition(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageJLabel = new javax.swing.JLabel();
        resultsJPanel = new javax.swing.JPanel();
        resultsJScrollPane = new javax.swing.JScrollPane();
        quoteJTextArea = new javax.swing.JTextArea();
        closeJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Player Details");
        setResizable(false);

        imageJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PlayerImages/Abel D Metek.jpg"))); // NOI18N

        quoteJTextArea.setEditable(false);
        quoteJTextArea.setColumns(20);
        quoteJTextArea.setLineWrap(true);
        quoteJTextArea.setRows(5);
        quoteJTextArea.setWrapStyleWord(true);
        resultsJScrollPane.setViewportView(quoteJTextArea);

        javax.swing.GroupLayout resultsJPanelLayout = new javax.swing.GroupLayout(resultsJPanel);
        resultsJPanel.setLayout(resultsJPanelLayout);
        resultsJPanelLayout.setHorizontalGroup(
            resultsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );
        resultsJPanelLayout.setVerticalGroup(
            resultsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        closeJButton.setText("Close");
        closeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(resultsJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(closeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(imageJLabel)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(resultsJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		closeJButtonActionPerformed()
    *	Description     Event handler used to close the form
    *	@author         <i>Leanne Vu</i>
    *	Date            3/8/2021
    *   history log
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void closeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeJButtonActionPerformed
    this.dispose();
    }//GEN-LAST:event_closeJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlayerDetails dialog = new PlayerDetails(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeJButton;
    private javax.swing.JLabel imageJLabel;
    private javax.swing.JTextArea quoteJTextArea;
    private javax.swing.JPanel resultsJPanel;
    private javax.swing.JScrollPane resultsJScrollPane;
    // End of variables declaration//GEN-END:variables
}
