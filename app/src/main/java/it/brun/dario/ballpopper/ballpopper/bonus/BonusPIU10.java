package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 11/05/17.
 */
public class BonusPIU10 extends Bonus
{

    public static final String CODIFICA="0SUNOBASP";
    private static String descrizione;
    public static void assegnaDescrizione(Activity act)
    {
        descrizione=act.getResources().getString(R.string.bonuspiu10description);
    }
    public String codice()
    {
        return CODIFICA;
    }
    public int getEffetto(int punti,int numPalla)
    {
        return punti+10*numPalla;
    }


    public BonusPIU10()
    {
        super("+10",descrizione,1);
    }
    public BonusPIU10(int num)
    {
        super("+10",descrizione,num,1);
    }





}
