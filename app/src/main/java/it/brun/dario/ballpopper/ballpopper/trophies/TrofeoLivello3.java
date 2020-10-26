package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 27/04/17.
 */
public class TrofeoLivello3 extends Trofeo
{
    public static final String LACODIFICA="AGLL30";
    public TrofeoLivello3(Activity act)
    {
        super(act.getResources().getString(R.string.trofeolivello3),act.getResources().getString(R.string.trofeolivello3description));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(lev==null)
        {
            return false;
        }

        for(int i=0;i<3;i++)
        {
            if(!Giocatore.getLivello(i).getCompletato())
            {
                return false;
            }
        }
        return true;
    }
}
