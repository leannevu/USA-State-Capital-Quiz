
package StateCapitals;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Capital.java
 * Project      Project1--StateCapitals
 * Description  Capitals class that is used in maintaining the Capital database. 
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Date         4/22/2021
 * @author	<i>Leanne Vu</i>
 * @version 	%1% %2%
 * @see         java.util.Objects;
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Capital {
    
    private String state;
    private String capital;
    private int year; //capital since year
    private double area; //in square miles
    private int population;
    private int rank; //by size in state
    
   /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Constructor   Capital() -default constructor
    * Description   Creates an instance of the Capital class and assign default
    *               values.
    * @author       <i>Leanne Vu</i>	
    * Date          /5/2021
    *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Capital(){
        state = "";
        capital = "";
        year = 0;
        area = 0;
        population = 0;
        rank = 0;
    }
    
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor      Capital()-overloaded constructor
     * Description      Create an instance of the Capital() class and assign
     *                  values with one parameter for assigning the correctAnswers
     * @author          <i>Leanne Vu</i>
     * @param           state String
     * @param           capital String
     * @param           year int
     * @param           area double
     * @param           population int
     * @param           rank int
     * Date             4/22/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Capital(String state, String capital, int year, double area, int population, int rank) {
        this.state = state;
        this.capital = capital;
        this.year = year;
        this.area = area;
        this.population = population;
        this.rank = rank;
    }
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Constructor   CApital() -overloaded copy constructor
    * Description   Create an instance of the Capital() class and assign
    *               values via parameters from another player to all fields.
    * @author       <i>Leanne Vu</i>	
    * @param        anotherCapital Capital
    * Date          4/22/2021
    *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Capital(Capital anotherCapital) {
        state = anotherCapital.state;
        capital = anotherCapital.capital;
        year = anotherCapital.year;
        area = anotherCapital.area;
        population = anotherCapital.population;
        rank = anotherCapital.rank;
    }
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getState()
     * Description  Get method to return state
     * @author      <i>Leanne Vu</i>
     * @return      name String
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getState() {
        return state;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setState()
     * Description  Set method to set state
     * @author      <i>Leanne Vu</i>
     * @param       state String
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setState(String state) {
        this.state = state;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getCapital()
     * Description  Get method to return variable capital
     * @author      <i>Leanne Vu</i>
     * @return      capital String
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getCapital() {
        return capital;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setCapital()
     * Description  Set method to set instance capital
     * @author      <i>Leanne Vu</i>
     * @param       capital String
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setCapital(String capital) {
        this.capital = capital;
    }
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getYear()
     * Description  Get method to return variable of year
     * @author      <i>Leanne Vu</i>
     * @return      year int
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getYear() {
        return year;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setYear()
     * Description  Set method to set num of questions
     * @author      <i>Leanne Vu</i>
     * @param       year int
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setYear(int year) {
        this.year = year;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getArea()
     * Description  Get method to return variable double
     * @author      <i>Leanne Vu</i>
     * @return      area double
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public double getArea() {
        return area;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setBalance()
     * Description  Set method to set instance variable double
     * @author      <i>Leanne Vu</i>
     * @param       area double
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setArea(double area) {
        this.area = area;
    }
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getPopulation()
     * Description  Get method to return variable of population
     * @author      <i>Leanne Vu</i>
     * @return      population int
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getPopulation() {
        return population;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setPopulation()
     * Description  Set method to set population
     * @author      <i>Leanne Vu</i>
     * @param       population int
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setPopulation(int population) {
        this.population = population;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getRank()
     * Description  Get method to return variable rank
     * @author      <i>Leanne Vu</i>
     * @return      rank int
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getRank() {
        return rank;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setRank()
     * Description  Set method to set instance variable year
     * @author      <i>Leanne Vu</i>
     * @param       rank int
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setRank(int rank) {
        this.rank = rank;
    }
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       compareTo()
     * Description  Required compareTo() method for comparison of two
     *              players by name (and if names are equal, then by year). 
     *              Allow the Linked data type to sort the Capital by name.
     * @author      <i>Leanne Vu</i>
     * @param       obj Object
     * Date         4/22/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int compareTo(Object obj) {
        Capital otherCapital = (Capital) obj;
        //If name equal, compare by age
        if((this.getState()).equalsIgnoreCase(otherCapital.getState()))
            return this.year - otherCapital.year;
        else    //otherwise, compare by name
            return (this.getState()).compareTo(otherCapital.getState());
        //this will eliminate duplicate players with the same age only: TreeSet
        //return this.getAge() - otherPerson.getAge();
    }
    
}
