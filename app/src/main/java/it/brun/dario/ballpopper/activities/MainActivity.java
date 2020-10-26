package it.brun.dario.ballpopper.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.brun.dario.ballpopper.views.Ambiente;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.Informazioni;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.levels.Livello;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Risultato;

public class MainActivity extends Activity
{




    public static final String BOMBE="bombe";
    public static final String LIVELLOCOMPIUTO="livellocompiuto";
    public static final String POSIZIONELIVELLO="posizionelivello";
    public static final String TEMPOTRASCORSO="tempotrascorso";

    private int contatore;
    private InventarioMunizioni inventario;

    private Informazioni informazioni;


    private Livello level;

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(MainActivity.POSIZIONELIVELLO,level.getStato());

        ambiente.salva(outState);

    }

    public void termina()
    {
        Intent intent=new Intent(this,ActivityIniziaPartita.class);
        intent.putExtra(ActivityIniziaPartita.GAMEOVER,true);
        startActivity(intent);
        finish();

    }
    public void termina(Risultato result)
    {
        Giocatore.completa(level);
        result.setPuntiLivello(level.getPuntiBonus());
        addPunti((int)(level.getPuntiBonus()*(result.getTime()/1000)));

        Intent intent=new Intent(this,ActivityFineLivello.class);
        intent.putExtra(ActivityFineLivello.RISULTATO,result);
        intent.putExtra(Giocatore.INFORMAZIONI,informazioni);
        intent.putExtra(LivelliActivity.CONTATORE,contatore);
        startActivity(intent);
        finish();
    }

    public void setTerminato()
    {
        if(ambiente!=null)
        {
            ambiente.setTerminato();
        }
    }
    private Ambiente ambiente;
    public void lancia(Bomba b)
    {
        ambiente.lanciaBomba(b);
    }



    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }



    public void addPotenziamento(Bonus b)
    {
        inventario.addPotenziamento(b);

    }
    public void addPunti(int p)
    {
        inventario.addPunti(p);
    }


    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        ambiente.distruggi();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        inventario=Giocatore.inventario();


        level=Livello.getLivello(getIntent().getIntExtra(LivelliActivity.LIVELLO,0),this,inventario);
        informazioni=getIntent().getParcelableExtra(Giocatore.INFORMAZIONI);
        contatore=getIntent().getIntExtra(LivelliActivity.CONTATORE,0);
        boolean terminato=false;
        if(savedInstanceState!=null)
        {
            terminato=savedInstanceState.getBoolean(LIVELLOCOMPIUTO);
            level.setStato(savedInstanceState.getInt(POSIZIONELIVELLO));
        }

        ambiente=new Ambiente(this,inventario,level,terminato);
        if(savedInstanceState!=null)
        {

            ambiente.caricaSalvataggio(savedInstanceState);
        }

        setContentView(ambiente);
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
