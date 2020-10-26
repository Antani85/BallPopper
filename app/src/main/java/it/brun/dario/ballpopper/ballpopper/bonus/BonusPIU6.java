package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 14/05/17.
 */
public class BonusPIU6 extends Bonus
{

    public static final String CODIFICA = "6SUNOBASP";
    private static String descrizione;

    public static void assegnaDescrizione(Activity act)
    {
        descrizione = act.getResources().getString(R.string.bonuspiu6description);
    }

    public String codice()
    {
        return CODIFICA;
    }

    public int getEffetto(int punti, int numPalla)
    {
        return punti + 6*numPalla;
    }


    public BonusPIU6()
    {
        super("+6", descrizione,2);
    }

    public BonusPIU6(int num)
    {
        super("+6", descrizione, num,2);
    }
}
