package it.brun.dario.ballpopper.ballpopper.shots;

import android.app.Activity;
import android.graphics.Bitmap;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.balls.BombaAtomica;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class ColpiAtomici extends Munizioni
{
    private static String nome;
    public static void assegnaNome(Activity act)
    {
        nome=act.getResources().getString(R.string.colpiatomici);
    }
    public static final String CODIFICA="AOXMLSCPT";
    public String codifica()
    {
        StringBuilder builder=new StringBuilder(CODIFICA);
        builder.append(getNumero());
        builder.append(InventarioMunizioni.SEPARATORE);
        return builder.toString();

    }

    public Bitmap immaginePalla()
    {
        return BombaAtomica.getImmagine();
    }
    public Bitmap immaginePallaSmall()
    {
        return BombaAtomica.getImmagineSmall();
    }

    public ColpiAtomici()
    {

        super(nome,3,5000);

    }


    public ColpiAtomici(int n)
    {
        super(nome,n,5000);


    }



    public Palla creaPalla(Ambiente a, float x, float y, double ang, int liv)
    {
        return new BombaAtomica(a,x,y,ang,liv);

    }





}
