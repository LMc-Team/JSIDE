package com.lefthumor.jside.ui.main;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lefthumor.jside.CoddingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class EditorPagerAdapter extends FragmentStateAdapter {


    private List<CodeEditorFragment> fragments;

    int total;

    public EditorPagerAdapter(@NonNull CoddingActivity fragmentActivity) {
        super(fragmentActivity);
        fragments = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addNew(CodeEditorFragment fragment) {
        fragments.add(fragment);
        total = fragments.size();
        notifyDataSetChanged();
    }


    @Override
    public long getItemId(int position) {
        return fragments.get(position).hashCode();
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

    @SuppressLint("NotifyDataSetChanged")
    public int createNew() {
        total = fragments.size() + 1;
        notifyDataSetChanged();
        return total;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        CodeEditorFragment fragment = CodeEditorFragment.newInstance(position);
        addNew(fragment);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return total;
    }
}