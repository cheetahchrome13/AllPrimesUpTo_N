
package allprimesupto_n;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Class: PrimesFinder
 * Tasks: Finds primes and stores them in RAF file starting from 0 or from last 
 *          stopping point until max is reached.
 * @author Justin Mangan
 */
public class PrimesFinder {
    
    // Fields
    private static long max;// Upper limit of search - set in Main class
    private static long count;// Count of prime numbers
    private static long number = 2;// Default value if RAF is empty
    private static int squareRoot = 1;// Default initial value 
    
    
    public static void findPrimes() throws IOException{
        
        // Try-with resources creates a binary file for prime numbers storage
        try (RandomAccessFile primesFile = new RandomAccessFile("PrimeNumbers.dat", "rw")) {
            
            // ArrayList for batches
            ArrayList<Long> batch = new ArrayList<>();
            
            /* If program restarted and RAF has data, start where file left off + 1,
                set pointer to 0, and load next batch into ArrayList from RAF. 
                If RAF is empty, fall through to main while-loop, and
                number defaults as 2 */
            if(primesFile.length() > 0) {
                primesFile.seek(primesFile.length() - 8);
                setNumber(primesFile.readLong() + 1);
                primesFile.seek(0);
                try {
                    nextBatch(batch, primesFile);
                }
                catch (EOFException e) {
                }
                
                setSquareRoot((int) (Math.sqrt(getNumber())) + 1);
                setCount(primesFile.length() / 8);
            }// End "restarted" if-block
            
            /* Main while-loop tests if current number is prime by determining 
                if batch contains a divisor for number */
            while (getNumber() <= max) {
                boolean isPrime = true;
                
                if (getSquareRoot() * getSquareRoot() < getNumber()){
                    setSquareRoot(getSquareRoot() + 1);
                }
                
                // While (number <= n)
                while(true) {
                    isPrime = true;
                    /* If a divisor is found for number (not prime), 
                        break out of for-loop*/
                    for (int k = 0; k < batch.size() && batch.get(k) <= getSquareRoot(); k++) {
                        if (getNumber() % batch.get(k) == 0) {
                            isPrime = false;
                            break;
                        }
                    }
                    
                    /* If RAF pointer is at end of file, or number is not prime,
                        break out of nested while */
                    if (primesFile.getFilePointer() == primesFile.length() || !isPrime) {
                        break;
                    }

                    /* Else if number is prime (so far), clear and reload ArrayList 
                    with next batch from RAF without resetting pointer to 0 */
                    else {
                        batch.clear();
                        try {
                            nextBatch(batch, primesFile);
                        }
                        catch (EOFException e) {
                        }
                    }
                }// End nested while
                
                /* If number is prime, append it to RAF, clear the ArrayList, 
                    reset RAF pointer to 0, and load ArrayList with next batch 
                    from RAF */
                if (isPrime) {
                    setCount(getCount() + 1);
                    Results.printStream();
                    primesFile.writeLong(getNumber());
                    batch.clear();
                    primesFile.seek(0);
                    try {
                        nextBatch(batch, primesFile);
                    } 
                    catch (EOFException e) {
                    }
                }
                setNumber(getNumber() + 1);
            }// End While           
        }// End try-with resources(RandomAccessFile implements AutoCloseable interface)
    }// end findPrimes method
    
    // Load ArrayList with next batch from RAF for divisor comparison with number
    public static void nextBatch(ArrayList batch, RandomAccessFile primesFile) throws IOException{
        for (int i = 0; i < 10000; i++) {
            batch.add(primesFile.readLong());
        }  
    }
    
    // Getters/Setters
    /**
     * @return the count
     */
    public static long getCount() {
        return count;
    }

    /**
     * @param aCount the count to set
     */
    public static void setCount(long aCount) {
        count = aCount;
    }

    /**
     * @return the number
     */
    public static long getNumber() {
        return number;
    }

    /**
     * @param aNumber the number to set
     */
    public static void setNumber(long aNumber) {
        number = aNumber;
    }

    /**
     * @return the squareRoot
     */
    public static int getSquareRoot() {
        return squareRoot;
    }

    /**
     * @param aSquareRoot the squareRoot to set
     */
    public static void setSquareRoot(int aSquareRoot) {
        squareRoot = aSquareRoot;
    }

    /**
     * @return the max
     */
    public static long getMax() {
        return max;
    }

    /**
     * @param aMax the max to set
     */
    public static void setMax(long aMax) {
        max = aMax;
    }
}
