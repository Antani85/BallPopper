package it.brun.dario.ballpopper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import it.brun.dario.ballpopper.ballpopper.Informazioni;
import it.brun.dario.ballpopper.views.PannelloSalvataggio;
import it.brun.dario.ballpopper.views.PannelloSovrascrivi;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.interfaces.Salvabile;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 21/04/17.
 */
public class FinestraDialogoSalvataggio extends DialogFragment
{

    private View viewUno;
    private View viewDue;
    private ViewGroup layouto;
    public void salvataggio()
    {
        eseguibile.salva(file, info);
        dismiss();
    }
    public void setLayoutUno()
    {
        layouto.removeAllViews();
        layouto.addView(viewUno);
    }
    public void setlayoutDue()
    {
        layouto.removeAllViews();
        layouto.addView(viewDue);
    }

    public void salva(String nomefile)
    {

        String nome = nomefile.trim();
        if (!nome.equals(""))
        {

            File folder = null;

            folder = new File(Environment.getExternalStorageDirectory().toString() + "/" + getResources().getString(R.string.app_folder_name));


            if (!folder.exists())
            {
                folder.mkdirs();

            }


            String directory = folder.toString();


            file = new File(directory, nome + getResources().getString(R.string.extension));
            if (file.exists())
            {
                setlayoutDue();

            }
            else
            {
                eseguibile.salva(file, info);
                dismiss();
            }
        }
    }
    private Salvabile eseguibile;
    private File file;
    private Informazioni info;
    public static FinestraDialogoSalvataggio creaFinestra(Salvabile ese,Informazioni i)
    {
        FinestraDialogoSalvataggio ff=new FinestraDialogoSalvataggio();
        ff.eseguibile=ese;
        ff.info=i;

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
        ((PannelloSalvataggio)viewUno).disalloca();

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

        layouto =(ViewGroup) inflater.inflate(R.layout.layout_dialog_salvataggio, container, false);

        if(viewUno==null)
        {
            viewUno = new PannelloSalvataggio(this);
        }
        if(viewDue==null)
        {
            viewDue = new PannelloSovrascrivi(this);
        }
        layouto.addView(viewUno);
        return layouto;
    }

}
