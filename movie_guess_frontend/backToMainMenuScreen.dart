import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:startup_namer/introduction.dart';
import 'package:startup_namer/main.dart';
import 'main.dart';

class BackToMainMenuScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
          appBar: setStandardAppbar(context),
          backgroundColor: Colors.grey,
          body: Center(
            child: Container(
                margin: const EdgeInsets.only(left: 25, right: 25),
                child: Column(children: [
                  SizedBox(height: 100),

                  setStandardText(
                      'Would you like to play again? ',
                      20
                  ),

                  SizedBox(height: 50),
                  // Buttons come here
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
                          MaterialPageRoute(builder: (context) => IntroductionScreen()),
                        );
                      },
                      child: setButtonText(
                        "Play again",
                        ),
                  ),
                  SizedBox(height: 20),
                ])),
          ),
        ));
  }
}
