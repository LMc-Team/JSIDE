package com.lefthumor.jside;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lefthumor.jside.ui.settings.editor.AppSettings;
import com.lefthumor.jside.ui.settings.editor.EditorSettings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppSettings.setEditorSettings(new EditorSettings(getSharedPreferences("editor",MODE_PRIVATE)));

        Intent intent = new Intent();
        intent.setClass(this,CoddingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}