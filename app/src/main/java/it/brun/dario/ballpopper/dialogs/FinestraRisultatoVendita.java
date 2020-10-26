package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import it.brun.dario.ballpopper.views.MessageView;
import it.brun.dario.ballpopper.views.Negozio;
import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 20/04/17.
 */
public class FinestraRisultatoVendita extends DialogFragment
{

    private int tipo;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        if(savedInstanceState!=null)
        {
            carica(savedInstanceState);
        }


    }



    public static FinestraRisultatoVendita creaFinestra(Bundle p)
    {
        FinestraRisultatoVendita ff=new FinestraRisultatoVendita();

        ff.carica(p);
        return ff;

    }
    public static FinestraRisultatoVendita creaFinestra(int p)
    {
        FinestraRisultatoVendita ff=new FinestraRisultatoVendita();

        ff.tipo=p;
        return ff;

    }


    @Override
    public void onSaveInstanceState (Bundle outState)
    {
        super.onSaveInstanceState(outState);
        salva(outState);

    }


    private void carica(Bundle bundle)
    {
        tipo =bundle.getInt(Negozio.MUNIZIONI);
    }

    private void salva (Bundle bundle)
    {
        bundle.putInt(Negozio.MUNIZIONI, tipo);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {



        String messaggio=getResources().getString(R.string.insufficiente);

        if(tipo==MessageView.OK_TYPE)
        {
            messaggio=getResources().getString(R.string.conferma_vendita);

        }

        else if (tipo==MessageView.NESSUN_TYPE)
        {
            messaggio=getResources().getString(R.string.nessun_salvataggio);

        }
        else if (tipo==MessageView.FALLITO_TYPE)
        {
            messaggio=getResources().getString(R.string.errore_caricamento);

        }
        View v = inflater.inflate(R.layout.layout_dialog_risultato, container, false);

        ((LinearLayout)v).addView(new MessageView(getActivity(),tipo,messaggio));


        return v;

    }

}
