package it.brun.dario.ballpopper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.ads.MobileAds;

import java.lang.ref.WeakReference;

import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.R;


public class ActivityIniziale extends Activity implements Runnable
{


    private static final String LINGUA_ITALIANA="italiano";

    public static long[] getUsedMemorySize()
    {


        try
        {
            Runtime info = Runtime.getRuntime();
            long freeSize = info.freeMemory();
            long totalSize = info.totalMemory();
            long usedSize = totalSize - freeSize;
            return new long[]{usedSize,freeSize,totalSize};
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }



    }


    private static final String TAG_LOG=ActivityIniziale.class.getName();
    private static final long WAIT_INTERVAL=5000L;
    private static final int GO_AHEAD_WHAT=1;
    private static final String IS_DONE_KEY="it.dario.brun.key.IS_DONE_KEY";
    private static final String START_TIME_KEY="it.dario.brun.key.START_TIME_KEY";
    private long mStartTime=-1l;
    private boolean mIsDone;

    private UIHandler myHandler;


    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_DONE_KEY,mIsDone);
        outState.putLong(START_TIME_KEY,mStartTime);
    }

    public void run()
    {

        Giocatore.assegnazione(this);

        synchronized (this)
        {
            contatore++;
            goAhead();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        contatore=0;


        new Thread(this).start();
        MobileAds.initialize(this, "ca-app-pub-8428734223550763~3643016532");

        setContentView(R.layout.activity_iniziale);

        String lingua=getResources().getString(R.string.lingua);
        if(LINGUA_ITALIANA.equals(lingua))
        {
            FrameLayout layout=(FrameLayout)findViewById(R.id.layout);
            layout.setBackgroundResource(R.drawable.palle_sfondo1);
        }



        if(savedInstanceState!=null)
        {
            this.mStartTime=savedInstanceState.getLong(START_TIME_KEY);
        }

        myHandler=new UIHandler(this);


    }

    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        this.mIsDone=savedInstanceState.getBoolean(IS_DONE_KEY);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_iniziale, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }


    protected void onStart()
    {
        super.onStart();
        if(mStartTime==-1)
        {
            mStartTime = SystemClock.uptimeMillis();
        }
        Message goAheadMessage=myHandler.obtainMessage(GO_AHEAD_WHAT);
        myHandler.sendMessageAtTime(goAheadMessage,mStartTime+WAIT_INTERVAL);

    }
    private void goAhead()
    {
        if(contatore>1)
        {
            Intent intent = new Intent(this, ActivityIniziaPartita.class);
            startActivity(intent);
            finish();
        }
    }

    private int contatore;

    private static class UIHandler extends Handler
    {
        private WeakReference<ActivityIniziale> activityRef;
        public UIHandler(ActivityIniziale attivita)
        {
            activityRef= new WeakReference<ActivityIniziale>(attivita);


        }
        public void handleMessage(Message msg)
        {
            ActivityIniziale attiva=activityRef.get();
            if(attiva==null)
            {
                return;
            }

            if (msg.what==GO_AHEAD_WHAT)
            {

                long elapsedTime = SystemClock.uptimeMillis() - attiva.mStartTime;
                if (elapsedTime >= WAIT_INTERVAL && !attiva.mIsDone)
                {
                    attiva.mIsDone = true;
                    synchronized (attiva)
                    {
                        attiva.contatore++;
                        attiva.goAhead();
                    }

                }



            }

        }
    };






}
