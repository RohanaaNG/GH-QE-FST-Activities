
package com.example;
public class Sum{
public static void main(String[] args){
    int [] numbers={10, 77, 10, 54, -11, 10};
    int sum=0;
     for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 10) {
                sum = sum + numbers[i];
            }
        }

        if (sum==30){
            System.out.println("True");
        }else{
            System.out.println("False");
        }

}
}