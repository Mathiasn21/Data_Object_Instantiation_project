package framework.collectors;

import org.jetbrains.annotations.Contract;

/** Class representing valid settings
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public enum Setting {
    ;
    public final String key;

    /**
     * @param key String
     */
    @Contract(pure = true)
    Setting(String key) {
        this.key = key;
    }
}
