package com.example.drudnitskiy.yandexmetro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private Spinner spFrom;
    private Spinner spTo;
    private Button btnFindPath;
    private Integer start;
    private Integer end;

    private Scheme scheme;
    private ArrayList<Station> Path;

    private HashMap<Integer,String> idToName;
    private HashMap<String,Integer> nameToId;

    private int INF = 10000;
    private int vNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            scheme = new Scheme("Stations.txt", this);
            FillSpinner();
            FillHashTables();
            FindShortestPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void FindShortestPath()
    {
        btnFindPath = (Button)findViewById(R.id.btnSPF);
        btnFindPath = (Button)findViewById(R.id.btnSPF);
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st = (String) spFrom.getSelectedItem();
                String ed = (String) spTo.getSelectedItem();
                Integer start=nameToId.get(st);
                Integer end=nameToId.get(ed);
                ArrayList<Link> L = scheme.FindPath(start,end);
                String Path=st;
                for(Link l: L)
                {
                    Path+="->"+idToName.get(l._id);
                }
                Toast.makeText(MainActivity.this,Path,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void FillHashTables()
    {
        idToName = new HashMap<>();
        nameToId = new HashMap<>();
        for(int i=0; i<6; i++)
        {
            idToName.put(i,scheme.Stations.get(i).Name);
            nameToId.put(scheme.Stations.get(i).Name,i);
        }
    }

    private void FillSpinner()
    {
        String[] Stas;
        spFrom = (Spinner)findViewById(R.id.spinner);
        spTo = (Spinner)findViewById(R.id.spinner2);
        Stas = new String[scheme.Stations.values().size()];
        int i = 0;
        Set<Integer> keys = scheme.Stations.keySet();
        Iterator<Integer> it = keys.iterator();
        while (it.hasNext())
        {
            Stas[i]=scheme.Stations.get(it.next()).Name;
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,Stas);
        spFrom.setAdapter(adapter);
        spTo.setAdapter(adapter);
        spFrom.setSelection(2);
        spTo.setSelection(2);
    }
}

