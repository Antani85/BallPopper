package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.balls.BombaAtomica;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 27/04/17.
 */
public class TrofeoX100Atomico extends Trofeo
{
    public static final String LACODIFICA="AGOC00";

    public TrofeoX100Atomico(Activity act)
    {
        super(act.getResources().getString(R.string.trofeox100atomico),act.getResources().getString(R.string.trofeox100atomicodescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return ((palla instanceof BombaAtomica)&&palla.getNumScoppiate()>=100);
    }
}
