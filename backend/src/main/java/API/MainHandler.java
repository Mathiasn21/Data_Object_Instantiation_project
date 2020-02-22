package API;

import framework.dataCollection.Item;

/* TODO:
    * Handle incoming requests                          |   DONE: NO
    * Allow retrieval of data                           |   DONE: NO
    * Retrieve detailed information given item          |   DONE: NO
 */

/** Main class for handling backend API requests
 * @author Robert Alexander Dankertsen
 * @author Github: Yeti-Programing @ https://github.com/yeti-programing
 * @author Mahtias W. Nilsen
 * @author Github: Mathiasn21@https://github.com/Mathiasn21
 * @version 1.0.0
 */
public class MainHandler implements IMainHandler {
    /**
     * @param item String
     * @return String string
     */
    public String getDataWith(String item) {
        return null; //return null = placeholder
    }

    /**
     * @param request String
     * @return String
     */
    @Override
    public String handleRequestFrom(String request) { return null; } //TODO: implement method

    /**
     * @param item {@link Item}
     * @return Stirng
     */
    @Override
    public String retrieveInformationFrom(Item item) { return null; } //TODO: implement method
}
