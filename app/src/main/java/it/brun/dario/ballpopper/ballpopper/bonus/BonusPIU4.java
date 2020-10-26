package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 14/05/17.
 */
public class BonusPIU4 extends Bonus
{

    public static final String CODIFICA = "4SUNOBASP";
    private static String descrizione;

    public static void assegnaDescrizione(Activity act)
    {
        descrizione = act.getResources().getString(R.string.bonuspiu4description);
    }

    public String codice()
    {
        return CODIFICA;
    }

    public int getEffetto(int punti, int numPalla)
    {
        return punti + 4*numPalla;
    }


    public BonusPIU4()
    {
        super("+4", descrizione,3);
    }

    public BonusPIU4(int num)
    {
        super("+4", descrizione, num,3);
    }
}
