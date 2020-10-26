package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.views.PannelloSalvataggio;
import it.brun.dario.ballpopper.views.PannelloVideoRegalo;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 22/05/17.
 */

public class FinestraDialogoVideo extends DialogFragment
{


    public void apriVideo()
    {
        eseguibile.esegui();
        dismiss();
    }


    private Eseguibile eseguibile;
    public static FinestraDialogoVideo creaFinestra(Eseguibile ese )
    {
        FinestraDialogoVideo ff=new FinestraDialogoVideo();
        ff.eseguibile=ese;


        return ff;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE,0);



    }
    @Override
    public  void onPause()
    {

        super.onPause();
        dismiss();
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

        return new PannelloVideoRegalo(this);
    }

}
