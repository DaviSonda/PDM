package com.davisonego.orcamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_DESC = "descricao";
    public static final String KEY_FK_CATEGORIA = "id_categoria";
    public static final String KEY_VALOR = "valor";
    public static final String KEY_PAGAMENTO = "pagamento";
    public static final String KEY_OBS = "obs";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "DB";
    private static final String DATABASE_TABLE_CATEGORIA = "categoria";
    private static final String DATABASE_TABLE_PRODUTO = "produto";
    private static final int DATABASE_VERSION =1;

    private static final String CRIA_DATABASE_CATEGORIA = "create table categoria " +
            "(_id integer primary key autoincrement, " +
            " descricao text not null);" ;

    private static final String CRIA_DATABASE_PRODUTO = "create table produto " +
            "(_id integer primary key autoincrement, " +
            " id_categoria integer not null," +
            " valor text not null," +
            " pagamento text not null," +
            " obs text not null, " +
            " FOREIGN key (id_categoria) references categoria(id))";

    private final Context contexto;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.contexto = ctx;
        DBHelper = new DatabaseHelper(contexto); //classe interna que herda de SQLiteOpenHelper
    }


    //Classe interna que manipula o banco
    //SQLiteOpenHelper é uma classe abstrata.
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(CRIA_DATABASE_CATEGORIA);
                db.execSQL(CRIA_DATABASE_PRODUTO);
                System.out.println("DROPMABABE");
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
            Log.w(TAG, "Atualizando a base de dados a partir da versao " + oldVersion
                    + " para " + newVersion + ",isso irá destruir todos os dados antigos");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CATEGORIA);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PRODUTO);
            onCreate(db);
        }
    }

    // *******************************************************************************
    //--- abre a base de dados ---
    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //--- fecha a base de dados ---
    public void close(){
        DBHelper.close();
    }

    //insere uma nova categoria
    public long insereCategoria(String desc){
        ContentValues dados = new ContentValues();
        dados.put(KEY_DESC, desc);
        return db.insert(DATABASE_TABLE_CATEGORIA, null, dados);
    }

    public boolean excluiCategoria(long idLinha) {
        return db.delete(DATABASE_TABLE_CATEGORIA, KEY_ROWID + "=" + idLinha, null) > 0;
    }

    public Cursor getTodasCategorias(){
        String colunas[] = {KEY_ROWID, KEY_DESC};
        return db.query(DATABASE_TABLE_CATEGORIA, colunas, null, null, null, null, null);
    }

    //---insere um Livro na base da dados ---
    public long insereProduto(long idCat, Integer valor, Integer pag, String obs){
        ContentValues dados = new ContentValues();
        dados.put(KEY_FK_CATEGORIA, idCat);
        dados.put(KEY_VALOR, valor);
        dados.put(KEY_PAGAMENTO, pag);
        dados.put(KEY_OBS, obs);
        return db.insert(DATABASE_TABLE_PRODUTO, null, dados);
    }


    //--- devolve todos os livros---
    public Cursor getTodosProdutosDaCategoria(long idCat){
        String colunas[] ={KEY_ROWID,KEY_FK_CATEGORIA,KEY_VALOR,KEY_PAGAMENTO, KEY_OBS};
        String where = KEY_FK_CATEGORIA + "=" + idCat;
        return db.query(DATABASE_TABLE_PRODUTO,colunas, where, null, null, null, null);
    }
}
