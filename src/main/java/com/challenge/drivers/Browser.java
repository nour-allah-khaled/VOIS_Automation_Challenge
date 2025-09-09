package com.challenge.drivers;

public enum Browser {
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeDriverFactory();
        }
    },
    FIREFOX {
        @Override
        public AbstractDriver getDriverFactory() {
            return new FirefoxDriverFactory();
        }
    },
    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return new EdgeDriverFactory();
        }
    };
    public abstract AbstractDriver getDriverFactory();
}
