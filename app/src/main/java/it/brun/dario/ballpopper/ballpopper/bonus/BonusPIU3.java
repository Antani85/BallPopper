package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 11/05/17.
 */
public class BonusPIU3 extends Bonus
{

    public static final String CODIFICA = "3SUNOBASP";
    private static String descrizione;

    public static void assegnaDescrizione(Activity act)
    {
        descrizione = act.getResources().getString(R.string.bonuspiu3description);
    }

    public String codice()
    {
        return CODIFICA;
    }

    public int getEffetto(int punti, int numPalla)
    {
        return punti + 3*numPalla;
    }


    public BonusPIU3()
    {
        super("+3", descrizione,4);
    }

    public BonusPIU3(int num)
    {
        super("+3", descrizione, num,4);
    }
}
