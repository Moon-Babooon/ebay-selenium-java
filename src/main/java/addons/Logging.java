package addons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {

    private final Logger log = LogManager.getLogger(Logging.class);

    public void logInfo(String message) {
        log.info(message);
    }

    public void logError(String message) {
        log.error(message);
    }

    public void logFatal(String message) {
        log.fatal(message);
    }

    public void logWarning(String message) {
        log.warn(message);
    }

}
