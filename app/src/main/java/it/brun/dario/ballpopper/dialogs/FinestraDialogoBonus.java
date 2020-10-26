package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.brun.dario.ballpopper.views.PannelloBonus;
import it.brun.dario.ballpopper.views.PannelloSalvataggio;
import it.brun.dario.ballpopper.activities.InventarioActivity;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 03/05/17.
 */
public class FinestraDialogoBonus extends DialogFragment
{
    private Bonus bonus;


    public FinestraDialogoBonus()
    {
        super();
    }
    public static FinestraDialogoBonus creaFinestra(Bundle p)
    {
        FinestraDialogoBonus ff=new FinestraDialogoBonus();

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
    public void onStart()
    {
        super.onStart();


        getDialog().getWindow().setLayout(Ambiente.getHEIGHT(),(int)(Ambiente.getHEIGHT()*PannelloSalvataggio.PROPORZIONE));
    }





    private void carica(Bundle bundle)
    {
        bonus =bundle.getParcelable(InventarioActivity.CARICABONUS);
    }

    private void salva (Bundle bundle)
    {
        bundle.putParcelable(InventarioActivity.CARICABONUS,bonus);
    }




    public void onSaveInstanceState(Bundle outstate)
    {
        super.onSaveInstanceState(outstate);
        salva(outstate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        return new PannelloBonus(getActivity(),bonus);
    }
}
