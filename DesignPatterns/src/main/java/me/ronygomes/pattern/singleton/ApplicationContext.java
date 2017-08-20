package me.ronygomes.pattern.singleton;

/**
 * @author Rony Gomes <rgomes.bd@gmail.com>
 * @since 2015.12.13
 */
public class ApplicationContext {

    private static ApplicationContext instance;
    private String config;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }

        return instance;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }
}
