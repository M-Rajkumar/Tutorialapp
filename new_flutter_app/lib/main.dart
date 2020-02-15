import 'package:flutter/material.dart';
import 'package:new_flutter_app/auth.dart';
import 'package:new_flutter_app/routes.dart';

void main() => runApp(new LoginApp());

class LoginApp extends StatelessWidget {


  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'My Login App',
      debugShowCheckedModeBanner: false,
      theme: new ThemeData(
        primarySwatch: Colors.red,
      ),
      routes: routes,
    );
  }


}