import 'package:flutter/material.dart';
import 'specialEndScreen.dart';
import 'main.dart';

class BackendlogicScreen extends StatefulWidget {
  @override
  _BackendlogicScreenState createState() => _BackendlogicScreenState();
}


class _BackendlogicScreenState extends State<BackendlogicScreen> {

  String _frage = jsonQuestion['text'];
  int _id = jsonQuestion['id'];

  void _frageneu() {
    setState(() {
      _frage = jsonQuestion['text'];
      _id = jsonQuestion['id'];
    });
  }


    Widget build(BuildContext context) {
      return Scaffold(
        appBar: setStandardAppbar(context),
        backgroundColor: primary,
        body: Center(
          child: Container(
              margin: const EdgeInsets.only(right: 25, left: 25),
              child: Column(children: [
                SizedBox(height: 50),
                setStandardText(_frage, 23),
                SizedBox(height: 150),
                // YES BUTTON
                FlatButton(
                  color: yesButton,
                  height: 65.0,
                  minWidth: 250.0,
                  padding: EdgeInsets.only(bottom: 20),
                  shape: RoundedRectangleBorder(
                    
                      borderRadius: BorderRadius.circular(35.0),
                      side: BorderSide(color: Colors.white, width: 2)),
                  onPressed: () async {
                    g++;
                    yesCounter++;
                    await sendHttp(_id, true, pId, gender);
                    // When a movie is asked for
                    if (_id == 999999) {
                      // PostMapping guessquestionwithanswer aufrufen
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => EndScreen()),
                      );
                    } else {
                      await getHttpText(g, pId, gender);
                      await _frageneu();
                    }
                  },
                  child: setButtonText(
                    " Yes ",
                  ),
                ),
                SizedBox(height: 20),

                // "No" button
                FlatButton(
                  color: noButton,
                  height: 65.0,
                  minWidth: 250.0,
                  padding: EdgeInsets.only(bottom: 20),
                  shape: RoundedRectangleBorder(
                    // Square = BorderRadius.circular(0.0)
                      borderRadius: BorderRadius.circular(35.0),
                      side: BorderSide(color: Colors.white, width: 2)),
                  onPressed: () async {
                    g++;
                    noCounter++;
                    await sendHttp(_id, false, pId, gender);
                    if (_id == 999999) {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => SpecialEndScreen()),
                      );
                    } else {
                      await getHttpText(g, pId, gender);
                      await _frageneu();
                    }
                  },
                  child: setButtonText(
                      "No"
                  ),
                ),
                SizedBox(height: 20),
              ])),
        ),
      );
    }
  }
