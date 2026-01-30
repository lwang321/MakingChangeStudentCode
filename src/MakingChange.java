/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author [YOUR NAME HERE]
 */

import java.util.Arrays;

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */

    // Table is our memoization/tabulation table: row[i] is the subproblem with coins 0 to i, and each column represents the subproblem of trying to get value j
    private static long[][] table;

    private static long memoization(int row, int col, int coins[]) {
        // Check for boundary conditions
        if (row < 0 || row >= table.length || col < 0 || col >= table[0].length) {
            return 0;
        }

        // Check if already calculated
        if (table[row][col] != 0) {
            return table[row][col];
        }

        // Return include (left) + exclude (up) through recursion
        long value = memoization(row-1, col, coins) + memoization(row, col-coins[row], coins);
        // Store calculated value in table to avoid recalculations
        table[row][col] = value;

        return (value);
    }




    public static long countWays(int target, int[] coins) {
        // Reset table
        table = new long[coins.length][target+1];

        // Set the first column to 1's: there is always 1 way to make target of 0
        for (int i = 0; i < coins.length; i++) {
            table[i][0] = 1;
        }

        // Code for Memoization
        // return memoization(coins.length-1, target, coins);

        // Code for Tabulation
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < target + 1; j++) {
                // Define variables to simplify boundary condition code
                long include = 0;
                if (j >= coins[i]){
                    include = table[i][j-coins[i]];
                }

                long exclude = 0;
                if (i > 0){
                    exclude = table[i-1][j];
                }

                table[i][j] = include + exclude;
            }
        }

        // Return top right entry in table
        return table[coins.length-1][target];
    }
}
