package it.brun.dario.ballpopper.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import it.brun.dario.ballpopper.dialogs.FinestraRisultatoVendita;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.MessageView;
import it.brun.dario.ballpopper.views.PannelloGameOver;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Timer;


public class ActivityIniziaPartita extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback
{
    public static final int PERMISSION_CODE=1;

    public  void controllaPermessi()
    {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            String [] permessi={android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,permessi, PERMISSION_CODE);
        }
        else
        {
            prosegui();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,
                                           int[] grantResults)
    {

        if (requestCode == PERMISSION_CODE)
        {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                prosegui();

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
    private boolean gameover;
    public boolean getGameOver()
    {
        return gameover;
    }
    public static final String GAMEOVER="gameover";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        gameover=getIntent().getBooleanExtra(GAMEOVER,false);


        setContentView(new PannelloGameOver(this));

    }
    @Override
    protected void onPause()
    {
        super.onPause();
        if(gameover)
        {
            Timer.stoppaPerdita();
        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if(gameover)
        {
            Timer.suonaPerdita();
        }
    }
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }


    private void mostraFinestraMessaggio()
    {


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }

        ft.addToBackStack(null);


        FinestraRisultatoVendita finestra = FinestraRisultatoVendita.creaFinestra(MessageView.NESSUN_TYPE);
        finestra.show(ft, "dialog");
    }
    public void prosegui()
    {




        File radicale= Environment.getExternalStorageDirectory();

        if(!radicale.exists())
        {
            mostraFinestraMessaggio();
            return;
        }
        File currentDir = new File(radicale.toString() + "/" + getResources().getString(R.string.app_folder_name));


        if (!currentDir.exists())
        {
            mostraFinestraMessaggio();
            return;
        }

        File[] f=currentDir.listFiles();

        if(f==null||f.length==0)
        {
            mostraFinestraMessaggio();
            return;
        }

        Intent intent=new Intent(this,FileChooserActivity.class);
        startActivity(intent);
        finish();


    }
    public void continua()
    {
        Giocatore.creaInventario(this);
        Intent intent=new Intent(this,LivelliActivity.class);
        startActivity(intent);
        finish();
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
}
