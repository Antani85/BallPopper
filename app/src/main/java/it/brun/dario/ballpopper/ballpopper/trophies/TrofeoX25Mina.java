package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bullets.MinaAntipalla;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 27/04/17.
 */
public class TrofeoX25Mina extends Trofeo
{
    public static final String LACODIFICA="AGAN52";

    public TrofeoX25Mina(Activity act)
    {
        super(act.getResources().getString(R.string.trofeox25mina),act.getResources().getString(R.string.trofeox25minadescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return ((palla instanceof MinaAntipalla)&&palla.getNumScoppiate()>=25);
    }
}
