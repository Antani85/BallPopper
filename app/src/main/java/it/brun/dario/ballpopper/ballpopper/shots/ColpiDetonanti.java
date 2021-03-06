package it.brun.dario.ballpopper.ballpopper.shots;

import android.app.Activity;
import android.graphics.Bitmap;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaDetonante;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class ColpiDetonanti extends Munizioni
{
    private static String nome;
    public static void assegnaNome(Activity act)
    {
        nome=act.getResources().getString(R.string.colpidetonanti);
    }
    public static final String CODIFICA="DEXMLSCPT";
    public String codifica()
    {
        StringBuilder builder=new StringBuilder(CODIFICA);
        builder.append(getNumero());
        builder.append(InventarioMunizioni.SEPARATORE);
        return builder.toString();
    }
    public Bitmap immaginePalla()
    {
        return PallaDetonante.getImmagine();
    }
    public Bitmap immaginePallaSmall()
    {
        return PallaDetonante.getImmagineSmall();
    }

    public ColpiDetonanti()
    {

        super(nome,10,100);


    }


    public ColpiDetonanti(int n)
    {
        super(nome,n,100);


    }


    public Palla creaPalla(Ambiente a, float x, float y, double ang, int liv)
    {
        return new PallaDetonante(a,x,y,ang,liv);

    }






}
