package it.brun.dario.ballpopper.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.brun.dario.ballpopper.views.Ambiente;
import it.brun.dario.ballpopper.interfaces.Eseguibile;
import it.brun.dario.ballpopper.FileData;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoCancella;
import it.brun.dario.ballpopper.dialogs.FinestraRisultatoVendita;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.Informazioni;
import it.brun.dario.ballpopper.views.MessageView;
import it.brun.dario.ballpopper.R;

public class FileChooserActivity extends Activity
{

    private float dimensionebig;
    private float dimensionesmall;
    private Bitmap cestino;
    private Comparator<FileData> comparatore;

    public static final String SDCARD="/";
    public static final String FILE_NAME="file";

    private String nomeRadice;
    private String pathradice;
    private File currentDir;
    private ListView listView;

    private void settaTextView()
    {
        TextView textview= ((TextView)findViewById(R.id.filechooserintest));
        Giocatore.setTypeface(textview);
        String testo=getResources().getString(R.string.salvataggi_disponibili);
        Paint p=new Paint();
        Giocatore.setTypeface(p);
        MessageView.setDimensione(testo,p,Ambiente.getWIDTH()*9/10,Ambiente.getHEIGHT()/10);
        textview.setTextSize(TypedValue.COMPLEX_UNIT_PX,p.getTextSize());
        textview.setText(testo);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        dimensionebig=getDimensioneBig();
        dimensionesmall=getDimensioneSmall();
        comparatore=FileData.getComparatorByName;
        cestino=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cestino),(int)(Ambiente.getHEIGHT()/6),(int)(Ambiente.getHEIGHT()/6));

        setContentView(R.layout.activity_file_chooser);
        listView=(ListView)findViewById(R.id.listview_file);
        File radicale= Environment.getExternalStorageDirectory();
        if(radicale.exists())
        {
            nomeRadice = radicale.getName();
            pathradice=radicale.getAbsolutePath();
            currentDir = new File(radicale.toString() + "/" + getResources().getString(R.string.app_folder_name));



            fill(currentDir);

        }
    }









    private String getRelativePath(String path  )
    {



        if(path.equals(pathradice))
        {
            return SDCARD;
        }
        else if (path.startsWith(pathradice))
        {


            return SDCARD+path.substring(pathradice.length() + 1);
        }
        else
        {

            return "";
        }
    }

    private AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int i, long l)
        {
            FileData o=(FileData)adapter.getItemAtPosition(i);

            if(o.getType()<=2)
            {
                return;
                //currentDir = new File(o.getPath());
                //fill(currentDir);
            }
            else
            {

                continua(o);
            }
        }
    };

    private void continua(FileData f)
    {
        try
        {
            Giocatore.creaInventario(this);

            BufferedReader scannone=new BufferedReader(new InputStreamReader(new FileInputStream(f.getPath())));
            Informazioni info=Giocatore.caricaPartita(scannone);
            Intent intent=new Intent(this,LivelliActivity.class);
            intent.putExtra(Giocatore.INFORMAZIONI,info);
            startActivity(intent);
            finish();

        }

        catch(Exception e)
        {
            mostraFinestraErrore();
        }



    }
    private void mostraFinestraCancella(final File f)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }

        ft.addToBackStack(null);


        FinestraDialogoCancella finestra = FinestraDialogoCancella.creaFinestra(f.getName(),new Eseguibile()
        {
            @Override
            public void esegui()
            {
                f.delete();
                fill(currentDir);

            }
        });
        finestra.show(ft, "dialog");

    }
    private void mostraFinestraErrore()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }

        ft.addToBackStack(null);


        FinestraRisultatoVendita finestra = FinestraRisultatoVendita.creaFinestra(MessageView.FALLITO_TYPE);
        finestra.show(ft, "dialog");
    }
    private boolean isSLV(File ff)
    {
        String name=ff.getName();
        int length=name.length();


        if(length>=4&&name.charAt(length-1)=='v'&&name.charAt(length-2)=='l'&&name.charAt(length-3)=='s'&&name.charAt(length-4)=='.')
        {

            return true;
        }

        return false;
    }
    private void fill(File f)
    {

        File[]dirs = f.listFiles();


        List<FileData> dir = new ArrayList<FileData>();

        try
        {
            for(File ff: dirs)
            {

                /*if(ff.isDirectory())
                {
                    dir.add(new FileData(ff, 1));
                }*/
                if(isSLV(ff))
                {
                    dir.add(new FileData(ff,3));
                }
                /*
                else
                {
                    dir.add(new FileData(ff,2));
                }
                */
            }
        }
        catch(Exception e)
        {
            Log.d("errore", e.toString());

        }
        Collections.sort(dir, comparatore);


        /*if(!f.getName().equalsIgnoreCase(nomeRadice))
        {
            dir.add(0, new FileData(new File(f.getParent()),0)
            {
                public String getName()
                {
                    return "..";
                }
            });

        }
        */
        listView.setAdapter(creaAdapter(dir));
        listView.setOnItemClickListener(listener);
        settaTextView();
    }


    private ListAdapter creaAdapter(final List<FileData> lista)
    {
        ListAdapter adapter=new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return lista.size();
            }

            @Override
            public Object getItem(int i)
            {
                return lista.get(i);
            }

            @Override
            public long getItemId(int i)
            {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup)
            {
                if(view==null)
                {
                    view=getLayoutInflater().inflate(R.layout.listrow,null);
                }
                FileData file=(FileData)getItem(i);
                TextView textview= ((TextView)(view.findViewById(R.id.file_name)));
                Giocatore.setTypeface(textview);
                textview.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimensionebig);
                textview.setText(file.getName());
                textview=((TextView)(view.findViewById(R.id.file_size)));
                Giocatore.setTypefaceCorsivo(textview);
                textview.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimensionesmall);
                textview.setText(file.getFileLength());
                textview=((TextView)(view.findViewById(R.id.file_time)));
                Giocatore.setTypefaceCorsivo(textview);
                textview.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimensionesmall);
                textview.setText(file.getTempo().toString());
                ImageView image=(ImageView)(view.findViewById(R.id.file_icon));
                image.setImageBitmap(getBitmap(Ambiente.getHEIGHT()/6));
                ImageView cestin=(ImageView)(view.findViewById(R.id.cestino_icon));
                cestin.setImageBitmap(cestino);
                final int j=i;
                cestin.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mostraFinestraCancella(new File(lista.get(j).getPath()));
                    }
                });
                return view;
            }
        };
        return adapter;
    }
    private static final String FILEPROVA="AAA.slv";
    private  float getDimensioneBig()
    {
        Paint p=new Paint();
        Rect bounds=new Rect();
        int i=1;
        p.setTextSize(i);
        p.getTextBounds(FILEPROVA,0,FILEPROVA.length(),bounds);
        while(bounds.height()<Ambiente.getHEIGHT()/24)
        {
            i++;
            p.setTextSize(i);
            p.getTextBounds(FILEPROVA,0,FILEPROVA.length(),bounds);
        }
        return p.getTextSize();
    }
    private  float getDimensioneSmall()
    {
        Paint p=new Paint();
        Rect bounds=new Rect();
        int i=1;
        p.setTextSize(i);
        p.getTextBounds(FILEPROVA,0,FILEPROVA.length(),bounds);
        while(bounds.height()<Ambiente.getHEIGHT()/36)
        {
            i++;
            p.setTextSize(i);
            p.getTextBounds(FILEPROVA,0,FILEPROVA.length(),bounds);
        }
        return p.getTextSize();
    }


    public static Bitmap getBitmap(float r)
    {
        Bitmap retval= Bitmap.createBitmap((int)r, (int)r, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(retval);
        Paint p=new Paint();
        int cc= Color.argb(255, (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        p.setColor(cc);
        c.drawOval(new RectF(r / 40, r / 40, r * 39 / 40, r * 39 / 40), p);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(r / 20);
        c.drawOval(new RectF(r / 40, r / 40, r * 39 / 40, r * 39 / 40), p);

        c.drawOval(new RectF(r/2,-r/2,r/2+r,r/2),p);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        //c.drawRect(new RectF(r/40+r*19/20,r/40,,r*19/20));
        return retval;

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_iniziale, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }
}