package it.brun.dario.ballpopper.ballpopper.bullets;

import it.brun.dario.ballpopper.ballpopper.Moltiplicante;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.ballpopper.bullets.PallaEsplosiva;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 29/04/17.
 */
public class SottoPalla extends PallaEsplosiva
{
    private Moltiplicante parent;

    public SottoPalla(Ambiente ambiente, Moltiplicante p, float i, float j, double d, int k)
    {
        super(ambiente, i, j, d, k);
        parent=p;


    }

    public void addColpita(Bomba b)
    {
        super.addColpita(b);
        synchronized (parent)
        {
            parent.addColpita(b);
        }
    }

    public void run()
    {

        while (true)
        {

            long l=System.currentTimeMillis();
            if(getStoppa())
            {
                break;
            }
            try
            {
                muovi();
                getAmbiente().postInvalidate();
                l=System.currentTimeMillis()-l;
                sleep(Palla.VELOCITAFPS - l);

            }
            catch(IllegalArgumentException ex)
            {
                continue;
            }
            catch (InterruptedException ex)
            {

                break;
            }
        }
        parent.rimuovi(this);

    }


}
