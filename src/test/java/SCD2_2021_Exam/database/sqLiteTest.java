package SCD2_2021_Exam.database;
import GameQRcodeMaker.database.JsonModifier;
import GameQRcodeMaker.database.sqLite;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class sqLiteTest {
    private GameQRcodeMaker.database.sqLite sqLite;

    @Test
    public void getSeriesReport(){
        sqLite sqLite = new sqLite();
        org.json.JSONObject json = JsonModifier.JsonReader("data.json");
        sqLite.setJson(json);
        String actul = sqLite.getSeriesReport("4584");
        String except = "Begin at: 2020-03-17T19:00:00Z\n" +
                " Description: null\n" +
                " End at: 2020-05-02T21:01:00Z\n" +
                " Full name: Season 3 2020\n" +
                " ID: 2546\n" +
                " League ID: 4584\n" +
                " Modified at: 2021-05-14T11:10:26Z\n" +
                " Name: \n" +
                " Season: 3\n" +
                " Slug: cs-go-esl-swiss-league-3-2020\n" +
                " Tier: d\n" +
                " Winner ID: 126501\n" +
                " Winner type: Team\n" +
                " Year: 2020\n" +
                " Begin at: 2020-08-27T18:00:00Z\n" +
                " Description: null\n" +
                " End at: 2020-10-10T19:31:00Z\n" +
                " Full name: Season 4 2020\n" +
                " ID: 2943\n" +
                " League ID: 4584\n" +
                " Modified at: 2021-05-14T11:30:20Z\n" +
                " Name: \n" +
                " Season: 4\n" +
                " Slug: cs-go-esl-swiss-league-4-2020\n" +
                " Tier: d\n" +
                " Winner ID: 127188\n" +
                " Winner type: Team\n" +
                " Year: 2020\n" +
                " Begin at: 2021-03-16T19:00:00Z\n" +
                " Description: null\n" +
                " End at: 2021-05-08T18:32:00Z\n" +
                " Full name: Season 5 2021\n" +
                " ID: 3422\n" +
                " League ID: 4584\n" +
                " Modified at: 2021-05-14T11:31:09Z\n" +
                " Name: \n" +
                " Season: 5\n" +
                " Slug: cs-go-esl-swiss-league-5-2021\n" +
                " Tier: d\n" +
                " Winner ID: 128703\n" +
                " Winner type: Team\n" +
                " Year: 2021"+"\n ";
        assertEquals(except,actul);

    }

    @Test
    public void getSeriesReportForUI(){
        sqLite sqLite = new sqLite();
        org.json.JSONObject json = JsonModifier.JsonReader("data.json");
        sqLite.setJson(json);
        String actul = sqLite.getSeriesForUI("4584",0);
        String except = "<html>Begin at: 2021-03-16T19:00:00Z\n" +
                " <br/>Description: null\n" +
                " <br/>End at: 2021-05-08T18:32:00Z\n" +
                " <br/>Full name: Season 5 2021\n" +
                " <br/>ID: 3422\n" +
                " <br/>League ID: 4584\n" +
                " <br/>Modified at: 2021-05-14T11:31:09Z\n" +
                " <br/>Name: \n" +
                " <br/>Season: 5\n" +
                " <br/>Slug: cs-go-esl-swiss-league-5-2021\n" +
                " <br/>Tier: d\n" +
                " <br/>Winner ID: 128703\n" +
                " <br/>Winner type: Team\n" +
                " <br/>Year: 2021\n" +
                " <br/><html>";
        assertEquals(except,actul);
    }

    @Test
    public void getSeriesName(){
        sqLite sqLite = new sqLite();
        org.json.JSONObject json = JsonModifier.JsonReader("data.json");
        sqLite.setJson(json);
        String actul = sqLite.getSeriesName("4584",0);
        String except = "Season 5 2021";
        assertEquals(except,actul);
    }

    @Test
    public void getgetSeriesNameIndex(){
        sqLite sqLite = new sqLite();
        org.json.JSONObject json = JsonModifier.JsonReader("data.json");
        sqLite.setJson(json);
        String actul = sqLite.getSeriesName("4584",1);
        String except = "Season 5 2021";
        assertEquals(except,actul);
    }

    @Test
    public void getgetSeriesNameIndexOOR(){
        sqLite sqLite = new sqLite();
        org.json.JSONObject json = JsonModifier.JsonReader("data.json");
        sqLite.setJson(json);
        String actul = sqLite.getSeriesName("4584",4);
        String except = "";
        assertEquals(except,actul);
    }

    @Test
    public void getgetLeagues(){
        sqLite sqLite = new sqLite();
        org.json.JSONObject json = JsonModifier.JsonReader("data.json");
        sqLite.setJson(json);
        List<String> actul = sqLite.getLeagues();
        List<String> except = new ArrayList<String>();
        except.add("INVie");
        except.add("MSI Cono Sur League");
        except.add("Rising Heroes");
        except.add("Operation League Taiwan");
        except.add("Nerd Street Gamers");
        except.add("LVP");
        except.add("Absolute Masters");
        except.add("Swiss Esports League");
        except.add("Scrimmage");
        except.add("LPL");
        except.add("Hyperion x OEL Launch");
        except.add("ESPORT NOW Cup");
        except.add("ESL Italia Championship");
        except.add("ESL Turkey Championship");
        except.add("ESL Brasil Premier League");
        except.add("ESL Mistrzostwa Polski");
        except.add("ESL Swiss League");
        except.add("Pittsburgh Knights Monthly Gauntlet");
        except.add("PNXBET Invitationals");
        except.add("ESL Masters España");
        except.add("ESL Benelux Championship");
        except.add("ESL Championnat National");
        except.add("Aorus League");
        except.add("PLE.GG VALORANT Nations Circuit");
        except.add("Huya PangHu Cup");
        except.add("ESL Meisterschaft");
        except.add("ESL Premiership");
        except.add("EPIC League");
        except.add("ESL One");
        except.add("DreamHack Showdown");
        except.add("DreamHack Open");
        except.add("DreamHack Masters");
        except.add("ESL Pro League");
        except.add("Pinnacle Cup");
        except.add("Copa Elite Six");
        except.add("CBCS Elite League");
        except.add("Digital Warriors");
        except.add("Perfect World League");
        except.add("Positive Fire Games");
        except.add("OEDL Fall Invitational");
        except.add("Spring Sweet Spring");
        except.add("PG Nationals");
        except.add("Dota 2 Champions League");
        except.add("NEO.bet Cup");
        except.add("LCS Proving Grounds");
        except.add("REPUBLEAGUE");
        except.add("Gamers Club Liga Série A");
        except.add("LCK Challengers League");
        except.add("i-League");
        except.add("BLAST Nordic Masters");
        assertEquals(except,actul);
    }




}
