package it.brun.dario.ballpopper.ballpopper.shots;

import android.app.Activity;
import android.graphics.Bitmap;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaPerforante;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class ColpiPerforanti extends Munizioni
{
    private static String nome;
    public static void assegnaNome(Activity act)
    {
        nome=act.getResources().getString(R.string.colpiperforanti);
    }
    public static final String CODIFICA="PRXMLSCPE";
    public String codifica()
    {
        StringBuilder builder=new StringBuilder(CODIFICA);
        builder.append(getNumero());
        builder.append(InventarioMunizioni.SEPARATORE);
        return builder.toString();
    }
    public Bitmap immaginePalla()
    {
        return PallaPerforante.getImmagine();
    }
    public Bitmap immaginePallaSmall()
    {
        return PallaPerforante.getImmagineSmall();
    }

    public ColpiPerforanti()
    {

        super(nome,20,20);

    }

    public ColpiPerforanti(int n)
    {
        super(nome,n,20);


    }



    public Palla creaPalla(Ambiente a, float x, float y, double ang, int liv)
    {
        return new PallaPerforante(a,x,y,ang,liv);

    }


}
