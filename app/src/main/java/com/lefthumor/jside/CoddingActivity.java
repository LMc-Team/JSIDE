package com.lefthumor.jside;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lefthumor.jside.databinding.ActivityCoddingBinding;
import com.lefthumor.jside.ui.main.EditorPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CoddingActivity extends AppCompatActivity {

    private ActivityCoddingBinding binding;


    public List<String> getFileNames() {
        return fileNames;
    }

    public List<String> fileNames;
    public List<String> filePaths;

    EditorPagerAdapter editorPagerAdapter;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileNames = new ArrayList<>();
        filePaths = new ArrayList<>();

        binding = ActivityCoddingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editorPagerAdapter = new EditorPagerAdapter(this);
        ViewPager2 viewPager = binding.viewPager;
        viewPager.setAdapter(editorPagerAdapter);
        TabLayout tabs = binding.tabs;
        new TabLayoutMediator(tabs, viewPager, ((tab, position) -> {
            tab.setText(getFileNames().get(position));
        })).attach();

        openFile("/sdcard/a.js");
    }

    public void openFile(String filePath) {
        filePaths.add(filePath);
        fileNames.add(filePath.substring(filePath.lastIndexOf("/")));
        editorPagerAdapter.getAt(editorPagerAdapter.createNew()).setFile(filePath);
    }
}