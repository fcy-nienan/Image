package Advanced.JMX;

public class Console implements ConsoleMBean {
    private static String info;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void start() {
        System.out.println("start------");
    }

    @Override
    public void stop() {
        System.out.println("stop--------");
    }
    public void dis(String i){
        System.out.println(i);
    }
    public void test(){
        System.out.println("test");
    }
}
