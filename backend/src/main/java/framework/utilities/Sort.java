package framework.utilities;
import org.jetbrains.annotations.NotNull;


/** Class for sorting the collected data
 * @author Robert Alexander Dankertsen
 * @author Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0.1
 * @since 1.0.0
 */
public class Sort implements ISort{
    /**
     * @param string String
     * @param string2 String
     * @return String
     */
    public String sortAlphabetically(@NotNull String string, @NotNull String string2){
        int length = sortByLength(string, string2).length();

        String sortedString = "";

        byte[] stringBytes = string.getBytes();
        byte[] string2Bytes = string2.getBytes();

        for(int i = 0; i <= (length-1); i++){
            sortedString = stringBytes[i] <= string2Bytes[i] ? string : string2;
        }
        return sortedString;
    }

    /**
     * @param string String
     * @param string2 String
     * @return String
     */
    public String sortByLength(@NotNull String string, @NotNull String string2){
        return string.length() <= string2.length() ? string : string2;
    }

    /**
     * @param weight String
     * @param weight2 String
     * @return String
     */
    public double sortByWeight(double weight, double weight2){
        return Math.min(weight, weight2);
    }

    /**
     * @param array int[]
     * @return int
     */
    @Override
    public int sort(int[] array) {

        return 0;
    }

    /**
     * @param array double[]
     * @return int
     */
    @Override
    public int sort(double[] array) {

        return 0;
    }

    /**
     * @param array float[]
     * @return float
     */
    @Override
    public int sort(float[] array) {

        return 0;
    }

    /**
     * @param array char[]
     * @return int
     */
    @Override
    public int sort(char[] array) {

        return 0;
    }

    /**
     * @param array long[]
     * @return int
     */
    @Override
    public int sort(long[] array) {

        return 0;
    }

    @Override
    public void reverse() {

    }

}
