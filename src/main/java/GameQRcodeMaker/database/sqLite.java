package GameQRcodeMaker.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class sqLite {
    private JSONObject json;
    private JSONArray array;
    private boolean hasImport;
    public sqLite() {
        this.hasImport = false;
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public void setJson(JSONObject json){
        this.json = json;
        this.array = array = json.getJSONArray("League List");
    }

    public void createLeagueTable(){
        {
            Connection c = null;
            Statement s = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:test.db");


                s = c.createStatement();
                String sql ="DROP TABLE IF EXISTS League;"+
                        "CREATE TABLE IF NOT EXISTS League" +
                        "(id TEXT NOT NULL," +
                        " name          TEXT, " +
                        " image_url     TEXT, " +
                        " series     TEXT, " +
                        " videoGame     TEXT, " +
                        " modified_at   TEXT, " +
                        " slug          TEXT, " +
                        " url           TEXT)";
                s.executeUpdate(sql);
                s.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("League table created successfully");
        }
    }


    public void insertLeague() {
        Connection c = null;
        Statement stmt = null;
        league league = new league(json);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            for(int i = 0; i<= array.length()-1;i++){
                String sql = "INSERT INTO League (id,name,image_url,series,videoGame,modified_at,slug,url) " +
                        "VALUES (" +"'"+ league.getId(i)+"'" + ", " + "'"+league.getName(i)+"'"+ "," + "'" +
                        league.getImage_url(i) + "'" + "," + "'"+ league.getSeries(i) + "'" + "," +
                        "'"+league.getVideoGame(i) + "'" + ","+ "'" + league.getModified_at(i) + "'" + ","+ "'" +
                        league.getSlug(i)+"'"+ ","+"'" +league.getUrl(i)+ "'"+ " );";

                stmt.executeUpdate(sql);

            }
            this.hasImport = true;
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("League records created successfully");
    }

    public List<String> getLeagues() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM League;");
            List<String> result = new ArrayList<String>();
            while (rs.next()) {
                String name = new String(rs.getString("name").getBytes("UTF-8"),"utf-8");
                result.add(name);
            }
            rs.close();
            stmt.close();
            c.close();
            return result;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }

    public String getLeagueID(String nm) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM League WHERE name = "+"'"+nm +"'" +";");

            String id = rs.getString("id");

            rs.close();
            stmt.close();
            c.close();
            return id;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }


    public void createSeriesTable(){
        {
            Connection c = null;
            Statement s = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:test.db");

                s = c.createStatement();
                String sql ="DROP TABLE IF EXISTS Series;"+
                        "CREATE TABLE IF NOT EXISTS Series" +
                        "(end_at            TEXT," +
                        " year              TEXT, " +
                        " description       TEXT, " +
                        " winner_id         TEXT, " +
                        " begin_at          TEXT, " +
                        " full_name         TEXT, " +
                        " tier              TEXT, " +
                        " name              TEXT, " +
                        " season            TEXT, " +
                        " id                TEXT, " +
                        " modified_at       TEXT, " +
                        " winner_type       TEXT, " +
                        " league_id         TEXT, " +
                        " slug              TEXT)";
                s.executeUpdate(sql);
                s.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Series table created successfully");
        }
    }

    public void insertSeries() {
        Connection c = null;
        Statement stmt = null;
        league league = new league(json);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            for(int i = 0; i<= array.length()-1;i++){
                String s ="";

                s = array.getJSONObject(i).get("series").toString();
                s=  s.substring(1);
                s = s.substring(0, s.length() - 1);

                s = "{Series List:[" + s + "]}";
                JSONObject jo = new JSONObject(s);

                JSONArray seriesArray = jo.getJSONArray("Series List");
                for(int j=0; j < seriesArray.length();j++){
                    JSONObject obj = seriesArray.getJSONObject(j);
                    series series = new series();
                    series.setJson(obj);
                    String sql = "INSERT INTO Series (end_at, year, description, winner_id , begin_at, full_name, tier, name, season, id, modified_at, winner_type, league_id, slug) " +
                            "VALUES (" +"'"+ series.end_at +"'" + ", " + "'"+series.year+"'"+ "," + "'" +
                            series.description + "'" + "," + "'"+ series.winner_id + "'" + "," +
                            "'"+series.begin_at + "'" + ","+ "'" + series.full_name + "'" + ","+ "'" +
                            series.tier+"'"+ ","+"'" +series.name+ "'"+","+"'"+
                            series.season+"'"+ ","+"'" +series.id+ "'"+","+"'"
                            +
                            series.modified_at+"'"+ ","+"'" +series.winner_type+ "'"
                            +","+"'"+
                            series.league_id+"'"+ ","+"'" +series.slug+"'"+ " );";

                    stmt.executeUpdate(sql);
                }





            }
            this.hasImport = true;
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Series records created successfully");
    }

    public String getSeriesReport(String leagueId) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Series WHERE league_id = "+"'" +leagueId+"'" +";");

            String result = "";

            while(rs.next()){
                result = result+"Begin at: " + rs.getString("begin_at") + "\n "
                        + "Description: "+ rs.getString("description") + "\n "
                        + "End at: " + rs.getString("end_at") + "\n "
                        + "Full name: " + rs.getString("full_name") + "\n "
                        + "ID: " + rs.getString("id") + "\n "
                        + "League ID: " + rs.getString("league_id") + "\n "
                        + "Modified at: " + rs.getString("modified_at") + "\n "
                        + "Name: " + rs.getString("name") + "\n "
                        + "Season: " + rs.getString("season") + "\n "
                        + "Slug: " + rs.getString("slug") + "\n "
                        + "Tier: " + rs.getString("tier") + "\n "
                        + "Winner ID: " + rs.getString("winner_ID") + "\n "
                        + "Winner type: " + rs.getString("winner_type") + "\n "
                        + "Year: " + rs.getString("year") + "\n ";

            }
            rs.close();
            stmt.close();
            c.close();
            return result;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }




    public String getSeriesForUI(String leagueId,int index) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Series WHERE league_id = "+"'" +leagueId+"'" +";");

            String result = "";
            int number = 0;
            while(rs.next()){
                if (number == index){
                    result = "<html>"+ "Begin at: " + rs.getString("begin_at") + "\n "+ "<br/>"
                            + "Description: "+ rs.getString("description") + "\n "+ "<br/>"
                            + "End at: " + rs.getString("end_at") + "\n "+ "<br/>"
                            + "Full name: " + rs.getString("full_name") + "\n "+ "<br/>"
                            + "ID: " + rs.getString("id") + "\n "+ "<br/>"
                            + "League ID: " + rs.getString("league_id") + "\n "+ "<br/>"
                            + "Modified at: " + rs.getString("modified_at") + "\n "+ "<br/>"
                            + "Name: " + rs.getString("name") + "\n "+ "<br/>"
                            + "Season: " + rs.getString("season") + "\n "+ "<br/>"
                            + "Slug: " + rs.getString("slug") + "\n "+ "<br/>"
                            + "Tier: " + rs.getString("tier") + "\n "+ "<br/>"
                            + "Winner ID: " + rs.getString("winner_ID") + "\n "+ "<br/>"
                            + "Winner type: " + rs.getString("winner_type") + "\n "+ "<br/>"
                            + "Year: " + rs.getString("year") + "\n "+ "<br/>";
                    result += "<html>";
                }else{
                    number+=1;
                }
            }
            rs.close();
            stmt.close();
            c.close();

            return result;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }

    public String getSeriesName(String leagueId,int index) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Series WHERE league_id = "+"'" +leagueId+"'" +";");

            String result = "";
            int number = 0;
            while(rs.next()){
                if (number == index){
                    result +="This league has the following series: ";
                    result = rs.getString("full_name");

                }else{
                    number+=1;
                }

            }

            rs.close();
            stmt.close();
            c.close();

            return result;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }

    public int getSeriesNumber(String leagueId) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Series WHERE league_id = "+"'" +leagueId+"'" +";");

            int result = 0;

            while(rs.next()){
                result += 1 ;
            }

            rs.close();
            stmt.close();
            c.close();
            return result;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return 0;
    }

    



}
