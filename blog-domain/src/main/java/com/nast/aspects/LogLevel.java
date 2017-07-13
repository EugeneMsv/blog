package com.nast.aspects;

import org.slf4j.Logger;

public enum LogLevel {

    TRACE {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.trace(pattern, objects);
        }

        @Override
        boolean isEnabled(Logger logger) {
            return logger.isTraceEnabled();
        }
    }, INFO {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.info(pattern, objects);
        }

        @Override
        boolean isEnabled(Logger logger) {
            return logger.isInfoEnabled();
        }
    }, DEBUG {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.debug(pattern, objects);
        }

        @Override
        boolean isEnabled(Logger logger) {
            return logger.isDebugEnabled();
        }
    }, ERROR {
        @Override
        void log(Logger logger, String pattern, Object... objects) {
            logger.error(pattern, objects);
        }

        @Override
        boolean isEnabled(Logger logger) {
            return logger.isErrorEnabled();
        }
    };

    private static final String INPUT_PATTERN = "Called {} method: {} with args: {}";
    private static final String OUTPUT_PATTERN = " {} method: {} returned: {}";

    void callLogger(Logger logger, Class<?> targetClass, String signatureName) {
        callLogger(logger, targetClass, signatureName, null);
    }

    void callLogger(Logger logger, Class<?> targetClass, String signatureName, Object[] args) {
        log(logger, INPUT_PATTERN, targetClass, signatureName, args);
    }

    void callLogger(Logger logger, Class<?> targetClass, String signatureName, Object proceedResult) {
        log(logger, OUTPUT_PATTERN, targetClass, signatureName, proceedResult);
    }

    abstract void log(Logger logger, String pattern, Object... objects);

    abstract boolean isEnabled(Logger logger);
}
