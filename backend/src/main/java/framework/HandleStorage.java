package framework;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class HandleStorage {

    /**
     * Standard method for writing data to a given file.
     * This methods does not append but overwrites!
     * Utilizes a relative path for top level directory, plus filename.extension
     *
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


    @NotNull
    @Contract(pure = true)
    private static String[] splitLineOn(@NotNull String line, @NotNull String usingDelimiter){
        return line.split(usingDelimiter);
    }


    /**
     * Standard reading from a file. Utilizes a relative path given filename.extension.
     * Files must exist in the top level directory.
     * @param fName String
     * @return String
     * @throws IOException IOException {@link IOException}
     */
    @NotNull
    private static Collection<String[]> readFromFile(@NotNull String fName, @NotNull String delimiter) throws IOException {
        String line;
        Collection<String[]> rows = new ArrayList<>();

        //TODO: split line on delimiter
        //TODO: store rows as linear arrays
        String filepath = "/files/" + fName;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("").getAbsolutePath() + filepath), StandardCharsets.UTF_8));
        while ((line = bufferedReader.readLine()) != null) {
            rows.add(splitLineOn(delimiter, line));
        }
        return rows;
    }
}
