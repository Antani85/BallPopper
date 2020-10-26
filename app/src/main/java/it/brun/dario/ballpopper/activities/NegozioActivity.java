package it.brun.dario.ballpopper.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.brun.dario.ballpopper.views.AmbienteLivelli;
import it.brun.dario.ballpopper.dialogs.FinestraRisultatoVendita;
import it.brun.dario.ballpopper.dialogs.FinestraVendita;
import it.brun.dario.ballpopper.ballpopper.shots.Munizioni;
import it.brun.dario.ballpopper.views.Negozio;
import it.brun.dario.ballpopper.R;

public class NegozioActivity extends Activity {
    private Negozio negozio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        negozio=new Negozio(this);
        setContentView(negozio);


    }


    public void creaDialog(Munizioni m, int anim)
    {


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        Bundle bundle=new Bundle();
        bundle.putParcelable(Negozio.MUNIZIONI,m);
        bundle.putInt(Negozio.ANIMATION,anim);


        FinestraVendita finestra = FinestraVendita.creaFinestra(bundle);
        finestra.show(ft, "dialog");
        AmbienteLivelli.suonaBlippatore();

    }

    public void creaDialogFinale(int m)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);



        FinestraRisultatoVendita finestra =FinestraRisultatoVendita.creaFinestra(m);
        finestra.show(ft, "dialog");
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
