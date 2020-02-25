package framework.utilities;

import org.jetbrains.annotations.NotNull;

/** Class for sorting the collected data
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @author Robert Alexander Dankertsen Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0.0
 */
public interface ISort {
    String sortAlphabetically(@NotNull String string, @NotNull String string2);
    String sortByLength(@NotNull String string, @NotNull String string2);
    double sortByWeight(double weight, double weight2);


    /**
     * @param array int[]
     */
    int sort(int[] array);

    /**
     * @param array double[]
     */
    int sort(double[] array);

    /**
     * @param array float[]
     */
    int sort(float[] array);

    /**
     * @param array char[]
     */
    int sort(char[] array);

    /**
     * @param array long[]
     */
    int sort(long[] array);

    void reverse();


    //sort with a given datastructure
}
