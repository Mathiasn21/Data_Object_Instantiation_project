package framework.resource.commands.delete;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/** Class for deletion of file.
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class DeleteFileDeleteCommand implements IDeleteFileDeleteCommand {

    public void deleteFileIfExists(File file){
        try {
            if(file.exists()){
                boolean result = Files.deleteIfExists(file.toPath());
            }
            else{
                System.out.println("File " + file.toString() + " does not exist or was not found.");
            }
        }
        catch (IOException e){
            //TODO: Add proper handling of exception
            System.out.println(e.toString());
        }
    }
}
