package logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4JNames {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int digital = 23423;
        byte byteVariable = 4;
        short min = 101;
        long max = 324234235423L;

        double d = 4234.03;
        float f = 5436346.4444f;

        boolean toBeOrNotToBe = true;
        char exit = 'E';

        LOG.debug("Digital: {}, byteVariable: {}, min: {}, max: {}, d: {}, f: {}, toBeOrNotToBe: {}, exit: {}, ",
                digital, byteVariable, min, max, d, f, toBeOrNotToBe, exit);
    }
}