import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:http/http.dart' as http;
import 'dart:convert' as convert;
import 'package:uuid/uuid.dart';
import 'dart:math';

import 'introduction.dart';


void main() async {
  runApp(MaterialApp(
    home: Home(),
    theme: ThemeData(
      fontFamily: 'Quicksand',
      textTheme: TextTheme(
        headline1: TextStyle(
            fontSize: 60, fontWeight: FontWeight.bold, color: Colors.white),
        bodyText1: TextStyle(
            fontSize: 25, fontWeight: FontWeight.w400, color: Colors.white),
        bodyText2: TextStyle(
            fontSize: 30, fontWeight: FontWeight.w600, color: Colors.white),
        button: TextStyle(
            fontSize: 20, fontWeight: FontWeight.w400, color: Colors.white),
      ),
    ),
  ));
}

//start the helper methods
const Color primary = Colors.grey;
const Color secondary = Colors.orange;
const Color yesButton = Colors.green;
const Color noButton = Colors.red;
const Color neutralerButton = secondary;


//Standard-Text
Text setStandardText(String text, double size) {
  try {
    return Text(
      text,
      textAlign: TextAlign.center,
      style: TextStyle(
          fontFamily: 'Quicksand',
          fontWeight: FontWeight.w700,
          color: Colors.white,
          fontSize: size
      ),
    );
  } catch (Exception) {
    return Text(
      "Server Error",
      textAlign: TextAlign.center,
      style: TextStyle(
          fontFamily: 'Quicksand',
          fontWeight: FontWeight.w700,
          color: Colors.white,
          fontSize: size
      ),
    );
  }
}

// Button-Text
Text setButtonText(String text) {
  return Text(
    text,
    textAlign: TextAlign.center,
    style: TextStyle(
        fontSize: 25.0,
        color: Colors.white,
        fontFamily: 'Quicksand',
        fontWeight: FontWeight.w700,
        height: 2
    ),
  );
}

// Appbar
AppBar setStandardAppbar(BuildContext context) {
  return AppBar(
    backgroundColor: secondary,
    title: setStandardText("Movie Oracle", 30),
    centerTitle: true,
    elevation: 7.5,
    actions: <Widget>[
      IconButton(
          icon: FaIcon(FontAwesomeIcons.home),
          color: Colors.white,
          iconSize: 30.0,
          alignment: Alignment.topRight,
          padding: const EdgeInsets.only(right: 40,top: 15),
          onPressed: () {
            reset();
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => Home()),
            );
          }),
    ],
  );
}


var lastQuestionId;
Map jsonQuestion;
int gameId = 2;
int pId = 0;
int g = 0;
int qid = 1;
int yesCounter = 0;
int noCounter = 0;
int gender = 2; // 0male, 1female, 2other

void reset() {
  g = 0;
  gender = 2;
  yesCounter = 0;
  noCounter = 0;
}

void reset1() {
  g = 0;
  yesCounter = 0;
  noCounter = 0;
}

//HTTP-Text
void getHttpText(int g, int pId, int gender) async {
    //final response = await http.get(Uri.parse('http://localhost:8010/questionX?g=$g&pId=$pId'));
  print('Frage Nummer $g mit Spielid $pId und $gender');
    final response = await http.get(Uri.parse('https://tpsose2021.englich.eu/sarah/questionX?g=$g&pId=$pId&gender=$gender'));
    final extractedData = await json.decode(response.body);
    jsonQuestion = extractedData;
    print(extractedData['id']);
    print(extractedData['text']);
}


void sendHttp(int qId, bool yn, int pId, int gender) async {
  //await http.post('http://localhost:8010/answerX?qId=$qId&yesOrNo=$yn&pId=$pId&gender=$gender');
  print('Antwort Frageid $qId mit $yn und Spielid $pId und $gender');
  await http.post('https://tpsose2021.englich.eu/sarah/answerX?qId=$qId&yesOrNo=$yn&pId=$pId&gender=$gender');
}

void sendMovie(String n) async {
  //await http.post('http://localhost:8010/answerX?qId=$qId&yesOrNo=$yn&pId=$pId&gender=$gender');
  print('Antwort $n');
  await http.post('https://tpsose2021.englich.eu/sarah/newmovie?n=$n');
}



class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Scaffold(
        backgroundColor: primary,
           body: Center(
              child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                children: [
                 SizedBox(height: 150),
                    Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                       SizedBox(height: 20),

                      // Logo is here
                      Image.asset(
                        'assets/logo_movieapp.png',
                        height: 150,
                        width: 100,
                      ),

                      SizedBox(height: 150),

                      FlatButton(
                        color: neutralerButton,
                        height: 50.0,
                        minWidth: 250.0,
                        padding: EdgeInsets.only(bottom: 20),
                        shape: RoundedRectangleBorder(
                              
                              borderRadius: BorderRadius.circular(35.0),
                              side: BorderSide(color: Colors.white, width: 2)),
                          onPressed: () {
                            Navigator.push(
                              context,
                              MaterialPageRoute(builder: (context) => IntroductionScreen()),
                            );
                          },
                          child: setButtonText(
                              "Start Quiz"
                          ),
                      ),
                       SizedBox(height: 25),
        ]),
      ])),
    ));
  }
}


class EndScreenContainer extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: EndScreen(),
      ),
    );
  }
}

class EndScreen extends StatefulWidget {
  EndScreenState createState() => EndScreenState();
}

class EndScreenState extends State {


  void updateMovieScreen() async {

    setState(() {

    });
  }

  @override
  void initState() {
    updateMovieScreen();
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: setStandardAppbar(context),
      backgroundColor: primary,
      body: Center(
        child: Container(
            margin: const EdgeInsets.only(right: 25, left: 25),
            child: Column(children: [
              SizedBox(height: 50),

              SizedBox(height: 125,
              child: setStandardText(
                  'We successfully guessed your movie!!!\n',
                  30
              ),
              ),
              /*Image.asset(
                'assets/smile.png',
                height: 200,
                width: 200,
              ),*/

            SizedBox(height: 50,
                child: setStandardText(
                    'Thanks for playing!',
                  30
                ),
            ),
              SizedBox(height: 25),

             setStandardText(
                 'Pressed Yes $yesCounter times',
                  20
             ),

              setStandardText(
                  'Pressed No $noCounter times',
                  20
              ),
              setStandardText(
                  'Finished at question $g',
                  20
              ),
              SizedBox(height: 40),

              FlatButton(
                  color: neutralerButton,
                  height: 65.0,
                  minWidth: 250.0,
                  padding: EdgeInsets.only(bottom: 20),
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(35.0),
                      side: BorderSide(color: Colors.white, width: 2)),
                  onPressed: () async {
                    await reset();
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => Home()),
                    );
                  },
                  child: setButtonText(
                    "Play again",
                  ),
              ),
              SizedBox(height: 20), // Placeholder
            ])),
      ),
    );
  }
}
