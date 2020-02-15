import 'package:flutter/material.dart';
import 'package:flutter_api_integration/authentication.dart';
import 'package:flutter_api_integration/color_util.dart';

class LoginSignupPage extends StatefulWidget {
  LoginSignupPage({this.auth, this.loginCallback});

  final BaseAuth auth;
  final VoidCallback loginCallback;

  @override
  State<StatefulWidget> createState() => new _LoginSignupPageState();
}

class _LoginSignupPageState extends State<LoginSignupPage> {
  final _formKey = new GlobalKey<FormState>();
  static final _myController = TextEditingController();

  String _email;
  String _password;
  String _errorMessage;
  bool _isLoginForm;
  bool _isLoading;
  static bool _isPassword;

  bool validateAndSave() {
    final form = _formKey.currentState;
    if (form.validate()) {
      form.save();
      return true;
    }
    return false;
  }

  void validateAndSubmit() async {
    setState(() {
      _errorMessage = "";
      _isLoading = true;
    });

    if (validateAndSave()) {
      String userId = "";
      try {
        if (_isLoginForm) {
          userId = await widget.auth.signIn(_email, _password);
          print('Signed in: $userId');
        } else {
          userId = await widget.auth.signUp(_email, _password);
          //widget.auth.sendEmailVerification();
          //_showVerifyEmailSentDialog();
          print('Signed up user: $userId');
        }
        setState(() {
          _isLoading = false;
        });

        if (userId.length > 0 && userId != null && _isLoginForm) {
          widget.loginCallback();
        }
      } catch (e) {
        print('Error: $e');
        setState(() {
          _isLoading = false;
          _errorMessage = e.message;
          _formKey.currentState.reset();
        });
      }
    }
  }

  @override
  void initState() {
    _errorMessage = "";
    _isLoading = false;
    _isLoginForm = true;
    _isPassword = false;
    super.initState();
  }

  void resetForm() {
    _formKey.currentState.reset();
    _errorMessage = "";
  }

  void toggleFormMode() {
    resetForm();
    setState(() {
      _isLoginForm = !_isLoginForm;
    });
  }

  static void isClearText() {
    _myController.clear();
  }

  void _passFunction() {
    setState(() {
      _isPassword = !_isPassword;
    });
  }

  @override
  Widget build(BuildContext context) {
    final emailField = Container(
        padding: const EdgeInsets.fromLTRB(0.0, 100.0, 0.0, 0.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Container(
              width: 36,
              height: 36,
              decoration: BoxDecoration(
                color: const Color(0xff666666),
                border: Border.all(
                  color: const Color(0xff666666),
                  width: 0,
                  style: BorderStyle.none,
                ),
                borderRadius: BorderRadius.circular(4.0),
              ),
              child: Padding(
                padding: EdgeInsets.all(7.0),
                child: Container(
                  decoration: BoxDecoration(
                    image: const DecorationImage(
                      image: AssetImage('assets/images/user.png'),
                      fit: BoxFit.contain,
                    ),
                  ),
                ),
              ),
            ),
            SizedBox(width: 8.0),
            Flexible(
                child: SizedBox(
              height: 38,
              child: TextFormField(
                controller: _myController,
                cursorColor: Color(0xFF399895),
                keyboardType: TextInputType.emailAddress,

                style: new TextStyle(
                  fontFamily: "Poppins",
                ),
//            onChanged: (value) {
//              setState(() {
//                isClear = value.length > 0;
//              });
//            },
                decoration: InputDecoration(
                    filled: true,
                    fillColor: Color(0xFFe4e7f0),
                    contentPadding: EdgeInsets.fromLTRB(10.0, 0.0, 0.0, 0.0),
                    hintText: "Email",
                    suffixIcon: Visibility(
//              visible: isClear,
                      child: IconButton(
                        onPressed: isClearText,
                        icon: Icon(Icons.clear),
                        iconSize: 18,
                        color: Color(0xFF399895),
                        splashColor: Colors.transparent,
                        highlightColor: Colors.transparent,
                      ),
                    ),
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(4.0),
                      borderSide: BorderSide(
                        width: 0,
                        style: BorderStyle.none,
                      ),
                    )),
              ),
            ))
          ],
        ));

    final passwordField = Container(
        padding: const EdgeInsets.fromLTRB(0.0, 10.0, 0.0, 0.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Container(
              width: 36,
              height: 36,
              decoration: BoxDecoration(
                color: const Color(0xff666666),
                border: Border.all(
                  color: const Color(0xff666666),
                  width: 0,
                  style: BorderStyle.none,
                ),
                borderRadius: BorderRadius.circular(4.0),
              ),
              child: Padding(
                padding: EdgeInsets.all(7.0),
                child: Container(
                  decoration: BoxDecoration(
                    image: const DecorationImage(
                      image: AssetImage('assets/images/password.png'),
                      fit: BoxFit.contain,
                    ),
                  ),
                ),
              ),
            ),
            SizedBox(width: 8.0),
            Flexible(
                child: SizedBox(
              height: 38,
              child: TextFormField(
                controller: TextEditingController(text: ''),
                cursorColor: Color(0xFF399895),
                keyboardType: TextInputType.emailAddress,
                obscureText: _isPassword,
                style: new TextStyle(
                  fontFamily: "Poppins",
                ),
//            onChanged: (value) {
//              setState(() {
//                isClear = value.length > 0;
//              });
//            },
                decoration: InputDecoration(
                    filled: true,
                    fillColor: Color(0xFFe4e7f0),
                    contentPadding: EdgeInsets.fromLTRB(10.0, 0.0, 0.0, 0.0),
                    hintText: "Password",
                    suffixIcon: Visibility(
//              visible: isClear,
                      child: IconButton(
                        onPressed: _passFunction,
                        icon: Icon(Icons.visibility_off),
                        iconSize: 18,
                        color: Color(0xFF399895),
                        splashColor: Colors.transparent,
                        highlightColor: Colors.transparent,
                      ),
                    ),
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(4.0),
                      borderSide: BorderSide(
                        width: 0,
                        style: BorderStyle.none,
                      ),
                    )),
              ),
            ))
          ],
        ));

    return new Scaffold(
//        appBar: new AppBar(
//          title: new Text('RK Flutter'),
//        ),
        body: Stack(
      children: <Widget>[
        passwordField,
        showForm(),
        showCircularProgress(),
      ],
    ));
  }

  Widget showCircularProgress() {
    if (_isLoading) {
      return Center(child: CircularProgressIndicator());
    }
    return Container(
      height: 0.0,
      width: 0.0,
    );
  }

  Widget showForm() {
    return new Container(
        padding: EdgeInsets.all(16.0),
        child: new Form(
          key: _formKey,
          child: new ListView(
            shrinkWrap: true,
            children: <Widget>[
              showLogo(),
//              showEmailInput(),

//              emailField,
//              passwordField,
//              showPasswordInput(),
              showPrimaryButton(),
              showSecondaryButton(),
              showErrorMessage(),
            ],
          ),
        ));
  }

  Widget showLogo() {
    return new Hero(
      tag: 'hero',
      child: Padding(
        padding: EdgeInsets.fromLTRB(0.0, 80.0, 0.0, 0.0),
        child: CircleAvatar(
          backgroundColor: Colors.transparent,
          radius: 78.0,
          child: Image.asset('assets/images/app_icon.png'),
        ),
      ),
    );
  }

  Widget showEmailInput() {
    return Padding(
      padding: const EdgeInsets.fromLTRB(0.0, 100.0, 0.0, 0.0),
      child: new TextFormField(
        maxLines: 1,
        keyboardType: TextInputType.emailAddress,
        autofocus: false,
        decoration: new InputDecoration(
            hintText: 'Email',
            icon: new Icon(
              Icons.mail,
              color: Colors.grey,
            )),
        validator: (value) => value.isEmpty ? 'Email can\'t be empty' : null,
        onSaved: (value) => _email = value.trim(),
      ),
    );
  }

  Widget showPasswordInput() {
    return Padding(
      padding: const EdgeInsets.fromLTRB(0.0, 15.0, 0.0, 0.0),
      child: new TextFormField(
        maxLines: 1,
        obscureText: true,
        autofocus: false,
        decoration: new InputDecoration(
            hintText: 'Password',
            icon: new Icon(
              Icons.lock,
              color: Colors.grey,
            )),
        validator: (value) => value.isEmpty ? 'Password can\'t be empty' : null,
        onSaved: (value) => _password = value.trim(),
      ),
    );
  }

  Widget showPrimaryButton() {
    return new Padding(
        padding: EdgeInsets.fromLTRB(85.0, 45.0, 85.0, 0.0),
        child: SizedBox(
          height: 40.0,
          child: new RaisedButton(
            elevation: 2.0,

//            shape: new RoundedRectangleBorder(
//                borderRadius: new BorderRadius.circular(30.0)),
            color: AppColors.PRIMARY_COLOR,
            child: new Text(_isLoginForm ? 'Login' : 'Create account',
                style: new TextStyle(fontSize: 20.0, color: Colors.white)),
            onPressed: validateAndSubmit,
          ),
        ));
  }

  Widget showSecondaryButton() {
    return new FlatButton(
        child: new Text(
            _isLoginForm ? 'Create an account' : 'Have an account? Sign in',
            style: new TextStyle(fontSize: 18.0, fontWeight: FontWeight.w300)),
        onPressed: toggleFormMode);
  }

  Widget showErrorMessage() {
    if (_errorMessage.length > 0 && _errorMessage != null) {
      return new Text(
        _errorMessage,
        style: TextStyle(
            fontSize: 13.0,
            color: Colors.red,
            height: 1.0,
            fontWeight: FontWeight.w300),
      );
    } else {
      return new Container(
        height: 0.0,
      );
    }
  }
}
