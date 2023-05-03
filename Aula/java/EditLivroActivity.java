package com.zampieri.base_dados_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditLivroActivity extends AppCompatActivity {
    private long idLinha;
    private TextView lblTitulo;
    private TextView lblEditora;
    private TextView lblISBN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_livro);
       // Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        lblEditora = (TextView) findViewById(R.id.lblEditora);
        lblISBN = (TextView) findViewById(R.id.lblISBN);

        Bundle extras = getIntent().getExtras();
        idLinha = extras.getLong(MainActivity.LINHA_ID);
    }

    @Override
    protected void onResume(){
        super.onResume();
        new CarregaLivroTask().execute(idLinha);
    }

    // Executa a consulta em uma thead separada
    private class CarregaLivroTask extends AsyncTask<Long, Object, Cursor> {
        DBAdapter databaseConnector = new DBAdapter(EditLivroActivity.this);

        @Override
        protected Cursor doInBackground(Long... params){
            databaseConnector.open();
            return databaseConnector.getLivro(params[0]);
        }
        // Usa o Cursor retornado do método doInBackground
        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);

            result.moveToFirst();

            int tituloIndex = result.getColumnIndex("titulo");
            int editoraIndex = result.getColumnIndex("editora");
            int isbnIndex = result.getColumnIndex("isbn");

            lblTitulo.setText(result.getString(tituloIndex));
            lblEditora.setText(result.getString(editoraIndex));
            lblISBN.setText(result.getString(isbnIndex));
            result.close();
            databaseConnector.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consulta_livros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.editItem:
                Intent addEditLivro = new Intent(this, AddNovoLivroActivity.class);

                addEditLivro.putExtra(MainActivity.LINHA_ID, idLinha);
                addEditLivro.putExtra("titulo", lblTitulo.getText());
                addEditLivro.putExtra("editora", lblEditora.getText());
                addEditLivro.putExtra("isbn", lblISBN.getText());

                startActivity(addEditLivro);
                return true;
            case R.id.deleteItem:
                deleteLivro(idLinha);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteLivro(long idLinha){

        AlertDialog.Builder builder = new AlertDialog.Builder(EditLivroActivity.this);

        builder.setTitle(R.string.confirmaTitulo);
        builder.setMessage(R.string.confirmaMensagem);

        // provide an OK button that simply dismisses the dialog
        builder.setPositiveButton(R.string.botao_delete,
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int button){
                        final DBAdapter conexaoDB = new DBAdapter(EditLivroActivity.this);

                        ExecutorService executor = Executors.newSingleThreadExecutor();
                        Handler handler = new Handler(Looper.getMainLooper());

                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                //Background work here
                                conexaoDB.open();
                                conexaoDB.excluiTitulo(idLinha);
                                conexaoDB.close();

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //UI Thread work here
                                        finish(); // Fecha a atividade
                                    }
                                });
                            }
                        });
                    }
                }); // finaliza o  método setPositiveButton

        builder.setNegativeButton(R.string.botao_cancel, null);
        builder.show();
    }
}