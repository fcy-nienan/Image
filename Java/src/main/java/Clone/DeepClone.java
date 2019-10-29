package Clone;

public  class DeepClone implements Cloneable {

    //    深度克隆
    public Object clone() {
        DeepClone u = null;
        try {
            u = (DeepClone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        u.address = (NormalClone) address.clone();
        return u;
    }

    private NormalClone address;
    private String username;
    private String password;

    public NormalClone getAddress() {
        return address;
    }

    public void setAddress(NormalClone address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "address=" + address +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public DeepClone(NormalClone address, String username, String password) {
        this.address = address;
        this.username = username;
        this.password = password;
    }
}
