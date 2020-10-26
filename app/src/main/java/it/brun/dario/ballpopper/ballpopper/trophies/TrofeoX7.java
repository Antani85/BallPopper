package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.Pietrone;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 20/04/17.
 */
public class TrofeoX7 extends Trofeo
{
    public static final String LACODIFICA="AGOE7X";

    public TrofeoX7(Activity act)
    {
        super(act.getResources().getString(R.string.trofeox7),act.getResources().getString(R.string.trofeox7description));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return ((palla instanceof Pietrone)&&palla.getNumScoppiate()>=25);
    }
}
