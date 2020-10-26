package it.brun.dario.ballpopper.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.File;
import java.lang.ref.WeakReference;

import it.brun.dario.ballpopper.views.AmbienteLivelli;
import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoPremio;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoSalvataggio;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoVideo;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.Informazioni;
import it.brun.dario.ballpopper.levels.Livello;
import it.brun.dario.ballpopper.Premio;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.interfaces.Salvabile;
import it.brun.dario.ballpopper.services.Salvataggio;


public class LivelliActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback
{

    public void controllaPermessi()
    {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            String [] permessi={android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,permessi, ActivityIniziaPartita.PERMISSION_CODE);
        }
        else
        {
            mostraFinestraSalvataggio();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,
                                           int[] grantResults)
    {

        if (requestCode == ActivityIniziaPartita.PERMISSION_CODE)
        {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                mostraFinestraSalvataggio();

            }
            else
            {
                return;
            }


        }
        else
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private Premio premio;
    private static final String ID_VIDEO_PREMIO="ca-app-pub-8428734223550763/5427474138";
    private static final String ID_SPOT_PRINCIPALE="ca-app-pub-8428734223550763/9585145337";

    private RewardedVideoAd mAd;
    private RewardedVideoAdListener reguardo=new RewardedVideoAdListener()
    {
        @Override
        public void onRewardedVideoAdLoaded()
        {
            Message messaggio=handler.obtainMessage(VIDEO_SPOT);
            handler.sendMessage(messaggio);
        }

        @Override
        public void onRewardedVideoAdOpened()
        {

        }

        @Override
        public void onRewardedVideoStarted()
        {

        }

        @Override
        public void onRewardedVideoAdClosed()
        {

        }

        @Override
        public void onRewarded(RewardItem rewardItem)
        {

            premio=generaPremio();
            mostraFinestraPremio();
            ambienteLivelli.settaVideo(false);
        }

        @Override
        public void onRewardedVideoAdLeftApplication()
        {

        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int i)
        {
            Log.d("probl nel caricamento","mannaggia");
        }
    };

    private Premio generaPremio()
    {
        return Premio.generaPremio(this);
    }
    private void mostraVideo()
    {
        if (mAd.isLoaded())
        {
            mAd.show();
        }
    }

    private SpotHandler handler;
    private InterstitialAd mInterstitialAd;



    private  int contatore;


    private boolean pippone;
    public void mostraFinestraPremio()
    {

        try
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null)
            {
                ft.remove(prev);
            }

            ft.addToBackStack(null);


            FinestraDialogoPremio finestra = FinestraDialogoPremio.creaFinestra(premio);
            finestra.show(ft, "dialog");
        }
        catch(Exception e)
        {
            pippone=true;
        }
    }









    public void mostraFinestraVideo()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }

        ft.addToBackStack(null);


        FinestraDialogoVideo finestra = FinestraDialogoVideo.creaFinestra(new Eseguibile() {
            @Override
            public void esegui()
            {
                mostraVideo();
            }
        });
        finestra.show(ft, "dialog");
    }
    private  void settaSpot(Activity act)
    {
        mInterstitialAd = new InterstitialAd(act);
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdUnitId(ID_SPOT_PRINCIPALE);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener()
        {


            @Override
            public void onAdClosed()
            {
                mInterstitialAd=null;
            }
            @Override
            public void onAdLoaded()
            {
                Message messaggio=handler.obtainMessage(SPOT);
                handler.sendMessage(messaggio);
            }
        });

    }

    public  boolean showSpot()
    {
        if(contatore%2==1)
        {


            if (mInterstitialAd!=null&&mInterstitialAd.isLoaded()&&visibile)
            {
                mInterstitialAd.show();
                return true;
            }

        }
        return  false;

    }

    public static final String LIVELLO="livello";

    private boolean visibile;
    public boolean isVisibile()
    {
        return visibile;
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }


    private AmbienteLivelli ambienteLivelli;

    public Informazioni getInformazioni(Bundle saved)
    {

        return saved.getParcelable(Giocatore.INFORMAZIONI);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        visibile=false;

        ambienteLivelli.pausa();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        visibile=true;
        ambienteLivelli.riparti();
        if(pippone)
        {
            mostraFinestraPremio();
        }
        pippone=false;
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ambienteLivelli.rilascia();
    }

    public static final String CONTATORE="contatore";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        handler=new SpotHandler(this);
        Informazioni informazioni=null;
        if(savedInstanceState!=null)
        {
            contatore=savedInstanceState.getInt(CONTATORE);
            informazioni=getInformazioni(savedInstanceState);

        }
        else
        {

            informazioni=getIntent().getParcelableExtra(Giocatore.INFORMAZIONI);
            contatore=getIntent().getIntExtra(CONTATORE,-1);
            contatore++;
            if(contatore%2==1)
            {
                settaSpot(this);
            }
            else
            {

                mAd = MobileAds.getRewardedVideoAdInstance(this);
                mAd.setRewardedVideoAdListener(reguardo);
               // mAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
                mAd.loadAd(ID_VIDEO_PREMIO, new AdRequest.Builder().build());

            }


        }
        ambienteLivelli=new AmbienteLivelli(this,informazioni);

        setContentView(ambienteLivelli);

    }

    private static final int SPOT=1;
    private static final int VIDEO_SPOT=2;




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
    public void dispiega(Livello lev)
    {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra(LIVELLO,lev.getNumero());
        intent.putExtra(Giocatore.INFORMAZIONI,ambienteLivelli.salva());
        intent.putExtra(CONTATORE,contatore);


        startActivity(intent);
        finish();
    }
    public void apriNegozio()
    {
        Intent intent=new Intent(this,NegozioActivity.class);
        startActivity(intent);
    }
    public void apriInventario()
    {
        Intent intent=new Intent(this,InventarioActivity.class);
        startActivity(intent);
    }
    public void apriTrofei()
    {
        Intent intent=new Intent(this,ActivityTrofei.class);
        startActivity(intent);
    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Giocatore.INFORMAZIONI,ambienteLivelli.salva());
        outState.putInt(CONTATORE,contatore);

    }

    public void mostraFinestraSalvataggio()
    {


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }

        ft.addToBackStack(null);


        FinestraDialogoSalvataggio finestra = FinestraDialogoSalvataggio.creaFinestra(new Salvabile()
        {
            public void salva(File f, Informazioni i)
            {
                salvataggio(f,i);
            }
        },ambienteLivelli.getInformazioni());
        finestra.show(ft, "dialog");
    }
    private void salvataggio(File f,Informazioni i)
    {
        Intent intento = new Intent(this, Salvataggio.class);

        intento.putExtra(Giocatore.URI, f);
        intento.putExtra(Giocatore.INFORMAZIONI,i);
        startService(intento);
    }

    private static class SpotHandler extends Handler
    {
        private WeakReference<LivelliActivity> activityRef;
        public SpotHandler(LivelliActivity attivita)
        {
            activityRef= new WeakReference<LivelliActivity>(attivita);


        }
        public void handleMessage(Message msg)
        {
            LivelliActivity attiva=activityRef.get();
            if(attiva==null)
            {
                return;
            }

            if (msg.what==SPOT)
            {

               attiva.showSpot();



            }
            else if(msg.what==VIDEO_SPOT)
            {
                attiva.ambienteLivelli.settaVideo(true);
            }

        }
    };





}
