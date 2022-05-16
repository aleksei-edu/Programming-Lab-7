package com.lapin.common.utility;

import java.util.Stack;

public class OutManager {
    private static Stack<String> out = new Stack<>();
    public static void push(String str){
        out.push(str);
    }
    public static String pop(){
        if (out.empty()){
            return "";
        }
        return out.pop();
    }

    public static void println(String str){
        System.out.println(str);
    }
    public static void print(String str){
        System.out.print(str);
    }

}
