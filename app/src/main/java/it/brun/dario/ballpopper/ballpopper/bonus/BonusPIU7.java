package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 14/05/17.
 */
public class BonusPIU7 extends Bonus
{

    public static final String CODIFICA = "7SUNOBASP";
    private static String descrizione;

    public static void assegnaDescrizione(Activity act)
    {
        descrizione = act.getResources().getString(R.string.bonuspiu7description);
    }

    public String codice()
    {
        return CODIFICA;
    }

    public int getEffetto(int punti, int numPalla)
    {
        return punti + 7*numPalla;
    }


    public BonusPIU7()
    {
        super("+7", descrizione,2);
    }

    public BonusPIU7(int num)
    {
        super("+7", descrizione, num,2);
    }
}
