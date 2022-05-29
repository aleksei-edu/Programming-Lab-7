package com.lapin.common.utility;

import com.lapin.network.StatusCodes;

import java.util.Map;
import java.util.Stack;

public class OutManager {
    private static Stack<Pair<StatusCodes,String>> out = new Stack<>();
    public static void push(StatusCodes st, String str){
        out.push(new Pair<>(st,str) );
    }
    public static Pair pop(){
        if (out.empty()){
            return null;
        }
        return out.pop();
    }

    public static void println(String str){
        System.out.println(str);
    }
    public static void println(){
        System.out.print(OutManager.pop().getSecond());
    }

}
