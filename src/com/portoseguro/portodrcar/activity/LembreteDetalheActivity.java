package com.portoseguro.portodrcar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.constants.PrototipoConstants;
import com.portoseguro.portodrcar.model.Lembrete;
import com.portoseguro.portodrcar.util.FontUtils;
import com.portoseguro.portodrcar.util.FontUtils.FontEnum;

public class LembreteDetalheActivity extends Activity {

	private TextView titulo;
	private TextView descricao;
	private TextView frequencia;
	private TextView frequenciaHeader;
	private Button agendar;
	private Lembrete lembrete;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lembrete_detalhe_activity);
		getActionBar().setTitle("Nao se esqueca!");
        getActionBar().setSubtitle(PrototipoConstants.getApresentacao());
		inicializaVariaveis();
	}
	
	private void inicializaVariaveis() {
		lembrete = (Lembrete) getIntent().getSerializableExtra("lembrete");
		
		frequenciaHeader = (TextView) findViewById(R.id.frequencia_header);
		frequenciaHeader .setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.CHUNKFIVE));
		
		frequencia = (TextView) findViewById(R.id.frequencia);
		frequencia.setText(lembrete.getFrequencia());
		frequencia.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		
		titulo = (TextView) findViewById(R.id.titulo);
		titulo.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.CHUNKFIVE));
		titulo.setText(lembrete.getTitulo());
		
		descricao = (TextView) findViewById(R.id.descricao);
		descricao.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		descricao.setText(lembrete.getDescricao());
		
		agendar = (Button) findViewById(R.id.agendar);
		agendar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), AgendamentoMapaActivity.class);
				i.putExtra("lembrete", lembrete);
				startActivity(i);
				overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
			}
		});
	}

	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}
}
