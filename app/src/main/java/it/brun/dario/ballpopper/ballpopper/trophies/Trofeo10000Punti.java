package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 01/05/17.
 */
public class Trofeo10000Punti extends Trofeo
{
    public static final String LACODIFICA="AG01IT";

    public Trofeo10000Punti(Activity act)
    {
        super(act.getResources().getString(R.string.trofeo10000punti),act.getResources().getString(R.string.trofeo10000puntidescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {

        if(palla==null)
        {
            return false;
        }

        return palla.getPuntiTotali()>=100000;
    }
}
