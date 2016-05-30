package com.ages.event.old;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ages.event.R;
import com.ages.event.model.Noticia;


public class InformacoesActivity extends Activity {

	private TextView txtTitulo;
	private TextView txtTexto ;
	private Noticia noticia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacoes);

		noticia = (Noticia) getIntent().getSerializableExtra("noticia");
		
		txtTitulo = (TextView) findViewById(R.id.txtTitulo);
		txtTexto = (TextView) findViewById(R.id.txtTexto);
		
		txtTitulo.setText(noticia.getTitulo());
		txtTexto.setText(noticia.getTexto());

	}

}
