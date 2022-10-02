package com.lefthumor.jside.ui.settings.editor;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

public class EditorSettings {


    boolean wordWrap;

    boolean showLineNumber = true;

    boolean pinLineNumber = true;

    public boolean isWordWrap() {
        return wordWrap;
    }

    public void setWordWrap(boolean wordWrap) {
        this.wordWrap = wordWrap;
    }

    public boolean isShowLineNumber() {
        return showLineNumber;
    }

    public void setShowLineNumber(boolean showLineNumber) {
        this.showLineNumber = showLineNumber;
    }

    public boolean isPinLineNumber() {
        return pinLineNumber;
    }

    public void setPinLineNumber(boolean pinLineNumber) {
        this.pinLineNumber = pinLineNumber;
    }

    public boolean isUseICU() {
        return useICU;
    }

    public void setUseICU(boolean useICU) {
        this.useICU = useICU;
    }

    public boolean isEnableMagnifier() {
        return enableMagnifier;
    }

    public void setEnableMagnifier(boolean enableMagnifier) {
        this.enableMagnifier = enableMagnifier;
    }

    boolean useICU;

    boolean enableMagnifier;


    SharedPreferences sharedPreferences;

    public EditorSettings(SharedPreferences preferences) {
        sharedPreferences = preferences;
        read();
    }

    public void read() {
        wordWrap = sharedPreferences.getBoolean("wordWrap", false);
        useICU = (sharedPreferences.getBoolean("icu", false));
        showLineNumber = sharedPreferences.getBoolean("sln", true);
        pinLineNumber = sharedPreferences.getBoolean("pinln", true);
        enableMagnifier = sharedPreferences.getBoolean("magnifier", true);
    }

    @SuppressLint("CommitPrefEdits")
    public void save() {
        sharedPreferences.edit().putBoolean("wordWrap",wordWrap).apply();
        sharedPreferences.edit().putBoolean("icu",useICU).apply();
        sharedPreferences.edit().putBoolean("pinln",pinLineNumber).apply();
        sharedPreferences.edit().putBoolean("sln",showLineNumber).apply();
        sharedPreferences.edit().putBoolean("magnifier",enableMagnifier).apply();
    }
}
