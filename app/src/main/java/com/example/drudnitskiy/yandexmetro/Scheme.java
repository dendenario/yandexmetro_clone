package com.example.drudnitskiy.yandexmetro;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class Scheme {
    public Hashtable<Integer, Station> Stations;
    private int INF = 10000;
    private int vNum;

    public Scheme(String path, Context context) throws IOException {
        Stations = new Hashtable<>();
        Gson json = new Gson();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(path), "windows-1251"));
            String mLine;
            Station mStation;
            int i = 0;
//            while ((mLine = reader.readLine()) != null)
            while (i != 6) {
                mLine = reader.readLine();
                mStation = json.fromJson(mLine, Station.class);
                Stations.put(i, mStation);
                i++;
            }
        } catch (IOException e) {
            Toast.makeText(context, "No file", Toast.LENGTH_SHORT);

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Toast.makeText(context, "No file", Toast.LENGTH_SHORT);
                }
            }
        }
    }


//        Stations = new Hashtable<>();
//        Gson json = new Gson();
//        Type type = Stations.getClass();
//        Stations = json.fromJson(file,type);
//        Stations = StringToHashTable(file,Stations);


//    public static  <T> Hashtable<Integer, T> StringToHashTable(String s, Hashtable<Integer,T> clazz)
//    {
//        Gson json = new Gson();
//        Hashtable<Integer, T> St  = json.fromJson(s, (Type) clazz);
//        return St;
//    }

    public ArrayList<Link> FindPath(int start, int end) {
        ArrayList<Link> ShortestPath = new ArrayList<>();
        vNum = Stations.size();
        Link[] Prev = new Link[vNum];
        int[] P = new int[vNum];
        boolean[] Used = new boolean[vNum];
        int[] dist = new int[vNum];
        Fill(dist);
        dist[start] = 0;

        for (; ; ) {
            int v = -1;
            for (int nv = 0; nv < vNum; nv++) {
                if (!Used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) {
                    v = nv;
                }
            }
            if (v == -1) {
                break;
            }
            Used[v] = true;
            for (Link i : Stations.get(v).Links) {
                int nv = i._id;
                if (!Used[nv]) // для всех непомеченных
                {
                    if(dist[nv]>dist[v]+i.time) {
                        dist[nv] = dist[v] + i.time;
                        Prev[nv]=i;
                        P[nv]=v;
                    }
                }
            }
        }
        for (int v=end;v!=start;v=P[v])
        {
            ShortestPath.add(0,Prev[v]);
        }
            return ShortestPath;
        }

    private void Fill(int[] dist) {
        for (int i =0; i<dist.length; i++) {
            dist[i] = INF;
        }
    }
}
