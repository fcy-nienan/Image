package array;

public class findDiagonalOrder {


    public static void main(String[] args) {
        int[][] data = new int[][]{{1,2,3,4,33},{5,6,7,8,34}};

//        1, 2, 3, 4  1;
//        5, 6 ,7, 8  1;
//        9, 10,11,12 1;

//        0,0;
//        1,0  0,1;
//        0,2  1,1  2,0;
//        2,1  1,2  0,3;
//        1,3  2,2;
//        2,3;
//        0,0;
//        0,1  1,0;
//        0 2  1,1  2,0;
//        0 3  1,2  2,1;
//        0 4  1,3  2,2;
//        1 4  2,3;
//        2 4;
//

        int row = data.length;
        int column = data[0].length;

        int everyTime = 0; //内层每次遍历几次

        boolean needRotate = false;//是否需要旋转

        int startY = 0; //每次开始的y坐标
        int startX = 0; //每次开始的x坐标

        int otherTimes = Math.min(row,column) - 1;//长宽相差

        int xAdd = 1;
        int yAdd = 0;

        for(int times = 1;times<=row+column-1;times++){//需要遍历几次
            boolean positive = times <= otherTimes ? true : false;
            boolean negative = times + otherTimes > row+column-1 ? true : false;
            if (positive){
                everyTime ++;
            }else if(negative){
                everyTime --;
            }else{
                everyTime = row;
            }

            int x = 0;
            int y = 0;

            if (times <= column){
                startX = 0;
                startY = yAdd;
                yAdd++;
            }else{
                startX = xAdd;
                startY = column - 1;
                xAdd++;
            }

            x = startX;
            y = startY;

            for (int outTime = 0;outTime < everyTime;outTime++){
                System.out.print(data[x][y]+",");
                y--;
                x++;
            }
        }


    }

}
