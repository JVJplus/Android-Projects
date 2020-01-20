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

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener= new MediaPlayer.OnCompletionListener() {
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

        printNumbersAndPlayAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    void printNumbersAndPlayAudio() {
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "ek", R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two", "do", R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three", "tin", R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four", "char", R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five", "panch", R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six", "che", R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven", "sath", R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "eaath", R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine", "noo", R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "dash", R.drawable.number_ten,R.raw.number_ten));
        words.add(new Word("eleven", "gaarah", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("twelve", "baarah", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("thirteen", "teerah", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("fourteen", "chuodoh", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("fifteen", "pandhrah", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("sixteen", "soloh", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("seventen", "satarah", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("eighten", "aatharah", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("nineten", "ounish", R.mipmap.ic_launcher,R.raw.number_one));
        words.add(new Word("twenty", "bish", R.mipmap.ic_launcher,R.raw.number_one));

        WordAdapter adapter = new WordAdapter   (this, words, R.color.category_numbers);
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
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getmAudioResourceId());
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
