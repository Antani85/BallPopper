package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 10/04/17.
 */
public class BonusPIU5 extends Bonus
{

    public static final String CODIFICA="5SUNOBASP";
    private static String descrizione;
    public static void assegnaDescrizione(Activity act)
    {
        descrizione=act.getResources().getString(R.string.bonuspiu5description);
    }
    public String codice()
    {
        return CODIFICA;
    }
    public int getEffetto(int punti,int numPalla)
    {
        return punti+5*numPalla;
    }


    public BonusPIU5()
    {
        super("+5",descrizione,3);
    }
    public BonusPIU5(int num)
    {
        super("+5",descrizione,num,3);
    }





}
