package it.brun.dario.ballpopper.ballpopper.shots;

import android.app.Activity;
import android.graphics.Bitmap;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaEsplosiva;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class ColpiEsplosivi extends Munizioni
{

    private static String nome;
    public static void assegnaNome(Activity act)
    {
        nome=act.getResources().getString(R.string.colpiesplosivi);
    }
    public static final String CODIFICA="EPXMLSCPS";
    public String codifica()
    {
        StringBuilder builder=new StringBuilder(CODIFICA);
        builder.append(getNumero());
        builder.append(InventarioMunizioni.SEPARATORE);
        return builder.toString();
    }
    public Bitmap immaginePalla()
    {
        return PallaEsplosiva.getImmagine();
    }
    public Bitmap immaginePallaSmall()
    {
        return PallaEsplosiva.getImmagineSmall();
    }

    public ColpiEsplosivi()
    {

        super(nome,15,30);

    }



    public ColpiEsplosivi(int n)
    {
        super(nome,n,30);


    }

    public Palla creaPalla(Ambiente a, float x, float y, double ang, int liv)
    {
        return new PallaEsplosiva(a,x,y,ang,liv);

    }





}
