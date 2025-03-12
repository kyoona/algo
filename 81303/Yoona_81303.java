package org.example;

import java.util.*;

public class Yoona_81303 {
    public String solution(int n, int k, String[] cmd) {
        Table[] tables = new Table[n];
        for(int i = 0; i < n; i++){
            tables[i] = new Table(i - 1, i + 1);
        }

        Stack<Integer> trash = new Stack<>();
        for(int i = 0; i < cmd.length; i++){
            String[] ins = cmd[i].split(" ");
            if(ins[0].equals("U")){
                int count = 0;
                while(count < Integer.parseInt(ins[1])){
                    k = tables[k].prev;
                    count++;
                }
            }else if(ins[0].equals("D")){
                int count = 0;
                while(count < Integer.parseInt(ins[1])){
                    k = tables[k].post;
                    count++;
                }
            }else if(ins[0].equals("C")){
                tables[k].deleted = true;
                trash.push(k);
                int prevIndex = tables[k].prev;
                int postIndex = tables[k].post;
                if(prevIndex >= 0){
                    tables[prevIndex].post = postIndex;
                }
                if(postIndex < n){
                    tables[postIndex].prev = prevIndex;
                    k = postIndex;
                }else{
                    k = prevIndex;
                }
            }else if(ins[0].equals("Z")){
                int index = trash.pop();
                tables[index].deleted = false;

                int prevIndex = index - 1;
                while(prevIndex >= 0){
                    if(tables[prevIndex].deleted){
                        prevIndex--;
                    }else{
                        break;
                    }
                }
                if(prevIndex >= 0){
                    tables[prevIndex].post = index;
                }
                tables[index].prev = prevIndex;

                int postIndex = index + 1;
                while(postIndex < n){
                    if(tables[prevIndex].deleted){
                        prevIndex++;
                    }else{
                        break;
                    }
                }
                if(postIndex < n){
                    tables[postIndex].prev = index;
                }
                tables[index].post = postIndex;
            }
        }

        StringBuilder str = new StringBuilder();
        for(Table table : tables){
            str.append(table.deleted ? "X" : "O");
        }

        return str.toString();
    }

    static class Table{
        public int prev;
        public int post;
        public boolean deleted = false;

        public Table(int prev, int post){
            this.prev = prev;
            this.post = post;
        }
    }
}
