package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.views.PannelloCancellaFile;
import it.brun.dario.ballpopper.views.PannelloSalvataggio;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 04/05/17.
 */
public class FinestraDialogoCancella extends DialogFragment
{



    public void cancella()
    {
        eseguibile.esegui();
        dismiss();
    }
    public void chiudi()
    {

        dismiss();
    }


    public String getNome()
    {
        return nome;
    }

    private Eseguibile eseguibile;
    private String nome;

    public static FinestraDialogoCancella creaFinestra(String nomeFile,Eseguibile ese)
    {
        FinestraDialogoCancella ff=new FinestraDialogoCancella();
        ff.eseguibile=ese;
        ff.nome=nomeFile;

        return ff;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE,0);



    }
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



        return new PannelloCancellaFile(this);
    }

}
