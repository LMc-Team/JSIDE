package com.lefthumor.jside.ui.settings.editor;

public class EditorSettings {


    boolean wordWrap;

    boolean showLineNumber;

    boolean pinLineNumber;

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




}
