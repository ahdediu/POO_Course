package lab06;
import java.math.BigInteger;

import static org.junit.Assert.*;
import org.junit.Test;

//Search for "junit" --
// you're looking for something like
// "junit:junit:4.11".
class GridPath {

    /** Classical factorial: n! */
    private static BigInteger fact(BigInteger n) {
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }
        // n! = n · (n-1)!
        return n.multiply(fact(n.subtract(BigInteger.ONE)));
    }

    /**
     * Number of lattice paths in an m×n grid, i.e. (m+n)! / (m! · n!)
     */
    public static BigInteger numberOfRoutes(int m, int n) {
        BigInteger mm = BigInteger.valueOf(m);
        BigInteger nn = BigInteger.valueOf(n);

        BigInteger numerator   = fact(mm.add(nn));              // (m+n)!
        BigInteger denominator = fact(mm).multiply(fact(nn));   // m! · n!

        return numerator.divide(denominator);
    }
}

public class NumberOfRoutesTests {
    private static void tester (int m, int n, BigInteger exp) {
        assertEquals(exp, GridPath.numberOfRoutes(m, n));
    }
    @Test
    public void ExampleTests () {
        tester(1, 1, new BigInteger("2"));
        tester(5, 1, new BigInteger("6"));
        tester(3, 4, new BigInteger("35"));
    }
}