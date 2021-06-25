package GameQRcodeMaker.database;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class league {
    private JSONObject json;
    private JSONArray array;


    public league(JSONObject json){
        this.json = json;
        this.array = array = json.getJSONArray("League List");
    }

    public String getId(int index){
        String result = array.getJSONObject(index).get("id").toString();
        return result;
    }

    public String getName(int index){
        String result = array.getJSONObject(index).get("name").toString();
        return result;
    }



    public String getSeries(int index){
        String result = array.getJSONObject(index).get("series").toString();
        return result;
    }

    public String getUrl(int index){
        String result = array.getJSONObject(index).get("url").toString();
        return result;
    }

    public String getImage_url(int index){
        String result = array.getJSONObject(index).get("image_url").toString();
        return result;
    }


    public String getVideoGame(int index){
        String result = array.getJSONObject(index).get("videogame").toString();
        return result;
    }

    public String getModified_at(int index){
        String result = array.getJSONObject(index).get("modified_at").toString();
        return result;
    }

    public String getSlug(int index){
        String result = array.getJSONObject(index).get("slug").toString();
        return result;
    }




}