package com.base.luculent;

import android.util.Log;

/**
 * @Author byz
 * @CreateData 2021/4/19 17:14
 * @blame Android Team
 * @Content
 */
public class TestAlgorithm {

    static int j ,i;
    public static void main(String[] args) {


        test2(numArray,0,numArray.length-1);


//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i=0;i<numArray.length;i++){
//            stringBuilder.append(numArray[i])
//                    .append(",");
//        }
//
//        System.out.println(stringBuilder.toString());
    }
    static String message = "";

    private static void test(int[] arr,int begin,int end){
        if(begin > end) {
            return;
        }
        i = begin; j = end;
        int tmp = arr[i];
        while (i!=j){
            message = "";
            while (arr[j]>=tmp && j>i) {
                j--;
            }
            while (arr[i]<=tmp && j>i){
                i++;
            }
            if(j>i){
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
            message += (i+"  "+j+"  ");
            for(int a=0;a<arr.length;a++){
                message+=(arr[a]+",");
            }
            System.out.println(message);

        }
        arr[begin] = arr[i];
        arr[i] = tmp;


        message ="end  " + i +"    "+j+"   ";
        for(int a=0;a<arr.length;a++){
            message+=(arr[a]+",");
        }
        System.out.println(message);
//        test(arr,begin,i-1);
//        test(arr,i+1,end);
    }


    private static int[] numArray={100,20,60,89,45,232,89,15,78,12,789,1,45,122,47};
    private static void test2(int[] arr,int begin,int end){
        int i = begin;
        int j = end;
        if(i>j){
            return;
        }

        int tem = arr[i];
        while (i!=j){

            while (arr[j]>= tem && i<j){
                j--;
            }

            while (arr[i]<=tem && i<j){
                i++;
            }

            int num = arr[i];
            arr[i] = arr[j];
            arr[j] = num;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i)
                    .append("   ")
                    .append(j)
                    .append("   ");
            for(int a=0;a<arr.length;a++){
                stringBuilder.append(arr[a])
                        .append(",");
            }
            System.out.println(stringBuilder.toString());
        }

        arr[begin] = arr[i];
        arr[i] = tem;


        test2(arr,begin,i-1);
        test2(arr,i+1,end);

        StringBuilder stringBuilder = new StringBuilder();
        for(int a=0;a<arr.length;a++){
            stringBuilder.append(arr[a])
                    .append(",");
        }
        System.out.println(stringBuilder.toString());
    }

}
