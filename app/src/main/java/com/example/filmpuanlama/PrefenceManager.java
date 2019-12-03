package com.example.filmpuanlama;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

    class PreferenceManager extends Activity {
        private static volatile PreferenceManager _instance = null;
        public static SharedPreferences _preferences;
        public static SharedPreferences.Editor _editor;
        private static Context ctx;

        public PreferenceManager(Context ctx) {
            if (_instance != null)
                throw new RuntimeException("Lütfen Instance() fonksiyonunu kullanınız");
            else {
                _preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(ctx);
                _editor = _preferences.edit();
            }
        }

        public PreferenceManager() {

        }

        public static PreferenceManager Instance(Context ctx) {
            if (_instance == null) {
                synchronized (PreferenceManager.class) {

                    _instance = new PreferenceManager(ctx);
                }
            }
            return _instance;
        }


        public void Girisvar() {
            _editor.putBoolean("girisvar",true);
            _editor.commit();
        }
        public void Girisyok() {
            _editor.putBoolean("girisvar",false);
            _editor.commit();
        }
        public boolean GirisKontrol() {
            return _preferences.getBoolean("girisvar",false);
        }

    }