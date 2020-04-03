package framework.utilities.data.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Handler for handling conversion to, from, a graph to an actual node
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public final class CSVGraphHandler extends CSVHandler{
    private List<Exception> exceptions = new ArrayList<>();

    //handle bufferedstream
    public final List<Object[]> handleGraph(BufferedReader bufferedReader){
        List<Object[]> rows = null;
        try{
            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null){
                System.out.println(currentLine);
            }
        }
        catch (IOException e){
            exceptions.add(e);
        }
        finally {
            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                exceptions.add(e);
            }
        }
        return rows;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }
}
