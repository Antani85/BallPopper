package it.brun.dario.ballpopper.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import it.brun.dario.ballpopper.views.Ambiente;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.PannelloTrofeo;
import it.brun.dario.ballpopper.R;

public class ActivityTrofei extends Activity
{

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        lista=new ListView(this);
        lista.setAdapter(creaAdapter());
        setContentView(lista);

    }

    private PannelloTrofeo creaPannelloTrofeo(int i)
    {

        return new PannelloTrofeo(this,Giocatore.getTrofeo(i),Ambiente.getWIDTH(),Ambiente.getHEIGHT()/4);
    }



    private ListAdapter creaAdapter()
    {
        ListAdapter adapter=new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return Giocatore.getNumTrofei();
            }

            @Override
            public Object getItem(int i)
            {
                return Giocatore.getTrofeo(i);
            }

            @Override
            public long getItemId(int i)
            {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup)
            {

                view = creaPannelloTrofeo(i);

                return view;
            }
        };
        return adapter;
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
