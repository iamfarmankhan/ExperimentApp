package com.example.experimentapp;

public class Utility {


    public String stringReversal(String input){
       char[] inputAsArray = input.toCharArray();
       int length = inputAsArray.length;
       int needToLoop = inputAsArray.length/2;
       for (int i=0;i<needToLoop;i++){
           char temp = inputAsArray[i];
           inputAsArray[i] = inputAsArray[length-(i+1)];
           inputAsArray[length-(i+1)] = temp;
       }
       return new String(inputAsArray);
    }


}
