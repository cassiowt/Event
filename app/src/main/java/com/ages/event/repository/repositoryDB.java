package com.ages.event.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ages.event.util.Constantes;

/**
 * Created by cassio on 09/05/2016.
 */
public class RepositoryDB extends SQLiteOpenHelper {


    public RepositoryDB(Context context) {
       super(context, Constantes.BD_HOME, null, Constantes.BD_VERSION);
        SQLiteDatabase db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_LOGIN ( ");
        query.append(" ID_LOGIN INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" USUARIO TEXT(15) NOT NULL,");
        query.append(" SENHA TEXT(15) NOT NULL )");

        db.execSQL(query.toString());

        popularDB(db);

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
        query.append(" DT_ALTERACAO INTERGER NOT NULL)");
        db.execSQL(query.toString());
    }

    public void popularDB(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO TB_LOGIN(USUARIO, SENHA)");
        query.append("VALUES (?,?)");

        String[] params = {"admin", "admin"};
        db.execSQL(query.toString(), params);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        StringBuilder query = new StringBuilder();


    }
}
