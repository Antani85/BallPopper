package it.brun.dario.ballpopper;

import java.io.File;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by dario on 21/04/17.
 */
public class FileData
{

    public Tempo getTempo()
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(file.lastModified()));
        return new Tempo(cal);
    }

    public static double arrotonda(double d)
    {
        d=10*d;
        int l=(int)d;
        return (double)l/10;
    }

    private static final String BYTE="bytes";
    private static final String KILOBYTE="kB";
    private static final String MEGABYTE="MB";


    public String getFileLength()
    {
        if(type<2)
        {
            return "";
        }
        double l=file.length();
        if(l<1024)
        {
            return arrotonda(l)+" "+BYTE;
        }
        l=l/1024;
        if(l<1024)
        {
            return arrotonda(l)+" "+KILOBYTE;
        }
        l=l/1024;
        return arrotonda(l)+" "+MEGABYTE;
    }


    public static Comparator<FileData> getComparatorByName= new Comparator<FileData>()
    {
        @Override
        public int compare(FileData fileData, FileData fileData2)
        {

            int tipo1=fileData.type;
            if(tipo1>2)
            {
                tipo1=2;
            }
            int tipo2=fileData2.type;
            if(tipo2>2)
            {
                tipo2=2;
            }
            if(tipo1-tipo2!=0)
            {
                return tipo1-tipo2;
            }
            return fileData.getName().compareTo(fileData2.getName());

        }
    };
    public static Comparator<FileData> getComparatorByLength= new Comparator<FileData>()
    {
        @Override
        public int compare(FileData fileData, FileData fileData2)
        {

            int tipo1=fileData.type;
            if(tipo1>2)
            {
                tipo1=2;
            }
            int tipo2=fileData2.type;
            if(tipo2>2)
            {
                tipo2=2;
            }
            if(tipo1-tipo2!=0)
            {
                return tipo1-tipo2;
            }
            return (int)(fileData.file.length()-fileData2.file.length());

        }
    };

    public static Comparator<FileData> getComparatorByModified= new Comparator<FileData>()
    {
        @Override
        public int compare(FileData fileData, FileData fileData2)
        {

            int tipo1=fileData.type;
            if(tipo1>2)
            {
                tipo1=2;
            }
            int tipo2=fileData2.type;
            if(tipo2>2)
            {
                tipo2=2;
            }
            if(tipo1-tipo2!=0)
            {
                return tipo1-tipo2;
            }
            return (int)(fileData.file.lastModified()-fileData2.file.lastModified());

        }
    };

    private File file;
    private int type;
    private int risorsaimmagine;



    public int getRisorsaImmagine()
    {
        if(type==0)
        {
            return R.drawable.openfolder;
        }
        else if(type==1)
        {
            return R.drawable.folder;
        }
        else if(type==2)
        {
            return R.drawable.file;
        }
        else
        {
            return R.drawable.file;
        }
    }
    public int getType()
    {
        return type;
    }
    public FileData(File f, int t)
    {

        file=f;
        type=t;

    }
    public String getName()
    {
        return file.getName();
    }

    public String getPath()
    {
        return file.getAbsolutePath();
    }


}