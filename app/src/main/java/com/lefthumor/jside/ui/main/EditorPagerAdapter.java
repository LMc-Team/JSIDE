package com.lefthumor.jside.ui.main;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lefthumor.jside.CoddingActivity;
import com.lefthumor.jside.api.OnEditorLoadFinished;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class EditorPagerAdapter extends FragmentStatePagerAdapter {


    private List<CodeEditorFragment> fragments;

    List<Long> idList;

    int total;

    CoddingActivity activity;

    public EditorPagerAdapter(@NonNull CoddingActivity fragmentActivity) {
        super(fragmentActivity.getSupportFragmentManager());
        fragments = new ArrayList<>();
        idList = new ArrayList<>();
        activity = fragmentActivity;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addNew(CodeEditorFragment fragment) {
        fragments.add(fragment);
        total = fragments.size();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void delete(CodeEditorFragment fragment) {
        fragments.remove(fragment);
        total = fragments.size();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clear() {
        fragments.clear();
        total = 0;
        notifyDataSetChanged();
    }

    public CodeEditorFragment getAt(int pos) {
        return fragments.get(pos);
    }
    @NonNull
//    @Override
    public Fragment createFragment(int position,String fp) {
        CodeEditorFragment fragment;
        fragment= new CodeEditorFragment(() -> {
            CodeEditorFragment codeEditorFragment = (CodeEditorFragment)getItem(position-1);
            codeEditorFragment.setFile(fp);
        });

        addNew(fragment);
        return fragment;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return activity.getFileNames().get(position);
    }
}