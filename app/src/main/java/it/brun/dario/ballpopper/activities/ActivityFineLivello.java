package it.brun.dario.ballpopper.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.brun.dario.ballpopper.views.AmbienteLivelli;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoGettatrice;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.Informazioni;
import it.brun.dario.ballpopper.views.PannelloFineLivello;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Risultato;

public class ActivityFineLivello extends Activity
{


    private int contatore;
    private Risultato result;
    private Informazioni informazioni;
    private PannelloFineLivello pannello;
    public static final String RISULTATO="RISULTATO";
    public void mostraFinestraGettatrice()
    {


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }

        ft.addToBackStack(null);


        FinestraDialogoGettatrice finestra = new FinestraDialogoGettatrice();
        finestra.show(ft, "dialog");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        result=getIntent().getParcelableExtra(RISULTATO);
        informazioni=getIntent().getParcelableExtra(Giocatore.INFORMAZIONI);
        contatore=getIntent().getIntExtra(LivelliActivity.CONTATORE,0);
        pannello=new PannelloFineLivello(this,result);
        setContentView(pannello);
        if(Giocatore.inventario().eccedente())
        {
            mostraFinestraGettatrice();
        }


    }
    @Override
    protected void onResume()
    {
        super.onResume();
        AmbienteLivelli.suonaVittoria();
    }

    public void finish()
    {
        Intent intent = new Intent(this, LivelliActivity.class);
        intent.putExtra(Giocatore.INFORMAZIONI, informazioni);
        intent.putExtra(LivelliActivity.CONTATORE,contatore);
        startActivity(intent);
        super.finish();

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
