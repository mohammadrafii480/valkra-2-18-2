package eu.siacs.conversations.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "PREF_SELISO_CHAT";
    // All Shared Preferences Keys
    public static final String KEY_JABBERID = "JABBERID";
    public static final String KEY_SERVERNAME = "SERVERNAME";
    public static final String KEY_SUBSCRIBE_USERROLE = "SUBSCRIBE_USERROLE";
    public static final String KEY_SUBSCRIBE_STATUS = "SUBSCRIBE_STATUS";
    public static final String KEY_SUBSCRIBE_TIMELEFT = "KEY_SUBSCRIBE_TIMELEFT";
    public static final String KEY_LOGIN = "ISLOGIN";
    public static final String KEY_LOGOUT = "LOGOUT";
    public static final String KEY_SECURE_PIN = "PIN";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void saveJabberID(String data) {
        editor.putString(KEY_JABBERID,data);
        editor.commit();
    }

    public void saveServerName(String data) {
        editor.putString(KEY_SERVERNAME,data);
        editor.commit();
    }

    public void savePin(String data) {
        editor.putString(KEY_SECURE_PIN,data);
        editor.commit();
    }

    public void saveSubscribeRole(String data) {
        editor.putString(KEY_SUBSCRIBE_USERROLE,data);
        editor.commit();
    }

    public void saveSubscribeStatus(String data) {
        editor.putString(KEY_SUBSCRIBE_STATUS,data);
        editor.commit();
    }

    public void saveSubscribeTimeLeft(int data) {
        editor.putInt(KEY_SUBSCRIBE_TIMELEFT,data);
        editor.commit();
    }

    public void saveLogoutState(boolean data) {
        editor.putBoolean(KEY_LOGOUT,data);
        editor.commit();
    }
    public void setLogin(boolean data) {
        editor.putBoolean(KEY_LOGIN,data);
        editor.commit();
    }



    public void clearData(){
        editor.clear();
        editor.commit();
    }

    // GET PREFERENCES

    public String getJabberID() {
        return pref.getString(KEY_JABBERID,null);
    }
    public String getServerName() {
        return pref.getString(KEY_SERVERNAME,null);
    }
    public String getSubscribeRole() {
        return pref.getString(KEY_SUBSCRIBE_USERROLE,null);
    }
    public String getSubscribeStatus() {
        return pref.getString(KEY_SUBSCRIBE_STATUS,null);
    }
    public int getSubscribeTimeLeft() {
        return pref.getInt(KEY_SUBSCRIBE_TIMELEFT,0);
    }
    public boolean getLogoutState() {
        return pref.getBoolean(KEY_LOGOUT,false);
    }
    public boolean isLogin() {
        return pref.getBoolean(KEY_LOGIN,false);
    }
    public String getSecurePin() {
        return pref.getString(KEY_SECURE_PIN,"");
    }

}
