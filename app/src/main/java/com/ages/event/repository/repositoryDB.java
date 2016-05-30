package com.ages.event.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.support.v7.widget.LinearSmoothScroller;

import com.ages.event.model.Noticia;
import com.ages.event.util.Constantes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cassio on 09/05/2016.
 */
public class RepositoryDB extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public RepositoryDB(Context context) {
       super(context, Constantes.BD_HOME, null, Constantes.BD_VERSION);
        SQLiteDatabase db = getReadableDatabase();
    }

    public RepositoryDB open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_LOGIN ( ");
        query.append(" ID_LOGIN INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" USUARIO TEXT(15) NOT NULL,");
        query.append(" SENHA TEXT(15) NOT NULL )");

        db.execSQL(query.toString());

        // TABELA para cadastro do Evento principal, o Evento unico se for o caso.
        query = new StringBuilder();
        query.append("CREATE TABLE TB_EVENTO ( ");
        query.append(" ID_EVENTO INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" SIGLA TEXT(15),");
        query.append(" DESCRICAO TEXT(255),");
        query.append(" DT_INICIO INTERGER NOT NULL,");
        query.append(" DT_FIM INTERGER NOT NULL,");
        query.append(" DT_CADASTRO INTERGER NOT NULL,");
        query.append(" DT_ALTERACAO INTERGER NOT NULL)");
        db.execSQL(query.toString());

    // TABELA para cadastro de Sub_Evento referentes ao um Evento principal.
        query = new StringBuilder();
        query.append("CREATE TABLE TB_SUB_EVENTO ( ");
        query.append(" ID_SUB_EVENTO INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" ID_EVENTO INTEGER NOT NULL, ");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" SIGLA TEXT(15),");
        query.append(" DESCRICAO TEXT(255),");
        query.append(" DT_INICIO INTERGER NOT NULL,");
        query.append(" DT_FIM INTERGER NOT NULL,");
        query.append(" DT_CADASTRO INTERGER NOT NULL,");
        query.append(" DT_ALTERACAO INTERGER NOT NULL)");
        db.execSQL(query.toString());

        // TABELA  para cadastro dos tipos de atividades
        query = new StringBuilder();
        query.append("CREATE TABLE TB_ATIVIDADE ( ");
        query.append(" ID_ATIVIDADE INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" SIGLA TEXT(15),");
        query.append(" TIPO TEXT(15),");
        query.append(" TITULO TEXT(60) NOT NULL,");
        query.append(" DESCRICAO TEXT(255),");
        query.append(" ID_LOCAL INTEGER NOT NULL, ");
        query.append(" ID_PALESTRANTE INTEGER NOT NULL, ");
        query.append(" DATA INTERGER NOT NULL,");
        query.append(" HORA_INICIO INTERGER NOT NULL,");
        query.append(" HORA_FIM INTERGER NOT NULL,");
        query.append(" DT_CADASTRO INTERGER NOT NULL,");
        query.append(" DT_ALTERACAO INTERGER NOT NULL)");
        db.execSQL(query.toString());

        // TABELA para cadastro de locais dos sub_eventos

        query = new StringBuilder();
        query.append("CREATE TABLE TB_LOCAL ( ");
        query.append(" ID_LOCAL INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" PREDIO TEXT(15),");
        query.append(" TIPO TEXT(60) NOT NULL,");
        query.append(" qntLUGARES TEXT(255),");
        query.append(" LOCAL INTEGER NOT NULL, ");
        query.append(" DT_CADASTRO INTERGER NOT NULL,");
        query.append(" DT_ALTERACAO INTERGER NOT NULL)");
        db.execSQL(query.toString());

        // TABELA para cadastro de palestrantes

        query = new StringBuilder();
        query.append("CREATE TABLE TB_PALESTRANTE ( ");
        query.append(" ID_PALESTRANTE INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(50),");
        query.append(" EMAIL TEXT(60) NOT NULL,");
        query.append(" DT_CADASTRO INTERGER NOT NULL,");
        query.append(" DT_ALTERACAO INTERGER NOT NULL)");
        db.execSQL(query.toString());

        // Tabela de notícias
        query = new StringBuilder();
        query.append("CREATE TABLE TB_NOTICIAS ( ");
        query.append(" ID_NOTICIA INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" TITULO TEXT(50),");
        query.append(" TEXTO TEXT(60) NOT NULL,");
        query.append(" DT_CADASTRO INTERGER NOT NULL,");
        query.append(" DT_ALTERACAO INTERGER NOT NULL,");
        query.append(" STATUS TEXT (10) NOT NULL)");
        db.execSQL(query.toString());

        popularDB(db);
    }

    public void popularDB(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO TB_LOGIN(USUARIO, SENHA)");
        query.append("VALUES (?,?)");
        String[] params = {"admin", "admin"};
        db.execSQL(query.toString(), params);

      /*  query = new StringBuilder();
        query.append("INSERT INTO `TB_NOTICIAS` (ID_NOTICIA,TITULO,TEXTO,DT_CADASTRO,DT_ALTERACAO,STATUS)");
        query.append("VALUES (1,'Inicio do Evento','No dia  03/07 o evento inicia com um curso de Qualidade',1462902977,1462902977,'ATIVO'),");
        query.append("(2,'Palestra Incicial','No dia 04/07 teremos a palestra de abertura do CSBC no Teatro de predio 40',1462902977,1462902977,'ATIVO'),");
        query.append("(3,'Troca se Sala','A palestra de IHC das 14:30 será na sala 307 prédio 40',1462902977,1462902977,'ATIVO'),");
        query.append("(4,'Plestrante Internacional','Não perca a palestra de Cloud na sala 211 prédio 40 as 17:00 ',1462902977,1462902977,'ATIVO'),");
        query.append("(5,'Palestra Incicial','No dia 04/07 teremos a palestra de abertura do CSBC no Teatro de predio 40',1462902977,1462902977,'INATIVO')");
        db.execSQL(query.toString());
*/    }
    public long insertNoticia(String id, String titulo, String descricao, String status, long dtCadastro, long dtAlteracao) {

        ContentValues initialValues = new ContentValues();
        initialValues.put("ID_NOTICIA", id);
        initialValues.put("TITULO", titulo);
        initialValues.put("TEXTO", descricao);
        initialValues.put("STATUS", status);
        initialValues.put("DT_CADASTRO", dtCadastro);
        initialValues.put("DT_ALTERACAO", dtAlteracao);

        return db.insert("TB_NOTICIAS", null, initialValues);
    }

    public int deleteAllNoticias(){

        return db.delete("TB_NOTICIAS",null,null);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        StringBuilder query = new StringBuilder();

    }

    public List<Noticia> listarNoricias() {
        List<Noticia>  lista = new ArrayList<Noticia>();

        SQLiteDatabase db = getReadableDatabase();

        String[] params = {"ATIVO"};

        Cursor cursor = db.query("TB_NOTICIAS",null,"STATUS = ?",params,null,null,null);

        while (cursor.moveToNext()){
            Noticia noticia = new Noticia();
            noticia.setIdNoticia(cursor.getInt(cursor.getColumnIndex("ID_NOTICIA")));
            noticia.setTitulo(cursor.getString(cursor.getColumnIndex("TITULO")));
            noticia.setTexto(cursor.getString(cursor.getColumnIndex("TEXTO")));
            long time  =cursor.getLong(cursor.getColumnIndex("DT_CADASTRO"));
            Date dtCadastro = new Date();
            dtCadastro.setTime(time);
            noticia.setDtCadastro(dtCadastro);

            lista.add(noticia);
        }

        return lista;
    }
}
