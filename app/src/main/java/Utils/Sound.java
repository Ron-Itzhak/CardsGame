package Utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.cardsgame.R;

public class Sound {



    public static void play_sound(Context context,MediaPlayer mp,int sound) {
        if(mp==null)
            mp = MediaPlayer.create(context,sound);
        mp.start();
        Sound.maxVolume(context);
    }

    public static void pause_sound(MediaPlayer mp) {
        if(mp!=null)
            mp.pause();
    }


    public static void stop_sound(MediaPlayer mp) {
        if(mp!=null)
            //mp.stop();
            mp.release();
        //mp=null;
    }



    public static void maxVolume(Context context){
        AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
    }


}
