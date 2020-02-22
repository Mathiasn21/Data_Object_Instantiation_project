package framework.utilities;

import org.jetbrains.annotations.NotNull;

/** Class for sorting the collected data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0.0
 */
public interface ISort {
    String sortAlphabetically(@NotNull String string, @NotNull String string2, boolean Ascending);
    String sortByLength(@NotNull String string, @NotNull String string2, boolean shortestToLongest);
    double sortByWeight(double weight, double weight2, boolean lowToHigh);

    //Primitive sorting
    /**
     * @param array int[]
     */
    void sort(int[] array);

    /**
     * @param array double[]
     */
    void sort(double[] array);

    /**
     * @param array float[]
     */
    void sort(float[] array);

    /**
     * @param array char[]
     */
    void sort(char[] array);

    /**
     * @param array long[]
     */
    void sort(long[] array);

    /**
     * @param ascending boolean
     */
    void orderBy(boolean ascending);




    //sort with a given datastructure
}
