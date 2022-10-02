package com.lefthumor.jside.ui.settings.editor;

public class AppSettings {
    public static void setEditorSettings(EditorSettings editorSettings) {
        AppSettings.editorSettings = editorSettings;
    }

    private static EditorSettings editorSettings ;

    public static EditorSettings getEditorSettings() {
        return editorSettings;
    }
}
