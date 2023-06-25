package com.davisonego.petshop;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CAMERA = 2;

    private ImageView imageView;
    private Button captureButton;

    private ActivityResultLauncher<Intent> cameraLauncher;

    String tipo = "1"; //1 = Perdido/Achado, 2 = Petshop
    String tit;
    String b64img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tipo = extras.getString("tipo").toString();
        }

        imageView = findViewById(R.id.imageView);
        captureButton = findViewById(R.id.captureButton);
        Spinner spnTitulo = findViewById(R.id.spnTit);
        EditText txtContato = findViewById(R.id.txtCont);
        EditText txtDesc = findViewById(R.id.txtDesc);

        if (tipo.equals("1")) {
            spnTitulo.setVisibility(View.VISIBLE);
        } else {
            spnTitulo.setVisibility(View.INVISIBLE);
            tit = "Para Adoçao";
        }

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(imageBitmap);
                    saveImageToGallery(imageBitmap);
                    convertToBase64(imageBitmap);
                }
            }
        });

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddActivity.this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    ActivityCompat.requestPermissions(AddActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
                }
            }
        });

        Button button = findViewById(R.id.btnSbmt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipo.equals("1")) {
                    tit = spnTitulo.getSelectedItem().toString();
                }
                String cont = txtContato.getText().toString();
                String desc = txtDesc.getText().toString();
                Pet pet = new Pet(tit, desc, b64img, tipo, cont);

                // Perform operations with the input text

                // Example: Display a toast message with the input values
//                Toast.makeText(AddActivity.this, pet.toString(), Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Titulo", pet.getTitulo());
                    jsonObject.put("Descricao", pet.getDescricao());
                    jsonObject.put("Tipo_pub", pet.getTipo());
                    jsonObject.put("Metodo_Contato", pet.getContato());
                    jsonObject.put("Img", pet.getImg());
                    String jsonString = jsonObject.toString();
                    new PostData().execute(jsonString);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            cameraLauncher.launch(takePictureIntent);
        }
    }

    private void saveImageToGallery(Bitmap imageBitmap) {
        String imageFileName = "petfoto";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, imageFileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try {
            getContentResolver().openOutputStream(uri).close();
            getContentResolver().update(uri, values, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void convertToBase64(Bitmap imageBitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        b64img = Base64.encodeToString(byteArray, Base64.DEFAULT);

        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Permissão de câmera negada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class PostData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                // on below line creating a url to post the data.
                URL url = new URL(Globals.url + "/posts");
//                URL url = new URL("https://semente.requestcatcher.com/test");

                // on below line opening the connection.
                HttpURLConnection client = (HttpURLConnection) url.openConnection();

                // on below line setting method as post.
                client.setRequestMethod("POST");

                // on below line setting content type and accept type.
                client.setRequestProperty("Content-Type", "application/json");
                client.setRequestProperty("Accept", "application/json");

                // on below line setting client.
                client.setDoOutput(true);

                // on below line we are creating an output stream and posting the data.
                try (OutputStream os = client.getOutputStream()) {
                    byte[] input = strings[0].getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // on below line creating and initializing buffer reader.
                try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"))) {
                }

            } catch (Exception e) {

                // on below line handling the exception.
                e.printStackTrace();
                Toast.makeText(AddActivity.this, "Fail to post the data : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }
}