package com.lapin.common.utility;

import com.lapin.common.utility.Pair;
import com.lapin.network.StatusCodes;

import java.util.Stack;

public class OutResultStack {
    private static Stack<Pair<StatusCodes,Object>> out = new Stack<>();
    public static void push(StatusCodes st, Object obj){
        out.push(new Pair<>(st,obj) );
    }
    public static Pair pop(){
        if (out.empty()){
            return new Pair(StatusCodes.OK,"I have nothing to say here :(");
        }
        return out.pop();
    }
}
