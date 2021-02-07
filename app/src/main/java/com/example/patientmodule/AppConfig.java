package com.example.patientmodule;

public class AppConfig {
    //static String  host = "http://172.16.204.28/";
    static String pathLogin = "/api/login.php";
    static String pathRegister = "/api/register.php";
    static String  host = "http://10.0.2.2";
    // Server user login url
    //public static String URL_LOGIN = "http://10.0.2.2/api/login.php";
    public static String URL_LOGIN = host + pathLogin;
    // Server user register url
    //public static String URL_REGISTER = "http://10.0.2.2/api/register.php";
    public  static String URL_REGISTER = host + pathRegister;
}