package com.example.pidthesstransport;

public class PassIdGenerator {

    //Fils a table with random integers
    public static void fill(int[] part1) {

        for (int i=0;i<part1.length;i++){
            part1[i]=(int)(Math.random()*10);
        }

    }

    //Turns a table of ints into a string
    public static String toString(int P[]){
        String tmp="";
        for (int i=0;i<P.length;i++){
            tmp+= String.valueOf(P[i]);
        }

        return tmp;

    }

    //Generates the total value of a String
    public static int GenerateValue(String s){
        if (s.length()==0) return 0;
        return (Integer.parseInt(s.substring(0,1)))+GenerateValue(s.substring(1));
    }

    //Generates a second string giving the power and the length of the first
    public static String GenerateSecondString(int length, int power){

        if (length==1) return power+"";

        int number;
        int index = power - length;
        if (index>9) index =9;

        number = (int) (Math.random()*(index+1));
        if (((double)power)/length > 8) number = 9;
        if (power==length) number = 1;

        
        return number+""+GenerateSecondString(length-1,power-number);

    }





}
