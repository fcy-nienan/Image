package JMX;

public interface ConsoleMBean {
    void setState(String state);
    String getState();
    void start();
    void stop();
    void dis(String i);
}
