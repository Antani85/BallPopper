package it.brun.dario.ballpopper;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

/**
 * Created by pierpaolo on 09/07/14.
 */
public class Riproduttore implements Runnable
{
    private SoundPool soundPool;

    public void rilascia()
    {
        soundPool.release();
    }
    protected void createSoundPool()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            createNewSoundPool();
        }
        else
        {
            createOldSoundPool();
        }
    }


    protected void createNewSoundPool()
    {
        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
    }


    protected void createOldSoundPool()
    {
        soundPool = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
    }
    public void pausa()
    {
        soundPool.stop(soundID);

    }
    private int soundID;

    private  int suono;
    public Riproduttore(Context context, int sound)
    {
        createSoundPool();
        suono=soundPool.load(context,sound, 0);

    }
    public  void run()
    {
        soundID=soundPool.play(suono, 1f, 1f, 1, 0, 1f);
    }
    public void play()
    {
        new Thread(this).start();
    }

}

