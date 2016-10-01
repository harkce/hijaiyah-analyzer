package com.dblab.hijaiyahanalyzer.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dblab.hijaiyahanalyzer.R;
import com.dblab.hijaiyahanalyzer.model.Hijaiyah;

import java.io.File;
import java.io.IOException;

/**
 * Created by dblab on 01/09/16.
 */
public class BacaActivity extends AppCompatActivity {

    private ImageView imgAtas, imgBawah, imgCheck;
    private Button btnPlay, btnStop, btnRecord, btnVerifikasi;
    private MediaRecorder audioRecorder;
    private String outputFile;

    final private int REQUEST_CODE_ASK_STORAGE = 120;
    final private int REQUEST_CODE_ASK_RECORDING = 121;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca);

        imgAtas = (ImageView) findViewById(R.id.img_atas);
        imgBawah = (ImageView) findViewById(R.id.img_bawah);
        imgCheck = (ImageView) findViewById(R.id.img_check);

        btnPlay = (Button) findViewById(R.id.btn_play);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnRecord = (Button) findViewById(R.id.btn_record);
        btnVerifikasi = (Button) findViewById(R.id.btn_verifikasi);

        btnStop.setEnabled(false);
        btnPlay.setEnabled(false);
        btnVerifikasi.setEnabled(false);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Baca");

        Hijaiyah huruf = (Hijaiyah) getIntent().getSerializableExtra("huruf");
        Hijaiyah harakat = (Hijaiyah) getIntent().getSerializableExtra("harakat");

        if ((harakat.getDrawableId() == R.drawable.kasroh) || (harakat.getDrawableId() == R.drawable.kasrotain)) {
            imgAtas.setImageDrawable(ContextCompat.getDrawable(this, huruf.getDrawableId()));
            imgBawah.setImageDrawable(ContextCompat.getDrawable(this, harakat.getDrawableId()));
        } else {
            imgAtas.setImageDrawable(ContextCompat.getDrawable(this, harakat.getDrawableId()));
            imgBawah.setImageDrawable(ContextCompat.getDrawable(this, huruf.getDrawableId()));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            int hasWriteStoragePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_STORAGE);
                return;
            }
            outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        } else {
            outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        }
        Log.i("OutputFileHabib", outputFile);

//        // Check file exist
//        File file = new File(outputFile);
//        if (file.exists()) {
//            btnPlay.setEnabled(true);
//        }

        if (Build.VERSION.SDK_INT >= 23) {
            int hasRecordingPermission = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (hasRecordingPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_ASK_RECORDING);
                return;
            }
            setupMediaRecorder();
        } else {
            setupMediaRecorder();
        }

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (audioRecorder == null) {
                        setupMediaRecorder();
                    }
                    audioRecorder.prepare();
                    audioRecorder.start();
                } catch (IllegalStateException | IOException ex) {
                    ex.printStackTrace();
                }

                btnRecord.setEnabled(false);
                btnStop.setEnabled(true);
                btnPlay.setEnabled(false);
                btnVerifikasi.setEnabled(false);
                imgCheck.setVisibility(View.GONE);

                Toast.makeText(BacaActivity.this, "Record start", Toast.LENGTH_SHORT).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioRecorder.stop();
                audioRecorder.release();
                audioRecorder = null;

                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);

                Toast.makeText(BacaActivity.this, "Record success", Toast.LENGTH_SHORT).show();
                btnRecord.setEnabled(true);
                btnVerifikasi.setEnabled(true);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer m = new MediaPlayer();

                try {
                    m.setDataSource(outputFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try {
                    m.prepare();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                m.start();
                Toast.makeText(BacaActivity.this, "Now playing", Toast.LENGTH_SHORT).show();
            }
        });

        btnVerifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCheck.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recordapps/recording.3gp";
                    btnRecord.setEnabled(true);
                } else {
                    btnRecord.setEnabled(false);
                    Toast.makeText(BacaActivity.this, "Write storage denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_ASK_RECORDING:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupMediaRecorder();
                } else {
                    Toast.makeText(BacaActivity.this, "Recording denied", Toast.LENGTH_SHORT).show();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
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

    void setupMediaRecorder() {
        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        audioRecorder.setOutputFile(outputFile);
    }
}
