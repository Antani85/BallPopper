package it.brun.dario.ballpopper;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

/**
 * Created by dario on 17/05/17.
 */
public class RiproduttoreZonale  implements Runnable
{
    private SoundPool soundPool;
    private int zona;

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
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    }


    private int soundID;

    private  int suono;

    public RiproduttoreZonale(Context context)
    {
        zona=-1;
        createSoundPool();

    }
    private int getSoundFromZone(int i)
    {
        switch (i)
        {
            case 1: return R.raw.zona2;
            case 2: return R.raw.zona3;
            case 3: return R.raw.zona4;
            case 4: return R.raw.zona5;
            case 5: return R.raw.zona6;
            case 6: return R.raw.zona7;
            default:return R.raw.zona1;

        }

    }
    public boolean caricaSuono(Context c,int i)
    {
        if(i!=zona)
        {
            zona=i;
            soundPool.stop(soundID);
            suono = soundPool.load(c, getSoundFromZone(zona), 0);
            return true;
        }
        return false;
    }
    public void pausa()
    {
        soundPool.stop(soundID);

    }
    public void riparti()
    {

        play();
    }
    public  void run()
    {

        while((soundID=soundPool.play(suono, 1f,1f, 1, -1, 1f))==0)
        {
            ;
        }
    }
    public void play()
    {
        new Thread(this).start();
    }

}

