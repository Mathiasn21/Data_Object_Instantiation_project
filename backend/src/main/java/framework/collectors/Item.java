package framework.collectors;

/** General data item in a dataset
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class Item {
    private String[] row;
    private String name;
    public Item(String name, String[] row){
        this.name = name;
        this.row = row;
    }
}
