package com.portoseguro.portodrcar.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.R.layout;
import com.portoseguro.portodrcar.model.Agendamento;
import com.portoseguro.portodrcar.model.Lembrete;
import com.portoseguro.portodrcar.task.AgendamentoTask;
import com.portoseguro.portodrcar.util.FontUtils;
import com.portoseguro.portodrcar.util.FontUtils.FontEnum;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AgendamentoDetalheActivity extends Activity {

	private Agendamento agendamento;
	
	private TextView titulo;
	private TextView descricao;
	
	private TextView dadosAgendamento;
	
	private TextView estabelecimentoValor;
	private TextView enderecoValor;
	private TextView telefoneValor;
	private TextView dataValor;
	private TextView horarioValor;

	private Button cancelarAgendamento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agendamento_detalhe_activity);
		inicializaVariaveis();
	}

	private void inicializaVariaveis() {
		titulo = (TextView) findViewById(R.id.titulo);
		titulo.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.CHUNKFIVE));
		
		descricao = (TextView) findViewById(R.id.descricao);
		descricao.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		
		dadosAgendamento = (TextView) findViewById(R.id.dados_agendamento);
		dadosAgendamento.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.CHUNKFIVE));
		
		estabelecimentoValor = (TextView) findViewById(R.id.estabelecimento_valor);
		estabelecimentoValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		enderecoValor = (TextView) findViewById(R.id.endereco_valor);
		enderecoValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		telefoneValor = (TextView) findViewById(R.id.telefone_valor);
		telefoneValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		dataValor = (TextView) findViewById(R.id.data_valor);
		dataValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		horarioValor = (TextView) findViewById(R.id.horario_valor);
		horarioValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		
		agendamento = (Agendamento) getIntent().getSerializableExtra("agendamento");
		cancelarAgendamento = (Button) findViewById(R.id.cancelar_agendamento);
		/**cancelarAgendamento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamaServicoAgendamento();
			}
		});**/
		
		if (agendamento != null){
			titulo.setText(agendamento.getTitulo());
			descricao.setText(agendamento.getDescricao());
			estabelecimentoValor.setText("Local: " + agendamento.getNomeEstabelecimento());
			enderecoValor.setText("Endereco: " + agendamento.getEnderecoEstabelecimento());
			telefoneValor.setText("Telefone: " + agendamento.getTelefoneEstabelecimento());
			dataValor.setText("Data: " + new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getDataAgendamento()));
			horarioValor.setText("Horario: " + new SimpleDateFormat("HH:mm").format(agendamento.getDataAgendamento()));
		}
	}
	
	/**private void chamaServicoAgendamento() {
		try {
			String [] parametros = new String[6];
			parametros[0] = "Fabiano Yoschitaki";
			parametros[1] = "Palio";
			parametros[2] = "Trocar Pneus";
			parametros[3] = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
			parametros[4] = "Oficina do Ze";
			parametros[5] = "Rua Logo Ali";
			new AgendamentoTask(this).execute(parametros);
		} catch (Exception e){
			Toast.makeText(getBaseContext(), "Erro ao chamar servicos:" + e, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}**/
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}
}
