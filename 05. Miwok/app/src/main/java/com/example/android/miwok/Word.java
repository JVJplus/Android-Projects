package com.example.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId;
    private int mAudioResourceId;
    private boolean hasImage;

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int mResourceId, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = mResourceId;
        this.mAudioResourceId = mAudioResourceId;
        hasImage=true;
    }

//    Especially For Phrases Section!
    public Word(String defaultTranslation, String miwokTranslation,int mAudioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        this.mAudioResourceId = mAudioResourceId;
        hasImage=false;
    }

    public int getmImageResourceId() {  return mImageResourceId; }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public boolean hasImage() { return hasImage; }
}