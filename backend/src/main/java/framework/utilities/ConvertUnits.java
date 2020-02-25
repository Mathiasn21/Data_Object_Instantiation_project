package framework.utilities;


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
    public double convertToThousandth(int number){
        //TODO: implement method
        return 0;
        /* DecimalFormat df = new DecimalFormat("#.###");
        return df.format(number);*/
    }

    /**
     * @param number number to be converted to percent
     * @return the percentage
     */
    public double convertToPercent(double number){
        return (number*100)/100;
    }


    /**
     * @param number number user wants to be rounded
     * @return the rounded number
     */
    public double round(double number){
        return Math.round(number);
    }
}
