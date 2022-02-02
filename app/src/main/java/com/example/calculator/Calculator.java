package com.example.calculator;

import java.util.*;

public class Calculator {
    ArrayList<String> list = new ArrayList<>();


    boolean validateUserInput (String Input){
        if ((Input.chars().allMatch(Character::isDigit) && ( list.size() > 0 &&
                list.get(list.size() - 1).chars().allMatch(Character::isDigit)) )){

            return false;
        }

        return !Input.chars().allMatch(c -> c == '+' || c == '-' || c == '/' || c == '*') ||
                (list.size() <= 0 || !list.get(list.size() - 1).chars().allMatch(c -> c == '+' || c == '-' || c == '/' || c == '*'));
    }


    boolean validateCalculator(){
        return list.size() >= 2;
    }


    int calculate() {
        int firstElement;
        String secondElement;
        int thirdElement;

        //This check will see if the first argument is an operator, if it is then it will add a 0 to te beginning of the calculation.
        if (list.get(0).chars().allMatch(c -> c == '+' || c == '-' || c == '/' || c == '*')) {
            list.add(0, "0");
        }

        if (list.get(list.size() - 1).chars().allMatch(c -> c == '+' || c == '-' || c == '/' || c == '*')) {
            list.add("0");
        }

        firstElement = Integer.parseInt(list.get(0));
        secondElement = (list.get(1));
        thirdElement = Integer.parseInt(list.get(2));


        while (list.size() > 1) {

            switch (secondElement) {
                case ("+"): {
                    firstElement += thirdElement;
                    break;
                }
                case ("-"): {
                    firstElement -= thirdElement;
                    break;
                }
                case ("/"): {
                    firstElement /= thirdElement;
                    break;
                }
                case ("*"): {
                    firstElement *= thirdElement;
                    break;
                }
                case ("%"):{
                    firstElement %= thirdElement;
                    break;
                }
                case ("POW"):{

                    firstElement = (int) Math.pow(firstElement,thirdElement);
                    break;
                }
                case ("MAX"):{

                    firstElement = firstElement > thirdElement ? firstElement: thirdElement;
                    break;
                }
                case ("MIN"):{

                    firstElement = firstElement < thirdElement ? firstElement: thirdElement;
                    break;
                }
                default: {
                    return 0;
                }
            }

            list.remove(2);
            list.remove(1);

            if (list.size() > 1) {
                secondElement = (list.get(1));
                thirdElement = Integer.parseInt(list.get(2));
            }
        }
        return firstElement;
    }


    void push (String value){
        list.add(value);
    }






}