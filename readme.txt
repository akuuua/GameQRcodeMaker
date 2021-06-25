- I've taken the simple extension.

-For the sqlite code, the create table , intsert data, get data are learnt.(The sql code inside is my work, and I made a lot of changes to the code for my use.)
-JsonReader and writer. I wrote those code when I took SOFT 2201 when doing the final assignment, when we need to get the data from the config file(which is a json file).

-----------------------REDGREEN TDD--------------------------------------------
--serise class
Red: If no JSON transmit in, all variable has null as initial status.
Green: Let all  string attributes of serise has null as initial status.
Refector: Initial those string variable under the class.

--sqLite class(mainly here, data manipulating)
---getSeriesReport():
Red: The string of report should be in the correct format I suggest.
Green: Set the line break after each map.
Refector:  Set the line break after each map.
Red: Unnecessary space exist.
Green: Delete the space.
Refector: Delect the spcae.
Red: Output is in a wrong sequence.
Green: Change the position.
Refector: Follow the excepted output format, change the output sequence.

---getSeriesReportForUI()
Red: The html version of selected serise should be in the correct format that I decided. It should has word wrap.
Green: Set the <html> and <br> in the string to get the purpose word wrap. 
Refector:  Set the <html> and <br> in the string. 
Red: Unnecessary space exist.
Green: Delete the space.
Refector: Delect the spcae.
Red: Unnecessary bracket exist.
Green: Delete the  bracket.
Refector: Delect the  bracket.
Red: Output is in a wrong sequence.
Green: Change the position.
Refector: Follow the excepted output format, change the output sequence.

---getgetSeriesName()
Red: This function should return "Season 5 2021" when we search the second series of league 4584. But it returns null.
Green: Check if all of the serises has been already stored in the sql database.
Refector: The database only store the first serise of each league. Use for loop to store all series instead.

---getgetSeriesNameIndex()
Red: This function should return " " when we search the fifth series of league 4584.
Green: If the serise doesn't exist, return empty string.
Refector: If there no mathing serise, do nothing to the empty string and return it directlly.

---getgetLeagues()
Red: A list of string about all leagues in the database should be return.But there are some messy code in the output.
Green: Ust UTF=8.
Refector: Transform the string to the UTF=8 format.



------------------------How to use?------------------------------
Notice! :
If the pandaScore offline, you can still use local database.
If the imgur is offline, you won't able to upload the QRCode to the server, but you still got QR code in the local.

Run "gradle run --args="online online""
  
The first online control the input online status which is for the pandaScore, and the second control the output which is Imgur.

There is a window for user to enter their own Authorisation Keys. Of course, for ez use, I provide a default key for user to use.(Also ez test)

Then the user can choose the index. Once the index is choosen, it can't be changed later. So user only get one chance to select the index when they open the app.

Then main page ui is very easy, two combobox. Once you select the league, the app will running.

The default setting is to use local database. If user want to get a new data from input website server, choose NO.

And then select the league, if the index < the number of serises that the league has, the sriese name and information will display in the UI, also the report will contain the message that the matching series name and the information about the serises of the league. Otherwise, the UI display nothing and the report will only contain the information about the serises of the league.



###############PLEASE USE THIS IN GOOD INTERNET CONDITION################
Must use VPN if you are in China.
For example in China, the imgur is blocked, so it's hard to upload the image.
Once the image is upload successfully, the image link in the imgur server will be printed by the system.(Not in the window.)
If upload failed, the system will tell you that server is down.
It's a QRcode.

