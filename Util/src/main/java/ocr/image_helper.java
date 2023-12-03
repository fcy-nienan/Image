package ocr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class image_helper {

    public static void main(String[] args) throws Exception {
        zoomInImage("E:\\1.jpg","E:\\zoom_1.jpg",4);
//        rgb_to_gray();

        grayImage(1,"E:\\zoom_1.jpg", "E:\\new123.jpg");//最大值法灰度化
//        grayImage(2,"E:\\zoom_1.jpg", "E:\\new1232.jpg");//最小值法灰度化
//        grayImage(3,"E:\\zoom_1.jpg", "E:\\new123.jpg");//平均值法灰度化
//        grayImage(4,"E:\\zoom_1.jpg", "E:\\new123.jpg");//加权法灰度化
    }

    public static void rgb_to_gray() throws Exception{
        String filename = "E:\\zoom_1.jpg";
        File file = new File(filename);
        BufferedImage bi = ImageIO.read(file);
        // 获取当前图片的高,宽,ARGB
        int h = bi.getHeight();
        int w = bi.getWidth();
        int rgb = bi.getRGB(0, 0);
        int arr[][] = new int[w][h];

        // 获取图片每一像素点的灰度值
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                // getRGB()返回默认的RGB颜色模型(十进制)
                arr[i][j] = getImageRgb(bi.getRGB(i, j));//该点的灰度值
            }

        }

        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);//  构造一个类型为预定义图像类型之一的 BufferedImage，TYPE_BYTE_BINARY（表示一个不透明的以字节打包的 1、2 或 4 位图像。）
        int FZ = 180;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int rs = arr[i][j];
                if (rs < FZ){
                    int black = new Color(255, 255, 255).getRGB();
                    bufferedImage.setRGB(i, j, black);
                }else{
                    int white = new Color(0, 0, 0).getRGB();
                    bufferedImage.setRGB(i, j, white);
                }
//
//                if (getGray(arr, i, j, w, h) > FZ) {
//                    int black = new Color(255, 255, 255).getRGB();
//                    bufferedImage.setRGB(i, j, black);
//                } else {
//                    int white = new Color(0, 0, 0).getRGB();
//                    bufferedImage.setRGB(i, j, white);
//                }
            }

        }
        ImageIO.write(bufferedImage, "jpg", new File("E:\\new123.jpg"));
    }

    private static int getImageRgb(int i) {
        String argb = Integer.toHexString(i);// 将十进制的颜色值转为十六进制
        // argb分别代表透明,红,绿,蓝 分别占16进制2位
        int r = Integer.parseInt(argb.substring(2, 4), 16);//后面参数为使用进制
        int g = Integer.parseInt(argb.substring(4, 6), 16);
        int b = Integer.parseInt(argb.substring(6, 8), 16);
        int result = (int) ((r + g + b) / 3);
        return result;
    }


    //自己加周围8个灰度值再除以9，算出其相对灰度值
    public static int getGray(int gray[][], int x, int y, int w, int h) {
        int rs = gray[x][y]
                + (x == 0 ? 255 : gray[x - 1][y])
                + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
                + (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])
                + (y == 0 ? 255 : gray[x][y - 1])
                + (y == h - 1 ? 255 : gray[x][y + 1])
                + (x == w - 1 ? 255 : gray[x + 1][y])
                + (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
                + (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
        return rs / 9;
    }


    public static BufferedImage  zoomInImage(BufferedImage  originalImage, Integer times){

        int width = originalImage.getWidth()*times;

        int height = originalImage.getHeight()*times;

        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

        Graphics g = newImage.getGraphics();

        g.drawImage(originalImage, 0,0,width,height,null);

        g.dispose();

        return newImage;

    }

    /**

     * 对图片进行放大

     * @param srcPath 原始图片路径(绝对路径)

     * @param newPath 放大后图片路径（绝对路径）

     * @param times 放大倍数

     * @return 是否放大成功

     */

    public static boolean zoomInImage(String srcPath,String newPath,Integer times){

        BufferedImage bufferedImage = null;

        try {

            File of = new File(srcPath);

            if(of.canRead()){

                bufferedImage =  ImageIO.read(of);

            }

        } catch (IOException e) {

            //TODO: 打印日志

            return false;

        }

        if(bufferedImage != null){

            bufferedImage = zoomInImage(bufferedImage,times);

            try {

                //TODO: 这个保存路径需要配置下子好一点

                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //保存修改后的图像,全部保存为JPG格式

            } catch (IOException e) {

                // TODO 打印错误信息

                return false;

            }

        }

        return true;

    }

    /**

     * 对图片进行缩小

     * @param originalImage 原始图片

     * @param times 缩小倍数
     *
     * @param reducewidth 缩放后减小宽度 (当times=-1时此参数及是图片宽度)
     * @param reduceheight缩放后减小高度(同上)

     * @return 缩小后的Image

     */

    public static BufferedImage  zoomOutImage(BufferedImage  originalImage, Integer times,int reducewidth,int reduceheight){

        int width = originalImage.getWidth()/times-reducewidth;
        if(width < 0){
            width=originalImage.getWidth();
        }
        int height = originalImage.getHeight()/times-reduceheight;
        if(height < 0){
            height=originalImage.getHeight();
        }
        if(times == -1){
            width= reducewidth;
            height =reduceheight;
        }
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

        Graphics g = newImage.getGraphics();

        g.drawImage(originalImage, 0,0,width,height,null);

        g.dispose();

        return newImage;

    }

    /**

     * 对图片进行缩小

     * @param srcPath 源图片路径（绝对路径）

     * @param newPath 新图片路径（绝对路径）

     * @param times 缩小倍数

     * @return 保存是否成功

     */

    public static boolean zoomOutImage(String srcPath,String newPath,Integer times){

        BufferedImage bufferedImage = null;

        try {

            File of = new File(srcPath);

            if(of.canRead()){

                bufferedImage =  ImageIO.read(of);

            }

        } catch (IOException e) {

            //TODO: 打印日志

            return false;

        }

        if(bufferedImage != null){

            bufferedImage = zoomOutImage(bufferedImage,times,0,0);

            try {

                //TODO: 这个保存路径需要配置下子好一点

                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //保存修改后的图像,全部保存为JPG格式

            } catch (IOException e) {

                // TODO 打印错误信息

                return false;

            }

        }

        return true;

    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    /**
     * 图片灰度化的方法
     * @param status 灰度化方法的种类，1表示最大值法，2表示最小值法，3表示均值法，4加权法
     * @param imagePath 需要灰度化的图片的位置
     * @param outPath 灰度化处理后生成的新的灰度图片的存放的位置
     * @throws IOException
     */
    public static void grayImage(int status,String imagePath, String outPath) throws IOException {
        File file = new File(imagePath);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height,  image.getType());
        //BufferedImage grayImage = new BufferedImage(width, height,  BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = image.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray=0;
                if(status==1){
                    gray=getBigger(r, g, b);//最大值法灰度化
                }else if(status==2){
                    gray=getSmall(r, g, b);//最小值法灰度化
                }else if(status==3){
                    gray=getAvg(r, g, b);//均值法灰度化
                }else if(status==4){
                    gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);//加权法灰度化
                }
                grayImage.setRGB(i, j, colorToRGB(0, gray, gray, gray));
            }
        }
        File newFile = new File(outPath);
        ImageIO.write(grayImage, "jpg", newFile);
    }

    //比较三个数的大小
    public static int getBigger(int x,int y,int z){
        if(x>=y&&x>=z){
            return x;
        }else if(y>=x&&y>=z){
            return y;
        }else if(z>=x&&z>=y){
            return z;
        }else{
            return 0;
        }
    }

    //比较三个是的大小取最小数
    public static int getSmall(int x,int y,int z){
        if(x<=y&&x<=z){
            return x;
        }else if(y>=x&&y>=z){
            return y;
        }else if(z>=x&&z>=y){
            return z;
        }else{
            return 0;
        }
    }

    //均值法
    public static int getAvg(int x,int y,int z){
        int avg=(x+y+z)/3;
        return avg;
    }
}