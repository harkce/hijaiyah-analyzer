package com.dblab.hijaiyahanalyzer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dblab.hijaiyahanalyzer.R;
import com.dblab.hijaiyahanalyzer.adapter.HijaiyahAdapter;
import com.dblab.hijaiyahanalyzer.layoutmanager.RtlGridLayoutManager;
import com.dblab.hijaiyahanalyzer.model.Hijaiyah;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_huruf);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new RtlGridLayoutManager(getApplicationContext(), 4);
        rv.setLayoutManager(layoutManager);

        ArrayList<Hijaiyah> hijaiyahList = prepareData();
        HijaiyahAdapter adapter = new HijaiyahAdapter(getApplicationContext(), hijaiyahList);
        adapter.setOnItemClickListener(new HijaiyahAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hijaiyah item) {
                Intent intent = new Intent(MainActivity.this, HarakatActivity.class);
                intent.putExtra("huruf", item);
                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
    }

    private ArrayList<Hijaiyah> prepareData() {
        ArrayList<Hijaiyah> hijaiyahList = new ArrayList<>();
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf1, "alif"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf2, "ba"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf3, "ta"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf4, "tsa"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf5, "ja"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf6, "ha"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf7, "kho"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf8, "da"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf9, "dza"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf10, "ro"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf11, "za"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf12, "sa"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf13, "sya"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf14, "sho"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf15, "dho"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf16, "tho"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf17, "zo"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf18, "a"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf19, "go"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf20, "fa"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf21, "qo"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf22, "ka"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf23, "la"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf24, "ma"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf25, "na"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf26, "wa"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf27, "ha"));
        hijaiyahList.add(new Hijaiyah(R.drawable.huruf28, "ya"));
        return hijaiyahList;
    }
}
