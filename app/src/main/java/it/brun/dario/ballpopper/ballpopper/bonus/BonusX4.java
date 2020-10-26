package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 11/05/17.
 */
public class BonusX4 extends Bonus
{
    private static String descrizione;
    public static void assegnaDescrizione(Activity act)
    {
        descrizione=act.getResources().getString(R.string.bonusx4description);
    }
    public static final String CODIFICA="4SUNOBASX";
    public String codice()
    {
        return CODIFICA;
    }

    public int getEffetto(int punti,int numPalla)
    {
        return punti*2*numPalla;
    }


    public BonusX4()
    {
        super("x4",descrizione,1);
    }
    public BonusX4(int num)
    {
        super("x4",descrizione,num,1);
    }




}

