package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 10/04/17.
 */
public class BonusX2 extends Bonus
{
    private static String descrizione;
    public static void assegnaDescrizione(Activity act)
    {
        descrizione=act.getResources().getString(R.string.bonusx2description);
    }
    public static final String CODIFICA="2SUNOBASX";
    public String codice()
    {
        return CODIFICA;
    }

    public int getEffetto(int punti,int numPalla)
    {
        return punti*numPalla;
    }


    public BonusX2()
    {
        super("x2",descrizione,1);
    }

    public BonusX2(int num)
    {
        super("x2",descrizione,num,1);
    }

}
