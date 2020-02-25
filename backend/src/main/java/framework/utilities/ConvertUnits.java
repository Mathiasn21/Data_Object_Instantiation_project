package framework.utilities;

import java.text.DecimalFormat;

/** Class for converting data to select units
 * @author Robert Alexander Dankertsen
 * @author Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0.+
 */
public class ConvertUnits {

    /**
     * @param number number to be converted to thousandth
     * @return the thousandth of the number
     */
    public String convertToThousandth(int number){
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(number);
    }

    /**
     * @param number number to be converted to percent
     * @return the percentage
     */
    public String convertToPercent(double number){
        double percentageCalculate = number * 100 / 100;

        return percentageCalculate + "%";
    }
    /**
     * @param number number user wants to be rounded
     * @return the rounded number
     */
    public double round(double number){
        return Math.round(number);
    }
}
