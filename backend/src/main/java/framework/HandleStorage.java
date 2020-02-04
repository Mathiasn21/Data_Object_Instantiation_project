package framework;

import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class HandleStorage {

    /**
     * Standard method for writing data to a given file.
     * This method does not append but overwrites!
     * Utilizes a relative path for top level directory, plus filename.extension
     * @param str   String
     * @param fName String
     */
    private static void writeToFile(String str, String fName) {
        String filepath = "/files/" + fName;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("").getAbsolutePath() + filepath))) {
            bufferedWriter.write(str);
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    /**
     * Returns a buffer for reading the given file.
     * Charset is standard UTF-8
     * @param fName String
     * @return BufferedReader BufferedReader {@link BufferedReader}
     * @throws IOException IOException {@link IOException}
     */
    @NotNull
    public static BufferedReader readFromFile(@NotNull String fName) throws IOException {
        String filepath = "/files/" + fName;
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File("").getAbsolutePath() + filepath), StandardCharsets.UTF_8));
    }
}
