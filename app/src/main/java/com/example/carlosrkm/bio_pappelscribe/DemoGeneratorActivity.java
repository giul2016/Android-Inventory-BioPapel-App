package com.example.carlosrkm.bio_pappelscribe;

/**
 * Creado por dakedroid el 17/12/17.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ydcool.lib.qrmodule.encoding.QrGenerator;

public class DemoGeneratorActivity extends AppCompatActivity {
    private static final String TAG = "DemoGeneratorActivity";

    @BindView(R.id.root_scrollview) ScrollView mScrollView;
    @BindView(R.id.edt_size) EditText mEdtSize;
    @BindView(R.id.img_qr_generated) ImageView mImgQrGenerated;
    private String id;
    private String nombre;
    private String departamento;
    private String extension;
    private String direccionip;
    private String marcamonitor;
    private String modelomonitor;
    private String seriemonitor;
    private String marcacpu;
    private String modelocpu;
    private String seriecpu;
    private String marcateclado;
    private String modeloteclado;
    private String serieteclado;
    private String marcamouse;
    private String modelomouse;
    private String seriemouse;


    private ErrorCorrectionLevel mEcc = ErrorCorrectionLevel.L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nombre = intent.getStringExtra("nombre");
        departamento = intent.getStringExtra("departamento");
        extension = intent.getStringExtra("extension");
        direccionip = intent.getStringExtra("direccionip");
        marcamonitor = intent.getStringExtra("marcamonitor");
        modelomonitor = intent.getStringExtra("modelomonitor");
        seriemonitor = intent.getStringExtra("seriemonitor");
        marcacpu = intent.getStringExtra("marcacpu");
        modelocpu = intent.getStringExtra("modelocpu");
        seriecpu = intent.getStringExtra("seriecpu");
        marcateclado = intent.getStringExtra("marcateclado");
        modeloteclado = intent.getStringExtra("modeloteclado");
        serieteclado = intent.getStringExtra("serieteclado");
        marcamouse = intent.getStringExtra("marcamouse");
        modelomouse = intent.getStringExtra("modelomouse");
        seriemouse = intent.getStringExtra("seriemouse");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_generate:
                onGenerateClick();
                return true;
            default:
                return false;
        }
    }

    void onGenerateClick() {
        if (checkEmpty(mEdtSize)) {
            Toast.makeText(DemoGeneratorActivity.this, "Qr Size Required!", Toast.LENGTH_SHORT).show();
        } else {
            try {

                String content = "id: " + id + "\n" + nombre + "\n" + departamento + "\n" + extension + "\n" + direccionip + "\n" + marcamonitor + "\n" + modelomonitor + "\n" + seriemonitor + "\n" + marcacpu + "\n" + modelocpu + "\n" + seriecpu + "\n" + marcateclado + "\n" + modeloteclado + "\n" + serieteclado + "\n" + marcamouse + "\n" + modelomouse + "\n" + seriemouse;
                Bitmap qrCode = new QrGenerator.Builder()
                        .content(content)
                        .qrSize(getInputtedInt(mEdtSize, 400))
                        .color(Color.BLACK)
                        .bgColor(Color.WHITE)
                        .ecc(mEcc)
                        .encode();

                mImgQrGenerated.setImageBitmap(qrCode);
                mScrollView.smoothScrollTo(0, 0);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean checkEmpty(EditText e) {
        return TextUtils.isEmpty(e.getText());
    }

    private int getInputtedInt(EditText edt, int def) {
        try {
            String s = edt.getText().toString();

            if (TextUtils.isEmpty(s))
                return def;
            else
                return Integer.parseInt(s);

        } catch (Exception e) {
            e.printStackTrace();
            return def;
        }
    }

    private <E extends Enum<E>> String[] getEnumNames(Class<E> enumType) {
        EnumSet<E> set = EnumSet.allOf(enumType);

        String[] names = new String[set.size()];

        for (Enum<E> e : set) {
            names[e.ordinal()] = e.toString();
        }

        return names;
    }
}