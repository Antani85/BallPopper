package it.brun.dario.ballpopper.ballpopper.shots;

import android.app.Activity;
import android.graphics.Bitmap;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class ColpiNormali extends Munizioni
{

    private static String nome;
    public static void assegnaNome(Activity act)
    {
        nome=act.getResources().getString(R.string.colpinormali);
    }
    public static final String CODIFICA="NRXMLSCPO";
    public String codifica()
    {
        StringBuilder builder=new StringBuilder(CODIFICA);
        builder.append(getNumero());
        builder.append(InventarioMunizioni.SEPARATORE);
        return builder.toString();
    }
    public Bitmap immaginePalla()
    {
        return Palla.getImmagine();
    }
    public Bitmap immaginePallaSmall()
    {
        return Palla.getImmagineSmall();
    }

    public ColpiNormali()
    {

        super(nome,-1,10);
    }

    public ColpiNormali(int n)
    {
        super(nome,n,10);
    }



    public  Palla creaPalla(Ambiente a, float x, float y, double ang, int liv)
    {
        return new Palla(a,x,y,ang,liv);

    }


}
