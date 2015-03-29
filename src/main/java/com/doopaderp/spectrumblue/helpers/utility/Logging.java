package com.doopaderp.spectrumblue.helpers.utility;

import com.doopaderp.spectrumblue.helpers.Reference;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class Logging {
    public Logging() {
    }

    public static void logThis(Level logLevel, Object object) {
        FMLLog.log(Reference._NAME, logLevel, String.valueOf(object), new Object[0]);
    }

    public static void all(Object object) {
        logThis(Level.ALL, object);
    }

    public static void debug(Object object) {
        logThis(Level.DEBUG, object);
    }

    public static void error(Object object) {
        logThis(Level.ERROR, object);
    }

    public static void fatal(Object object) {
        logThis(Level.FATAL, object);
    }

    public static void info(Object object) {
        logThis(Level.INFO, object);
    }

    public static void off(Object object) {
        logThis(Level.OFF, object);
    }

    public static void trace(Object object) {
        logThis(Level.TRACE, object);
    }

    public static void warn(Object object) {
        logThis(Level.WARN, object);
    }
}
