package it.brun.dario.ballpopper;

/**
 * Created by dario on 24/04/17.
 */
public class FloatValore
{
    private float valore;
    public FloatValore(float f)
    {
        valore=f;
    }
    public boolean equals (Object o)
    {
        if(!(o instanceof FloatValore))
        {
            return false;
        }
        FloatValore ff=(FloatValore)o;
        return ff.valore==valore;
    }

    public void setValore(float f)
    {
        valore=f;
    }
    public float getValore()
    {
        return valore;
    }
}
