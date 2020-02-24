package framework.utilities;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        double percentageCalculate = (number*100)/100;

        return percentageCalculate + "%";
    }

    /**
     * @param number number that will be converted to currency
     * @return currency
     */
    public String convertToCurrency(double number){
        return "KR " + number + ",-";
    }

    /**
     * @param currency the currency to be changed to a number
     * @return returns the number from the string
     */
    public double convertFromCurrency(String currency){
        Pattern findPattern = Pattern.compile("\\d+");
        Matcher match = findPattern.matcher(currency);

        return Double.parseDouble(match.group());
    }

    /**
     * @param number number user wants to be rounded
     * @return the rounded number
     */
    public double round(double number){
        return Math.round(number);
    }

    /**
     * @param fromUnit what unit it will be converted from (KM, Inch, CM)
     * @param toUnit what unit it will be converted to (KM, Inch, CM)
     * @param number the number of the unit that it wil be converted from (if km: 1000)
     * @return
     */
    public double convertDistance(String fromUnit, String toUnit, double number){
        return 0;
    }
}
