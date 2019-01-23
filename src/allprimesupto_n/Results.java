
package allprimesupto_n;

/**
 * Class: Results
 * Tasks: 1) Prints data stream while program executes
 *        2) Prints final data at end of build
 * @author Justin Mangan
 * Date: 31 March 2018
 */
public class Results {
    
    public static void printStream(){
        System.out.println("Prime number " + PrimesFinder.getNumber() + " added to file\tPrime numbers in file: " + PrimesFinder.getCount());
    }
    
    public static void printResults(){
        
        System.out.println("\nThere are " + PrimesFinder.getCount() + " prime numbers from 1 to " + PrimesFinder.getMax());
    }
    
}
