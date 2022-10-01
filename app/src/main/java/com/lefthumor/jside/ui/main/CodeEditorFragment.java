package com.lefthumor.jside.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lefthumor.jside.api.OnEditorLoadFinished;
import com.lefthumor.jside.databinding.FragmentCoddingBinding;
import com.lefthumor.jside.ui.settings.editor.AppSettings;
import com.lefthumor.jside.ui.settings.editor.EditorSettings;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.component.Magnifier;

/**
 * A placeholder fragment containing a simple view.
 */
public class CodeEditorFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentCoddingBinding binding;

    OnEditorLoadFinished onEditorLoadFinished;

    public CodeEditorFragment(OnEditorLoadFinished loadFinished) {
      onEditorLoadFinished = loadFinished;
    }

    public void setFile(String fp) {
        try {
            codeEditor.setText(FileUtils.readFileToString(new File(fp)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    CodeEditor codeEditor;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentCoddingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        codeEditor = binding.codeEditor;
        initEditor(codeEditor);

        onEditorLoadFinished.onFinished();
        return root;
    }

    public void initEditor(CodeEditor codeEditor) {
        EditorSettings editorSettings = AppSettings.getEditorSettings();

        codeEditor.getProps().useICULibToSelectWords = editorSettings.isUseICU();
        codeEditor.getComponent(Magnifier.class).setEnabled(editorSettings.isEnableMagnifier());
        codeEditor.setWordwrap(editorSettings.isWordWrap());
        codeEditor.setLineNumberEnabled(editorSettings.isShowLineNumber());
        codeEditor.setPinLineNumber(editorSettings.isPinLineNumber());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}