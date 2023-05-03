package com.zampieri.base_dados_01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long id=0;
        Cursor cursor=null;
        boolean alterou=false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapter db = new DBAdapter(this);

//********************************
//--- adiciona livros---
        db.open();
        id = db.insereLivro("Android para Programadores","Ed. Bookman", "978-85-407-0210-3");
        id = db.insereLivro("Manual de Produção de Jogos Digitais", "Ed. Bookman", "978-85-407-0183-0");
        id = db.insereLivro("C++ Absoluto", "Ed. Pearson", "85-88639-09-2");
        id = db.insereLivro("titulo", "editora", "isbn");
        db.close();

//********************************
//--- obtém todos os livros ---
        db.open();
        cursor = db.getTodosTitulos();
        if (cursor.moveToFirst() == true){
            do{
                mostraLivro(cursor);
            }while (cursor.moveToNext());
        }
        db.close();
//*******************************************************************
//--- obtém um livro ---
        db.open();
        cursor = db.getLivro(2); //objeto c declarado
        if (cursor.moveToFirst())
            mostraLivro(cursor);
        else
            Toast.makeText(this, "Livro nao encontrado!", Toast.LENGTH_LONG).show();
        db.close();
//*******************************************************************
        //--- exclui um livro ---
     /*   db.open();
        if (db.excluiTitulo(1))
            Toast.makeText(this, "Exclusao OK!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Nao foi possivel excluir!!",Toast.LENGTH_LONG).show();
        db.close();
    */
//*******************************************************************
        //--- altera um livro ---
        db.open();
        alterou = db.alteraTitulo(4,"Java - Como Programar","Ed. Bookman","85-7307-727-1");
        if (alterou == true)
            Toast.makeText(this, "Alteracao OK!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Nao foi possivel alterar!!",Toast.LENGTH_LONG).show();

        //--- Consulta o livro alterado ---
        cursor = db.getLivro(4);
        if (cursor.moveToFirst())
            mostraLivro(cursor);
        else
            Toast.makeText(this, "Livro nao encontrado!!",Toast.LENGTH_LONG).show();
        //-------------------
        db.close();
    }
    public void mostraLivro(Cursor cursor){
        String livro = "Id: " + cursor.getString(0) + "\n";
        livro = livro + "Título: " + cursor.getString(1) + "\n";
        livro = livro + "Editora: " +cursor.getString(2) + "\n";
        livro = livro + "ISBN: " + cursor.getString(3) + "\n";

        Toast.makeText(this, livro, Toast.LENGTH_LONG).show();
    }
}