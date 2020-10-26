package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.brun.dario.ballpopper.views.PannelloPremio;
import it.brun.dario.ballpopper.views.PannelloSalvataggio;
import it.brun.dario.ballpopper.Premio;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 22/05/17.
 */

public class FinestraDialogoPremio extends DialogFragment
{


    private PannelloPremio pannelloPremio;
    private Premio premio;

    @Override
    public void onStop()

    {
        super.onStop();
       dismiss();

        premio.consumaPremio();



    }

    public static FinestraDialogoPremio creaFinestra(Premio p )
    {
        FinestraDialogoPremio ff=new FinestraDialogoPremio();
        ff.premio=p;


        return ff;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE,0);



    }

    public void onResume()
    {
        super.onResume();
        getDialog().getWindow().setLayout(Ambiente.getHEIGHT(),(int)(Ambiente.getHEIGHT()*PannelloSalvataggio.PROPORZIONE));




    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        //v.setLayoutParams(new ViewGroup.LayoutParams(Ambiente.getHEIGHT(),(int)(Ambiente.getHEIGHT()*PannelloSalvataggio.PROPORZIONE)));

        pannelloPremio=new PannelloPremio(this,premio);
        return pannelloPremio;
    }

}
