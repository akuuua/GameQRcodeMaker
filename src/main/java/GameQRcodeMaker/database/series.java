package GameQRcodeMaker.database;

import org.json.JSONObject;

public class series {
    private JSONObject json;
    public String end_at;
    public String year;
    public String description;
    public String winner_id;
    public String begin_at;
    public String full_name;
    public String tier;
    public String name;
    public String season;
    public String id;
    public String modified_at;
    public String winner_type;
    public String league_id;
    public String slug;

    public series(){

    }

    public void setJson(JSONObject json){
        this.json = json;
        this.end_at = json.get("end_at").toString();
        this.year = json.get("year").toString();
        this.description = json.get("description").toString();
        this.winner_id = json.get("winner_id").toString();
        this.begin_at = json.get("begin_at").toString();
        this.full_name = json.get("full_name").toString();
        this.tier = json.get("tier").toString();
        this.name = json.get("name").toString();
        this.season = json.get("season").toString();
        this.id = json.get("id").toString();
        this.modified_at = json.get("modified_at").toString();
        this.winner_type = json.get("winner_type").toString();
        this.league_id = json.get("league_id").toString();
        this.slug = json.get("slug").toString();
    }



}
