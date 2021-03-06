package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 15/05/17.
 */
public class TrofeoLivello18 extends Trofeo
{
    public static final String LACODIFICA="AGLL81";

    public TrofeoLivello18(Activity act)
    {
        super(act.getResources().getString(R.string.trofeolivello18),act.getResources().getString(R.string.trofeolivello18description));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(lev==null)
        {
            return false;
        }

        for(int i=0;i<18;i++)
        {
            if(!Giocatore.getLivello(i).getCompletato())
            {
                return false;
            }
        }
        return true;
    }
}
