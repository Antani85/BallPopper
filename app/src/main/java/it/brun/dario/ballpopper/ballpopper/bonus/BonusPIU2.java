package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 11/05/17.
 */
public class BonusPIU2 extends Bonus
{

    public static final String CODIFICA = "2SUNOBASP";
    private static String descrizione;

    public static void assegnaDescrizione(Activity act)
    {
        descrizione = act.getResources().getString(R.string.bonuspiu2description);
    }

    public String codice()
    {
        return CODIFICA;
    }

    public int getEffetto(int punti, int numPalla)
    {
        return punti + 2*numPalla;
    }


    public BonusPIU2()
    {
        super("+2", descrizione,6);
    }

    public BonusPIU2(int num)
    {
        super("+2", descrizione, num,6);
    }
}
