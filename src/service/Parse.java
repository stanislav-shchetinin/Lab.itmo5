package service;

import java.io.*;
import java.util.Scanner;

public class Parse {
    public static String parseFromCSVtoString(Scanner in) throws IOException {
        String res = "";
        String line = "";

        while (in.hasNext()){
            line += in.nextLine().trim();
            if (line.toCharArray()[line.length() - 1] != ','){
                line += ',';
            }
        }
        System.out.println(line);

        char[] charArray = line.toCharArray();
        boolean isInMark = false;

        for (int i = 0; i < charArray.length; ++i){

            if (charArray[i] == '\"' && isInMark){
                isInMark = false;
            } else if (charArray[i] == '\"'){
                isInMark = true;
            }

            if (charArray[i] == ',' && !isInMark && charArray[i - 1] != '\n'){ //на i = 0 позиции не может быть запятая
                charArray[i] = '\n';
            }

        }
        String cur_line = "";
        for (int i = 0; i < charArray.length; ++i){

            if (charArray[i] == '\n'){
                if (cur_line.length() == 0){
                    continue;
                }
                cur_line = cur_line.trim();
                if (cur_line.toCharArray()[0] == '\"'){
                    cur_line = cur_line.substring(1, cur_line.length() - 1);
                }
                if (cur_line != "")
                    res += cur_line + "\n";
                cur_line = "";
            }
            cur_line += charArray[i];

        }

        return res;

    }



}
