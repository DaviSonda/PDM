package com.davisonego.cotacoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_BANDEIRA = "bandeira";
    public static final String KEY_NOME = "nome";
    public static final String KEY_COTACAO = "cotacao";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "Exemplo01BD";
    private static final String DATABASE_TABLE = "pais";
    private static final int DATABASE_VERSION = 1;

    private static final String CRIA_DATABASE = "create table pais " +
            "(_id integer primary key autoincrement, " +
            " nome text not null," +
            " bandeira text not null," +
            " cotacao text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context); //classe interna que herda de SQLiteOpenHelper
    }

    //classe interna que manipula o banco
    //SQLiteOpenHelper é uma classe abstrata.
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CRIA_DATABASE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Atualizando a base de dados a partir da versao " + oldVersion
                    + " para " + newVersion + ",isso irá destruir todos os dados antigos");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    // *******************************************************************************
    //--- abre a base de dados ---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //--- fecha a base de dados ---
    public void close() {
        DBHelper.close();
    }

    public long inserePais(String bandeira, String nome, String cotacao) {
        ContentValues dados = new ContentValues();
        dados.put(KEY_BANDEIRA, bandeira);
        dados.put(KEY_NOME, nome);
        dados.put(KEY_COTACAO, cotacao);
        return db.insert(DATABASE_TABLE, null, dados);
    }

    public void mock() {
        Cursor paises = getTodosPaises();
        if (paises.getCount() == 0) {
            ContentValues usa = new ContentValues();
            usa.put(KEY_BANDEIRA, "eua");
            usa.put(KEY_NOME, "Estados Unidos");
            usa.put(KEY_COTACAO, "5,04");
            ContentValues srb = new ContentValues();
            srb.put(KEY_BANDEIRA, "servia");
            srb.put(KEY_NOME, "Servia");
            srb.put(KEY_COTACAO, "0,046");
            ContentValues ger = new ContentValues();
            ger.put(KEY_BANDEIRA, "alemanha");
            ger.put(KEY_NOME, "Alemanha");
            ger.put(KEY_COTACAO, "5,41");
            db.insert(DATABASE_TABLE, null, usa);
            db.insert(DATABASE_TABLE, null, srb);
            db.insert(DATABASE_TABLE, null, ger);
        }
    }

    public Cursor getTodosPaises() {
        String colunas[] = {KEY_ROWID, KEY_BANDEIRA, KEY_NOME, KEY_COTACAO};
        return db.query(DATABASE_TABLE, colunas, null, null, null, null, null);
    }

    public Cursor getByName(String nome) throws SQLException{

        String colunas[] ={KEY_ROWID,KEY_BANDEIRA,KEY_NOME,KEY_COTACAO};
        String linhaAcessada = KEY_NOME + "=" + nome;
        Cursor mCursor = db.query(DATABASE_TABLE, colunas,linhaAcessada,null,null,null,null,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
