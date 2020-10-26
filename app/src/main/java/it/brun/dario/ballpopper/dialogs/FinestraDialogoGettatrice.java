package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.brun.dario.ballpopper.views.PannelloGettatore;
import it.brun.dario.ballpopper.views.PannelloSalvataggio;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 09/05/17.
 */
public class FinestraDialogoGettatrice extends DialogFragment
{

    private PannelloGettatore view;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE,0);



    }
    @Override
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);

        getDialog().getWindow().getAttributes().windowAnimations = R.anim.animazionebase;
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setCancelable(false);

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

        view=new PannelloGettatore(this);
        return view;
    }

}
