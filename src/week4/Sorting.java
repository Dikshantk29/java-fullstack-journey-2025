package week4;

public class Sorting {
    public static void main(String[] args) {

    }
    static int[] bubbleSort(int[] arr){
        int n =arr.length;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return  arr;
    }

//    static int[]  insertionSort(int[] arr){
//        for(int i=0;i < n;i++){
//            int minPos = i;
//            for(int j = i+1;j < n;j++){
//
//
//            }
//
//        }
//    }
}
