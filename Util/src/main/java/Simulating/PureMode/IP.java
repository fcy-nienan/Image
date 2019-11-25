package Simulating.PureMode;

public class IP {
    private String ip;

    public static void main(String args[]) throws Exception {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IP){
            IP ip1= (IP) obj;
            if (ip1.ip.equals(this.ip)){
                return true;
            }
        }
        return false;
    }
}
