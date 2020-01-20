package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        printColorsAndPlayAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    void printColorsAndPlayAudio() {
        final ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("red", "lal", R.drawable.color_red, R.raw.color_red));
        colors.add(new Word("yellow", "peela", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Word("blue", "neela", R.mipmap.ic_launcher, R.raw.color_red));
        colors.add(new Word("green", "haara", R.drawable.color_green, R.raw.color_green));
        colors.add(new Word("pink", "gulaabi", R.mipmap.ic_launcher, R.raw.color_red));
        colors.add(new Word("purple", "baigani", R.mipmap.ic_launcher, R.raw.color_red));
        colors.add(new Word("brown", "bhoora", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Word("golden", "shona", R.mipmap.ic_launcher, R.raw.color_red));
        colors.add(new Word("silver", "chandi", R.mipmap.ic_launcher, R.raw.color_red));
        colors.add(new Word("white", "safad", R.drawable.color_white, R.raw.color_white));
        colors.add(new Word("black", "kaala", R.drawable.color_black, R.raw.color_black));
        colors.add(new Word("orange", "narangi", R.mipmap.ic_launcher, R.raw.color_red));

        /*
//        Use Adapter Instead
        LinearLayout root = (LinearLayout) findViewById(R.id.numbers_root_view);
        for(int i=0;i<colors.size();i++){
            TextView child=new TextView(this);
            child.setText(colors.get(i));
            root.addView(child);
        }
        */

        WordAdapter adapter = new WordAdapter(this, colors, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        //    playAudio
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, colors.get(position).getmAudioResourceId());
                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
