package it.brun.dario.ballpopper.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoBonus;
import it.brun.dario.ballpopper.views.PannelloInventario;
import it.brun.dario.ballpopper.R;


public class InventarioActivity extends Activity
{

    public void creaDialog(Bonus m)
    {


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        Bundle bundle=new Bundle();
        bundle.putParcelable(CARICABONUS,m);


        FinestraDialogoBonus finestra = FinestraDialogoBonus.creaFinestra(bundle);
        finestra.show(ft, "dialog");
    }
    public static final String CARICABONUS="caricabonus";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PannelloInventario(this));
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
