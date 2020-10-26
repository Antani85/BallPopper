package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.brun.dario.ballpopper.ballpopper.shots.Munizioni;
import it.brun.dario.ballpopper.views.Negozio;
import it.brun.dario.ballpopper.views.PannelloSalvataggio;
import it.brun.dario.ballpopper.views.PannelloVendita;
import it.brun.dario.ballpopper.activities.NegozioActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 20/04/17.
 */
public class FinestraVendita extends DialogFragment
{

    private int animation;
    private Munizioni munizioni;
    private PannelloVendita pannello;

    public FinestraVendita()
    {
        super();
    }
    public static FinestraVendita creaFinestra(Bundle p)
    {
        FinestraVendita ff=new FinestraVendita();

        ff.carica(p);
        return ff;

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE,0);

        if(savedInstanceState!=null)
        {
            carica(savedInstanceState);
        }


    }

    @Override
    public void onResume()
    {
        super.onResume();

        getDialog().getWindow().setLayout(Ambiente.getHEIGHT(),(int)(Ambiente.getHEIGHT()*PannelloSalvataggio.PROPORZIONE));





    }


    @Override
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);

        getDialog().getWindow().getAttributes().windowAnimations = animation;

    }


    public void lanciaRisultato(int m)
    {
        ((NegozioActivity)getActivity()).creaDialogFinale(m);
    }



    private void carica(Bundle bundle)
    {
        munizioni =bundle.getParcelable(Negozio.MUNIZIONI);
        animation=bundle.getInt(Negozio.ANIMATION);
    }

    private void salva (Bundle bundle)
    {
        bundle.putParcelable(Negozio.MUNIZIONI,munizioni);
        bundle.putInt(Negozio.ANIMATION,animation);
    }





    public void onSaveInstanceState(Bundle outstate)
    {
        super.onSaveInstanceState(outstate);
        salva(outstate);
    }

    @Override
    public void onStop()
    {
        animation=0;
        super.onStop();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        pannello= new PannelloVendita(this,munizioni);
        return pannello;

    }
}
