package com.lefthumor.jside;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.google.android.material.tabs.TabLayout;
import com.lefthumor.jside.databinding.ActivityCoddingBinding;
import com.lefthumor.jside.ui.main.EditorPagerAdapter;
import com.lefthumor.jside.ui.settings.editor.AppSettings;
import com.lefthumor.jside.ui.widget.FuckingShitAndroidViewPager;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CoddingActivity extends AppCompatActivity {

    private ActivityCoddingBinding binding;


    public List<CharSequence> getFileNames() {
        return fileNames;
    }

    public List<CharSequence> fileNames;
    public List<String> filePaths;

    EditorPagerAdapter editorPagerAdapter;


    SharedPreferences sharedPreferences;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileNames = new ArrayList<>();
        filePaths = new ArrayList<>();

        binding = ActivityCoddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editorPagerAdapter = new EditorPagerAdapter(this);
        FuckingShitAndroidViewPager viewPager = binding.viewPager;
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(editorPagerAdapter);
        viewPager.setOffscreenPageLimit(1);
/*

        openFile("/sdcard/a.js");
        openFile("/sdcard/aaa.xml");
        openFile("/sdcard/log.txt");
*/

        sharedPreferences = getSharedPreferences("editor", MODE_PRIVATE);
    }


    public void openFile(String filePath) {
        filePaths.add(filePath);
        fileNames.add(filePath.substring(filePath.lastIndexOf("/") + 1));
        editorPagerAdapter.createFragment(editorPagerAdapter.getCount() + 1, filePath);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (menu.size() == 5) {

            MenuItem wordWrap = menu.findItem(R.id.menu_open_wordwrap);
            MenuItem icu = menu.findItem(R.id.menu_open_icu);
            MenuItem lm = menu.findItem(R.id.menu_open_linenumber);
            MenuItem pin = menu.findItem(R.id.menu_open_pinlinenumber);
            MenuItem magnifier = menu.findItem(R.id.menu_open_magnifier);

            wordWrap.setChecked(AppSettings.getEditorSettings().isWordWrap());
            icu.setChecked(AppSettings.getEditorSettings().isUseICU());
            lm.setChecked(AppSettings.getEditorSettings().isShowLineNumber());
            pin.setChecked(!AppSettings.getEditorSettings().isPinLineNumber());
            magnifier.setChecked(AppSettings.getEditorSettings().isEnableMagnifier());
//        }
        return super.onPrepareOptionsMenu(menu);
    }

    @SuppressLint({"NonConstantResourceId", "SdCardPath"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_openfile:
                DialogProperties properties = new DialogProperties();
                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                properties.selection_type = DialogConfigs.FILE_SELECT;
                properties.root = new File("/sdcard/");
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = null;
                FilePickerDialog dialog = new FilePickerDialog(CoddingActivity.this,properties);
                dialog.setTitle("Select a File");
                dialog.setDialogSelectionListener(files -> {
                    openFile(files[0]);
                    //files is the array of the paths of files selected by the Application User.
                });

                dialog.show();

                break;


            case R.id.menu_open_icu:
                item.setChecked(!item.isChecked());
                AppSettings.getEditorSettings().setUseICU(item.isChecked());
                AppSettings.getEditorSettings().save();
                editorPagerAdapter.resetEditor();
                break;
            case R.id.menu_open_wordwrap:
                item.setChecked(!item.isChecked());
                AppSettings.getEditorSettings().setWordWrap(item.isChecked());
                AppSettings.getEditorSettings().save();
                editorPagerAdapter.resetEditor();
                break;
            case R.id.menu_open_linenumber:
                item.setChecked(!item.isChecked());
                AppSettings.getEditorSettings().setShowLineNumber(item.isChecked());
                AppSettings.getEditorSettings().save();
                editorPagerAdapter.resetEditor();
                break;
            case R.id.menu_open_pinlinenumber:
                item.setChecked(!item.isChecked());
                AppSettings.getEditorSettings().setPinLineNumber(!item.isChecked());
                AppSettings.getEditorSettings().save();
                editorPagerAdapter.resetEditor();
                break;
            case R.id.menu_open_magnifier:
                item.setChecked(!item.isChecked());
                AppSettings.getEditorSettings().setEnableMagnifier(item.isChecked());
                AppSettings.getEditorSettings().save();
                editorPagerAdapter.resetEditor();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}