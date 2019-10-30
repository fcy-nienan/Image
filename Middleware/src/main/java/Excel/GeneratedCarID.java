package Excel;

import java.util.Random;
import java.util.logging.Logger;

public class GeneratedCarID {
    private static Logger logger = Logger.getLogger(GeneratedCarID.class.getName());

    public static void main(String args[]) throws Exception {

    }
    //    / 车牌号的组成一般为：省份+地区代码+5位数字/字母
    public static String generateCarID() {

        char[] provinceAbbr = { // 省份简称 4+22+5+3
                '京', '津', '沪', '渝',
                '冀', '豫', '云', '辽', '黑', '湘', '皖', '鲁', '苏', '浙', '赣',
                '鄂', '甘', '晋', '陕', '吉', '闽', '贵', '粤', '青', '川', '琼',
                '宁', '新', '藏', '桂', '蒙',
                '港', '澳', '台'
        };
        String alphas = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890"; // 26个字母 + 10个数字

        Random random = new Random(); // 随机数生成器

        String carID = "";

        // 省份+地区代码+·  如 湘A· 这个点其实是个传感器，不过加上美观一些
        carID += provinceAbbr[random.nextInt(34)]; // 注意：分开加，因为加的是2个char
        carID += alphas.charAt(random.nextInt(26));

        // 5位数字/字母
        for (int i = 0; i < 5; i++) {
            carID += alphas.charAt(random.nextInt(36));
        }
        return carID;
    }
}
