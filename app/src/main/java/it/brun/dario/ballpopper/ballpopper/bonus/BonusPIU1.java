package it.brun.dario.ballpopper.ballpopper.bonus;

import android.app.Activity;

import it.brun.dario.ballpopper.R;

/**
 * Created by dario on 11/05/17.
 */
public class BonusPIU1 extends Bonus
{

    public static final String CODIFICA="1SUNOBASP";
    private static String descrizione;
    public static void assegnaDescrizione(Activity act)
    {
        descrizione=act.getResources().getString(R.string.bonuspiu1description);
    }
    public String codice()
    {
        return CODIFICA;
    }
    public int getEffetto(int punti,int numPalla)
    {
        return punti+numPalla;
    }


    public BonusPIU1()
    {
        super("+1",descrizione,6);
    }
    public BonusPIU1(int num)
    {
        super("+1",descrizione,num,6);
    }





}