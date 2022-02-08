package StateCapitals;

import java.util.Objects;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Player.java
 * Project      Project3--CardGame
 * Description  Player class that is a subclass of the Person class
 *              (exhibiting inheritance, “is-a” relationship) and implements 
 *              the Comparable interface. Extends Person and implements 
 *              Comparable. Represents the GUI and is used in maintaining the 
 *              player database. 
 * File         CardGameGUI.java
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Date         4/22/2021 
 *
 * @author	<i>Leanne Vu</i>
 * @version 	%1% %2%
 * @see         java.util.Objects;
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Player extends Person implements Comparable{
    
    private String name;
    private int age;
    private int correctAnswers;
    private int numOfQuestions;

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       compareTo()
     * Description  Required overridden compareTo() method for comparison of two
     *              players by name (and if names are equal, then by age). 
     *              Allow the TreeSet data type to sort the players by name.
     * @author      <i>Leanne Vu</i>
     * @param       obj Object
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int compareTo(Object obj) {
        Player otherPerson = (Player) obj;
        //If name equal, compare by age
        if((this.getName()).equalsIgnoreCase(otherPerson.getName()))
            return this.age - otherPerson.age;
        else    //otherwise, compare by name
            return (this.getName()).compareTo(otherPerson.getName());
        //this will eliminate duplicate players with the same age only: TreeSet
        //return this.getAge() - otherPerson.getAge();
    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Constructor   Player() -default constructor
    * Description   Creates an instance of the player class and assign default
    *               values.
    * @author       <i>Leanne Vu</i>	
    * Date          4/22/2021
    *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player(){
        super("", 0);
        correctAnswers = 0;
        numOfQuestions = 0;
    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Constructor   Opears() -overloaded copy constructor
    * Description   Create an instance of the Player() class and assign
    *               values with 0 to correct answer.
    * @author       <i>Leanne Vu</i>	
    * @param        correctAnswers double
    * Date          4/22/2021
    *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
     public Player(int correctAnswers, int numOfQuestions){
         super("", 0);
         this.correctAnswers = correctAnswers;
         this.numOfQuestions = numOfQuestions;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor      Player()-overloaded constructor
     * Description      Create an instance of the Player() class and assign
     *                  values with one parameter for assigning the correctAnswers
     * @author          <i>Leanne Vu</i>
     * @param           name String
     * @param           age int
     * @param           correctAnswers double
     * Date             4/22/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player(String name, int age, int correctAnswers, int numOfQuestions) {
        super(name, age);
        this.correctAnswers = correctAnswers;
        this.numOfQuestions = numOfQuestions;
    }
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Constructor   Opears() -overloaded copy constructor
    * Description   Create an instance of the Player() class and assign
    *               values via parameters from another player to all fields.
    * @author       <i>Leanne Vu</i>	
    * @param        anotherPlayer Player
    * Date          4/22/2021
    *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player(Player anotherPlayer){
        super(anotherPlayer.name, anotherPlayer.age);
        correctAnswers = anotherPlayer.correctAnswers;
        numOfQuestions = anotherPlayer.numOfQuestions;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getNumOfQuestions()
     * Description  Get method to return variable of questions
     * @author      <i>Leanne Vu</i>
     * @return      name String
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getNumOfQuestions() {
        return numOfQuestions;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setNumOfQuestions()
     * Description  Set method to set num of questions
     * @author      <i>Leanne Vu</i>
     * @param       numOfQuestions double
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setNumOfQuestions(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getCorrectAnswers()
     * Description  Get method to return variable year
     * @author      <i>Leanne Vu</i>
     * @return      name String
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getCorrectAnswers() {
        return correctAnswers;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setBalance()
     * Description  Set method to set instance variable year
     * @author      <i>Leanne Vu</i>
     * @param       correctAnswers int
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
    
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       toString()
     * Description  Override toString() method to display player object
     * @author      <i>Leanne Vu</i>
     * @return      object String
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    @Override
    public String toString() {
        return "Player: "  + super.name + "\nAge: " + super.age
                + "\nCorrect answers: " + correctAnswers + "\nTotal number of"
                + " Questions " + numOfQuestions;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       equals()
     * Description  Override equals() method to compare Opera object equals to another
     * @author      <i>Leanne Vu</i>
     * @param       obj Object
     * @return      true or false boolean
     * Date       4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj; //checks if age is equal
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) { //checks if names are the same
            return false;
        }
        if (this.correctAnswers != other.correctAnswers) { //checks if correctAnswers is equal
            return false;
        }
        if (this.numOfQuestions != other.numOfQuestions) { //checks if numOfQuestions is equal
            return false;
        }
        return true;
    }
}
