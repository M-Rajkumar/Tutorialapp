import 'dart:async';

import 'package:new_flutter_app/models/user.dart';
import 'package:new_flutter_app/utils/network_util.dart';

class RestDatasource {
  NetworkUtil _netUtil = new NetworkUtil();
  static final BASE_URL = "https://avexisstaging.radiusdirect.net/api/";
  static final LOGIN_URL = BASE_URL + "v2/userLogin";
  static final _API_KEY = "somerandomkey";

  Future<User> login(String username, String password) {
    return _netUtil.post(LOGIN_URL, body: {
      "token": _API_KEY,
      "email": username,
      "password": password
    }).then((dynamic res) {
      print(res.toString());
      if (res["error"]) throw new Exception(res["error_msg"]);
      return new User.map(res["user"]);
    });
  }
}
