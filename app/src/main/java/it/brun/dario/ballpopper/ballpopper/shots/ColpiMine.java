package it.brun.dario.ballpopper.ballpopper.shots;

import android.app.Activity;
import android.graphics.Bitmap;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.MinaAntipalla;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class ColpiMine extends Munizioni
{
    private static String nome;
    public static void assegnaNome(Activity act)
    {
        nome=act.getResources().getString(R.string.mineantipalla);
    }

    public static final String CODIFICA="MNXMLSCPI";
    public String codifica()
    {
        StringBuilder builder=new StringBuilder(CODIFICA);
        builder.append(getNumero());
        builder.append(InventarioMunizioni.SEPARATORE);
        return builder.toString();
    }
    public Bitmap immaginePalla()
    {
        return MinaAntipalla.getImmagine();
    }
    public Bitmap immaginePallaSmall()
    {
        return MinaAntipalla.getImmagineSmall();
    }

    public ColpiMine()
    {

        super(nome,15,1500);


    }



    public ColpiMine(int n)
    {
        super(nome,n,1500);

    }

    public Palla creaPalla(Ambiente a, float x, float y, double ang, int liv)
    {
        return new MinaAntipalla(a,x,y,ang,liv);

    }






}
