package com.newbie.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Session {
    public SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int private_mode = 0;

    public static final String PrefName = "LoginApp";
    public static final String isLogin = "isLoggin";
    public static final String key_name = "username";
    public static final String key_pass = "password";

    public Session(Context _context){
        this.context = _context;
        pref = context.getSharedPreferences(PrefName, private_mode);
        editor = pref.edit();
    }

    public void createSession(String name, String password) {
        editor.putBoolean(isLogin, true);
        editor.putString(key_name, name);
        editor.putString(key_pass, password);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(key_name, pref.getString(key_name, null));
        user.put(key_pass, pref.getString(key_pass, null));
        return user;
    }

    public void cekLogin(){
        if(!this.isLogin()){
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public boolean isLogin(){
        return pref.getBoolean(isLogin, false);
    }
}
