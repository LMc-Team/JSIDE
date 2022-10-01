package com.lefthumor.jside;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lefthumor.jside.databinding.ActivityCoddingBinding;
import com.lefthumor.jside.ui.main.EditorPagerAdapter;
import com.lefthumor.jside.ui.widget.FuckingShitAndroidViewPager;

import java.lang.reflect.Field;
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
        FuckingShitAndroidViewPager viewPager = binding.viewPager;
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(editorPagerAdapter);
        viewPager.setOffscreenPageLimit(1);
/*

        try {
            initVP(viewPager);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
*/
        openFile("/sdcard/a.js");
        openFile("/sdcard/aaa.xml");
        openFile("/sdcard/log.txt");
    }

    void initVP(ViewPager viewPager) throws Throwable {

//        viewPager.setdis
        Field a = ViewPager.class.getDeclaredField("mTouchSlop");
        a.setAccessible(true);
        a.set(viewPager, 360);
        Field b = ViewPager.class.getDeclaredField("mMinimumVelocity");
        b.setAccessible(true);
        b.set(viewPager, 3);
        a = ViewPager.class.getDeclaredField("mFlingDistance");
        a.setAccessible(true);
        a.set(viewPager, 360);
    }

    public void openFile(String filePath) {
        filePaths.add(filePath);
        fileNames.add(filePath.substring(filePath.lastIndexOf("/") + 1));
        editorPagerAdapter.createFragment(editorPagerAdapter.getCount() + 1, filePath);
    }
}