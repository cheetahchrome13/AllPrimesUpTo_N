
package allprimesupto_n;

import java.io.IOException;

/**
 * Project: All Prime Numbers Up to N
 * Tasks: 1) Find and store all prime numbers up to N
 *        2) Print results
 * @author Justin Mangan
 * Date: 31 March 2018
 */
public class PrimesTest {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        // Set the upper limit of search, perform search, print results
        PrimesFinder.setMax(1_000_000L);
        PrimesFinder.findPrimes();
        Results.printResults();        
    }
}
