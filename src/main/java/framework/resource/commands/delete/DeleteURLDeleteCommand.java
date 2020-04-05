package framework.resource.commands.delete;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** Class for deletion of FILE via URL DELETE.
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class DeleteURLDeleteCommand implements IDeleteURLDeleteCommand{

    @Override
    public void DeleteFromURLCommand(@NotNull URL url) {
        try {
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void DeleteFromURLCommand(@NotNull String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded" );
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
