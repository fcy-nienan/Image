package query;

public class BinarySearch {
    public static void main(String args[]) {
        int[] intArray=new int[]{1,2,3,4,5,6,7,8,9};
        for(int i=0;i<intArray.length;i++){
            test1(intArray,intArray[i]);
        }

    }
    public static void test1(int[] intArray,int target){
        boolean exist=binarySearch(intArray,target);
        System.out.println(exist);
    }
    public static boolean binarySearch(int[] intArray,int target){
        if (intArray==null)
            throw new NullPointerException("array and strategy unable to be null!");
        int low=0,high=intArray.length-1,count=0;
        while(low<=high){
            count++;
            int mid=(low+high)/2;
            if (intArray[mid]==target){
                System.out.printf("%d \r\n",count);
                return true;
            }else if (intArray[mid]>target){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return false;
    }
    public static boolean binarySearchDG(int[] array,int target,int low,int high){
        if (low<=high){
            int mid=(low+high)/2;
            if (array[mid]==target){
                return true;
            }else if (array[mid]>target){
                return binarySearchDG(array,target,low,mid-1);
            }else{
                return binarySearchDG(array,target,mid+1,high);
            }
        }
        return false;
    }
}
