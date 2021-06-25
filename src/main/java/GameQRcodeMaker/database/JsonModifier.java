package GameQRcodeMaker.database;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class JsonModifier {

    public static JSONObject JsonReader(String fileName) {
        File jsonFile = new File(fileName);
        try {
            FileReader FR = new FileReader(jsonFile);
            BufferedReader BR = new BufferedReader(FR);
            StringBuffer SB = new StringBuffer();
            int i = 0;
            while ((i = BR.read()) != -1) {
                SB.append((char) i);
            }
            JSONObject jsonResult = new JSONObject(SB.toString());
            return jsonResult;
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void JsonWriter(String fileName, String input) {
        BufferedWriter writer = null;
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Input
        try {

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
//            input = input.substring(1);
//            input = input.substring(0,input.length() - 1);
            input = "{League List:" + input + "}";
            writer.write(input);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
