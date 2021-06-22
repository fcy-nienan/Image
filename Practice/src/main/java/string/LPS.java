package string;

public class LPS {
    public static void main(String[] args) {
        System.out.println(lps("babad"));
    }
    public static String lps(String str){
        if (str.length() == 0 ) {
            return "";
        }
        int max=0;
        String max_str = "";
        for (int i=0;i<str.length();i++){
            int j=i-1,k=i+1;
            int m=i,n=i+1;
            while (j>=0 && k<str.length() && str.charAt(j) == str.charAt(k)) {
                j--;
                k++;
            }
            while (m>=0&&n<str.length() && str.charAt(m)==str.charAt(n)) {
                m--;
                n++;
            }
            String x="";
            if ((n-m)>(k-j)){
                x=str.substring(m+1,n);
            }else{
                x=str.substring(j+1,k);
            }
            if (x.length() > max) {
                max_str = x;
                max = x.length();
            }
        }
        return max_str;
    }
}
