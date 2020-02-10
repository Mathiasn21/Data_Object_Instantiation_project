import java.util.ArrayList;
import java.util.List;

public class CollectorTest {


    void test() {
        String[] arra = new String[10];
        for (String str: arra) {

        }

        for (int i = 0; i < arra.length; i++) {
            System.out.println("herro");


        }

        List<String> stringList = new ArrayList<>(10);
        stringList.add("wda");
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println("herro");
        }
    }
}
