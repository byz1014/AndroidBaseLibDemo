package com.base.luculent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @Author byz
 * @CreateData 2021/1/15 9:00
 * @blame Android Team
 * @Content
 */
public class TestDome {
     String redBallList[] ={"01","02","03","04","05","06","07","08","09","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31","32","33"};
     String blueBallList[] = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16" };


     ArrayList<Integer> numlist = new ArrayList<>();



     public String getBallValue(){
         Random random =  new Random();

         StringBuilder stringBuilder = new StringBuilder();
         int length  =  6;
         for(int i=0;i<length;i++){
             int index = random.nextInt(redBallList.length);
             if(numlist.contains(index)){
                 length++;
                 continue;
             }
             numlist.add(random.nextInt(redBallList.length));
         }


         Collections.sort(numlist);

         for(int i = 0;i<numlist.size();i++){
             stringBuilder.append(redBallList[numlist.get(i)]);
             stringBuilder.append(" ");
         }

         stringBuilder.append(blueBallList[random.nextInt(blueBallList.length)]);

         return stringBuilder.toString();
     }

    public static void main(String str[]){
        System.out.println(String.format("%2d",1));
    }
}


