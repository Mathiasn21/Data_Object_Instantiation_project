package framework.utilities;

import org.jetbrains.annotations.NotNull;

/** Class for sorting the collected data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0
 */
public interface ISort{
    //String sortAlphabetically(@NotNull String string, @NotNull String string2, boolean Ascending);
    //String sortByLength(@NotNull String string, @NotNull String string2, boolean shortestToLongest);
    //double sortByWeight(@NotNull double weight, @NotNull double weight2, boolean lowToHigh);
    //String sortRandomly(@NotNull String string, @NotNull String string2);


    //Primitive sorting
    /**
     * @param array int[]
     */
    int[] sort(int[] array);

    /**
     * @param array double[]
     */
    double[] sort(double[] array);

    /**
     * @param array float[]
     */
    float[] sort(float[] array);

    /**
     * @param array char[]
     */
    char[] sort(char[] array);

    /**
     * @param array long[]
     */
    long[] sort(long[] array);

    /**
     * @param ascending boolean
     */
    void orderBy(boolean ascending);

    <C extends Comparable> int sort(C comparable);



    //sort with a given datastructure
}
