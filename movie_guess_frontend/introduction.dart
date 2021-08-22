import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:startup_namer/register.dart';
import 'package:startup_namer/main.dart';

import 'main.dart';

class IntroductionScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
          backgroundColor: Colors.grey,
          body: Center(


              child: Container(
            margin: const EdgeInsets.only(left: 25, right: 25),

            child: Column(children: [
              SizedBox(height: 50),
              setStandardText(
                'Lets Play!',
                  50.0
              ),
              SizedBox(height: 25),
              setStandardText(
                "Think of a movie and I'll try to guess it!",
                25.0,
              ),
              SizedBox(height: 25),

              setStandardText(
                  'I will ask you a maximum of 15 question '
                  'which you will answer with Yes or No. \n \n'
                  //'If you don’t know the answer, just tap on „I don’t know“ '
                  //'to skip the question.\n'
                  'I will use the database of TMDB \n'
                      'which contains common movies',
                      20.0
              ),

              SizedBox(height: 25),

              // the buttons come here
              FlatButton(
                  color: neutralerButton,
                  height: 65.0,
                  minWidth: 250.0,
                  padding: EdgeInsets.only(bottom: 20),
                  shape: RoundedRectangleBorder(
                      
                      borderRadius: BorderRadius.circular(35.0),
                      side: BorderSide(color: Colors.white, width: 2)),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => RegisterScreen()),
                    );
                  },

                  child: setButtonText(
                    "Next step",
                    ),
              ),
              SizedBox(height: 20),

              FlatButton(
                  color: neutralerButton,
                  height: 65.0,
                  minWidth: 250.0,
                  padding: EdgeInsets.only(bottom: 20),
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(35.0),
                      side: BorderSide(color: Colors.white, width: 2)),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => Home()),
                    );
                  },
                  child: setButtonText(
                    "Back to Main Menu",
                  ),
              ),
              SizedBox(height: 20), // Placeholder
            ])),

      ),
    ));
  }
}
