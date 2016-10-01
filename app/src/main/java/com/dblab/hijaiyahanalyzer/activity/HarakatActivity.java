package com.dblab.hijaiyahanalyzer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.dblab.hijaiyahanalyzer.R;
import com.dblab.hijaiyahanalyzer.adapter.HijaiyahAdapter;
import com.dblab.hijaiyahanalyzer.layoutmanager.RtlGridLayoutManager;
import com.dblab.hijaiyahanalyzer.model.Hijaiyah;

import java.util.ArrayList;

/**
 * Created by dblab on 01/09/16.
 */
public class HarakatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harakat);

        final int hurufIndex = getIntent().getIntExtra("index", 0);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_harakat);
        rv.setHasFixedSize(true);
        RtlGridLayoutManager layoutManager = new RtlGridLayoutManager(getApplicationContext(), 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? 3 : 1;
            }
        });
        rv.setLayoutManager(layoutManager);

        final ArrayList<Hijaiyah> harakatList = prepareData();
        HijaiyahAdapter adapter = new HijaiyahAdapter(getApplicationContext(), harakatList);
        final Hijaiyah huruf = (Hijaiyah) getIntent().getSerializableExtra("huruf");
        adapter.setOnItemClickListener(new HijaiyahAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hijaiyah item) {
                if (!(item == huruf)) {
                    Intent intent = new Intent(HarakatActivity.this, BacaActivity.class);
                    intent.putExtra("huruf", huruf);
                    intent.putExtra("harakat", item);
                    int alphIndex = (hurufIndex * 6) + harakatList.indexOf(item);
                    intent.putExtra("index", alphIndex);
                    startActivity(intent);
                }
            }
        });
        rv.setAdapter(adapter);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Harakat");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Hijaiyah> prepareData() {
        ArrayList<Hijaiyah> harakatList = new ArrayList<>();
        harakatList.add((Hijaiyah) getIntent().getSerializableExtra("huruf"));
        harakatList.add(new Hijaiyah(R.drawable.fathah, "fathah"));
        harakatList.add(new Hijaiyah(R.drawable.kasroh, "kasroh"));
        harakatList.add(new Hijaiyah(R.drawable.dhommah, "dhommah"));
        harakatList.add(new Hijaiyah(R.drawable.fathatain, "fathatain"));
        harakatList.add(new Hijaiyah(R.drawable.kasrotain, "kasrotain"));
        harakatList.add(new Hijaiyah(R.drawable.dhommahtain, "dhommahtain"));
        return harakatList;
    }
}
