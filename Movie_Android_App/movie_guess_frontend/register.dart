import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:startup_namer/backendlogic.dart';
import 'package:startup_namer/main.dart';
import 'main.dart';
import 'package:flutter/scheduler.dart' show timeDilation;

class RegisterScreen extends StatefulWidget {
  @override
  _RegisterScreenState createState() => _RegisterScreenState();
}



enum Sex { male, female, other, no }
class _RegisterScreenState extends State<RegisterScreen> {


  Sex _value = Sex.no;
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.grey,
        appBar: setStandardAppbar(context),
        body: Center(
          child: Column (
            children: [
          new Container(
            margin: const EdgeInsets.only(left: 25, right: 25),
            child: Column(
                children: [
                  SizedBox(height: 25),
                  setStandardText(
                    "Please fill in these details so that we can improve our game. \n Thank you!",
                    25.0,
                  ),
                  SizedBox(height: 25),
                   Text('Your gender:                       ',
                    textAlign: TextAlign.left,
                     style: TextStyle(
                        fontFamily: 'Quicksand',
                        fontWeight: FontWeight.w700,
                        color: Colors.white,
                        fontSize: 25
                      ),
                  ),
                  SizedBox(height: 10),


                  // sex buttons
                  ListTile(
                    title: const Text('Male',
                      style: TextStyle(
                        fontFamily: 'Quicksand',
                        fontWeight: FontWeight.w700,
                        color: Colors.white,
                        fontSize: 20
                      ),
                    ),
                    leading: Radio(
                      value: Sex.male,
                      groupValue: _value,
                      onChanged: (Sex value) {
                        setState(() {
                          _value = value;
                          gender = 0;
                        });
                      },
                    ),
                  ),
                  ListTile(
                    title: const Text('Female',
                      style: TextStyle(
                          fontFamily: 'Quicksand',
                          fontWeight: FontWeight.w700,
                          color: Colors.white,
                          fontSize: 20
                      ),
                    ),
                    leading: Radio(
                      value: Sex.female,
                      groupValue: _value,
                      onChanged: (Sex value) {
                        setState(() {
                          _value = value;
                          gender = 1;
                        });
                      },
                    ),
                  ),
                  ListTile(
                    title: const Text('Other',
                      style: TextStyle(
                          fontFamily: 'Quicksand',
                          fontWeight: FontWeight.w700,
                          color: Colors.white,
                          fontSize: 20
                      ),
                    ),
                    leading: Radio(
                      value: Sex.other,
                      groupValue: _value,
                      onChanged: (Sex value) {
                        setState(() {
                          _value = value;
                          gender = 2;
                        });
                      },
                    ),
                  ),
                  ListTile(
                    title: const Text('no answer',
                      style: TextStyle(
                          fontFamily: 'Quicksand',
                          fontWeight: FontWeight.w700,
                          color: Colors.white,
                          fontSize: 20
                      ),
                    ),
                    leading: Radio(
                      value: Sex.no,
                      groupValue: _value,
                      onChanged: (Sex value) {
                        setState(() {
                          _value = value;
                          gender = 3;
                        });
                      },
                    ),
                  ),

                  SizedBox(height: 25),

            // Continue Button
            FlatButton(
              color: neutralerButton,
              height: 65.0,
              minWidth: 250.0,
              padding: EdgeInsets.only(bottom: 20),
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(35.0),
                  side: BorderSide(color: Colors.white, width: 2)),
              onPressed: () async {
                await reset1();
                int makePid() {
                  var rng = new Random();
                  return rng.nextInt(10000000);
                }
                pId = makePid();
                await getHttpText(g, pId, gender);
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => BackendlogicScreen()),
                );
              },
              child: setButtonText(
                "Let's start!",
              ),
            ),
            SizedBox(height: 20),
          ]),
        ),
      ]),
    ),
    ),
    );
  }
}
