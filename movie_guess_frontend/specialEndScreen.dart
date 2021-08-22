import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'main.dart';


class SpecialEndScreenContainer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SpecialEndScreen(),
      ),
    );
  }
}

// if movie was not found
class SpecialEndScreen extends StatefulWidget {
  SpecialEndScreenState createState() => SpecialEndScreenState();
}


TextEditingController movieCon = new TextEditingController();

class SpecialEndScreenState extends State {
  String eingabe;
  TextEditingController inputController = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: setStandardAppbar(context),
      backgroundColor: primary,
      body: Center(
        child: Container(
          margin: const EdgeInsets.only(right: 25, left: 25),
          child: Column(children: [
            SizedBox(height: 20),
            setStandardText(
                'Which movie did you choose? ',
                25
            ),
            SizedBox(height: 25),
            TextField(
              controller: movieCon,
              decoration: InputDecoration(
                  hintText: 'enter your movie',
                  border: OutlineInputBorder(),
              ),
            ),

            SizedBox(height: 25),

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
                await sendMovie(movieCon.text);

                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Home()),
                );
              },
              child: setButtonText(
                "Play again",
              ),
            ),
          ]),
        ),
      ),
    );
  }
}

/*
// if movie was found
class SpecialEndScreenFind extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: setStandardAppbar(context),
      backgroundColor: primary,
      body: Center(
        child: Container(
          margin: const EdgeInsets.only(right: 25, left: 25),
          child: Column(children: [
            SizedBox(height: 40),
            setStandardText(
                'Hurray we could find your movie!!!!!!',
                35
            ),
            SizedBox(height: 25),
            FlatButton(
              color: neutralerButton,
              height: 65.0,
              minWidth: 250.0,
              padding: EdgeInsets.only(bottom: 20),
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(35.0),
                  side: BorderSide(color: Colors.white, width: 2)),
              onPressed: () async {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => Home()),
                );
              },
              child: setButtonText(
                "Play again",
              ),
            ),
          ]),
        ),
      ),
    );
  }
}*/

