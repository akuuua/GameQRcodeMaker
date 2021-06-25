package GameQRcodeMaker.UI;

import GameQRcodeMaker.QRCodeGenerater;
import GameQRcodeMaker.Server.dummyImgur;
import GameQRcodeMaker.Server.dummyPandaScore;
import GameQRcodeMaker.Server.imgur;
import GameQRcodeMaker.Server.pandaScore;
import GameQRcodeMaker.database.sqLite;
import GameQRcodeMaker.database.JsonModifier;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ui {
    private static int index = 0;
    private static String imgurID = "faf4e684b2b8e8e";
    private static String pandaScoreID ="4qxlunnvkauHsz8JoT69sM4AdS7mlWdrnXHnMGot9WP66hERbcs";

    public static void HomeUI(boolean pandaScoreOnline, boolean imgurOnline) throws Exception{
        String getLeague = "";
        if(pandaScoreOnline == true){
            //Initialize Json Modifier
            getLeague = pandaScore.doGet("https://api.pandascore.co/leagues",pandaScoreID);
            JsonModifier.JsonWriter("data.json",getLeague);
        }else{
            getLeague = dummyPandaScore.doGet();
        }

        org.json.JSONObject json = JsonModifier.JsonReader("data.json");


        QRCodeGenerater generater = new QRCodeGenerater();


        //Initialize sqLite
        sqLite sqLite = new sqLite();
        JSONArray array = json.getJSONArray("League List");
        JComboBox cmb=new JComboBox();
        cmb.addItem("--Please select the league--");
        for(int i=0; i < array.length();i++){
            cmb.addItem(sqLite.getLeagues().get(i));
        }

        JComboBox onlineMode=new JComboBox();
        onlineMode.addItem("--Default is Yes--");
        onlineMode.addItem("Yes");
        onlineMode.addItem("No");

        //UI part
        JFrame frame=new JFrame("PandaScore");
        JPanel panel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel leagueLabel=new JLabel("Select the League: ");
        JLabel local=new JLabel("Use local database, or grab a new one from server: ");

        JLabel label=new JLabel(" ");
        JLabel label1=new JLabel(" ");
        JLabel label2=new JLabel(" ");
        JLabel outputLabel = new JLabel("");
        label.setFont(new Font(null,Font.BOLD,16));



        cmb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(onlineMode.getSelectedItem().toString() == "No" && pandaScoreOnline == true ){
                    //sqLite update data.
                    sqLite.setJson(json);
                    sqLite.createLeagueTable();
                    sqLite.insertLeague();
                    sqLite.createSeriesTable();
                    sqLite.insertSeries();
                    System.out.println("Update local database successfully.");
                }else{
                    System.out.print("Using the local database.");
                }

                String series = null;
                String leagueName = cmb.getSelectedItem().toString();
                String leagueID = sqLite.getLeagueID(leagueName);
                String report = sqLite.getSeriesForUI(leagueID,ui.index);
                String matchingSeries = sqLite.getSeriesName(leagueID,ui.index);
                int numberOfSeries = sqLite.getSeriesNumber(leagueID);
                if(numberOfSeries > 1){
                    label.setText("This league has the following series: ");
                    label2.setText(matchingSeries);
                }

                label.setFont(new Font(null,Font.BOLD,20));
                label2.setFont(new Font(null,Font.BOLD,20));
                label1.setText(report);

                try {
                    if(numberOfSeries <= 1){
                        generater.makeQRCode(sqLite.getSeriesReport(leagueID));
                    }else{
                        generater.makeQRCode("Matching series was: " + matchingSeries + sqLite.getSeriesReport(leagueID));
                    }

                    String outputQR = "";
                    if(imgurOnline == true){
                        outputQR = imgur.doPost("https://api.imgur.com/3/image",imgurID);
                    }else{
                        outputQR = dummyImgur.doPost();
                    }
                    JSONParser parser = new JSONParser();
                    try{
                        JSONObject js = (JSONObject) parser.parse(outputQR);
                        JSONObject url = (JSONObject) js.get("data");
                        String strURL = url.get("link").toString();
                        outputLabel.setText("The url for QR code is: "+strURL);
                        outputLabel.setFont(new Font(null,Font.BOLD,20));
                        System.out.println("Upload successful!");
                        System.out.println("Your QR code Link is: "+strURL);

                    }catch (Exception exception){
                        System.out.println("Server is down.");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }

        });
        panel.add(leagueLabel);
        panel.add(cmb);
        panel.add(local);
        panel.add(onlineMode);
        panel.add(label);
        panel.add(label2);
        panel.add(label1);
        panel.add(outputLabel);
        frame.add(panel);
        frame.setBounds(300,200,600,500);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.indexSelectWindow();
        ui.inputKeyWindow();
    }

    private static void indexSelectWindow(){
        JComboBox cmb = ui.indexCmb();
        JFrame frame=new JFrame("Select the index");
        JLabel label = new JLabel("Before using, select the index first: ");
        label.setFont(new Font(null,Font.BOLD,20));
        JPanel panel=new JPanel(new FlowLayout(FlowLayout.LEFT));

        cmb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(cmb.getSelectedItem().toString() == "0"){
                    ui.index = 0;
                }else if(cmb.getSelectedItem().toString() == "1"){
                    ui.index = 1;
                }else if(cmb.getSelectedItem().toString() == "2"){
                    ui.index = 2;
                }else if(cmb.getSelectedItem().toString() == "3"){
                    ui.index = 3;
                }else if(cmb.getSelectedItem().toString() == "4"){
                    ui.index = 4;
                }

                frame.dispose();
            }

        });

        panel.add(label);
        panel.add(cmb);
        frame.add(panel);
        frame.setBounds(350,250,400,120);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private static JComboBox indexCmb(){

        JComboBox indexCmb = new JComboBox();
        indexCmb.addItem("--Default index is 0--");
        indexCmb.addItem("0");
        indexCmb.addItem("1");
        indexCmb.addItem("2");
        indexCmb.addItem("3");
        indexCmb.addItem("4");
        return indexCmb;

    }

    private static void inputKeyWindow() {
        JFrame frame = new JFrame("Input your Authorisation keys");
        JLabel label = new JLabel("PandaScore Authorisation Key: ");
        JLabel label1 = new JLabel("Imugr Authorisation Key: ");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextArea textArea = new JTextArea(pandaScoreID, 1, 60);
        JTextArea textArea1 = new JTextArea(imgurID, 1, 30);

        JButton button=new JButton("Enter");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ui.pandaScoreID = textArea.getText();
                ui.imgurID = textArea1.getText();
                frame.dispose();

            }

        });
        panel.add(label);
        panel.add(textArea);
        panel.add(label1);
        panel.add(textArea1);
        panel.add(button);
        frame.add(panel);
        frame.setBounds(400, 300, 400, 150);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
