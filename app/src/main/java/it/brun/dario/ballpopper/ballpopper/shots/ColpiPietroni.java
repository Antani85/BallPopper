package it.brun.dario.ballpopper.ballpopper.shots;

import android.app.Activity;
import android.graphics.Bitmap;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.Pietrone;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class ColpiPietroni extends Munizioni
{
    private static String nome;
    public static void assegnaNome(Activity act)
    {
        nome=act.getResources().getString(R.string.colpipietroni);
    }
    public static final String CODIFICA="PEXMLSCPI";
    public String codifica()
    {
        StringBuilder builder=new StringBuilder(CODIFICA);
        builder.append(getNumero());
        builder.append(InventarioMunizioni.SEPARATORE);
        return builder.toString();
    }
    public Bitmap immaginePalla()
    {
        return Pietrone.getImmagine();
    }
    public Bitmap immaginePallaSmall()
    {
        return Pietrone.getImmagineSmall();
    }


    public ColpiPietroni()
    {

        super(nome,5,250);


    }

    public ColpiPietroni(int n)
    {
        super(nome,n,250);

    }



    public Palla creaPalla(Ambiente a, float x, float y, double ang, int liv)
    {
        return new Pietrone(a,x,y,ang,liv);

    }



}
