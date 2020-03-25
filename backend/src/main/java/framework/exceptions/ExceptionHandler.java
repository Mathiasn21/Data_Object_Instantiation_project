package framework.exceptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/** Class for describing a code and a message describing the error
 * @author Maria Elinor Pedersen Github: https://github.com/marped
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public enum ExceptionHandler {
    ERROR_MISSING_RESOURCE(1, "Missing resource, make sure no files are missing"),
    ERROR_WRONG_DATA_OBJECT(2, "ERROR, wrong data format on object"),
    ERROR_NO_EXISTING_COLUMN(3, "ERROR, there are no existing column with that name"),
    ERROR_NO_PRIMARY_COLUMNS(4, "ERROR, can not find any primary columns"),
    ERROR_WRONG_URL(5, "This URL is not accurate or does not exist"),
    ERROR_WRONG_FILE_PATH(6, "This file path is not accurate or does not exist");

    public final int CODE;
    public final String ERROR_MSG;

    ExceptionHandler(int code, String errorMsg) {
        CODE = code;
        ERROR_MSG = errorMsg;
    }

    public static final String LOGS = "./logs/";

    public static void createLogWithDetails(@NotNull ExceptionHandler errMsg, @NotNull Throwable error ) throws IOException {
        File log = new File(LOGS + errMsg.CODE + "-" + System.currentTimeMillis() + ".txt");
        error.printStackTrace(new PrintStream(log));
    }
}
