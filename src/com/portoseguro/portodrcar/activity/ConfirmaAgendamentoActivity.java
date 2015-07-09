package com.portoseguro.portodrcar.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.constants.PrototipoConstants;
import com.portoseguro.portodrcar.model.Lembrete;
import com.portoseguro.portodrcar.model.Oficina;
import com.portoseguro.portodrcar.task.AgendamentoTask;
import com.portoseguro.portodrcar.util.FontUtils;
import com.portoseguro.portodrcar.util.FontUtils.FontEnum;

public class ConfirmaAgendamentoActivity extends Activity {

	private Oficina oficina;
	private Lembrete lembrete;
	
	private TextView titulo;
	private TextView descricao;
	private TextView dadosAgendamento;
	
	private TextView estabelecimentoValor;
	private TextView enderecoValor;
	private TextView telefoneValor;
	private TextView dataValor;
	private TextView horarioValor;

	private Button confirmarAgendamento;
	
	private int ano;
	private int mes;
	private int dia;
	private int hora;
	private int minuto;
	
	Calendar data;
	
	private boolean escolheuData = false;
	private boolean escolheuHorario = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirma_agendamento);
		getActionBar().setTitle("Confirme seu agendamento");
        getActionBar().setSubtitle(PrototipoConstants.getApresentacao());
		inicializaVariaveis();
	}

	private void inicializaVariaveis() {
		confirmarAgendamento = (Button) findViewById(R.id.confirmar_agendamento);
		confirmarAgendamento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (escolheuData && escolheuHorario){
					chamaServicoAgendamento();
				} else {
					Toast.makeText(getApplicationContext(), "Escolha data e horario primeiro", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		titulo = (TextView) findViewById(R.id.titulo);
		titulo.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.CHUNKFIVE));
		
		descricao = (TextView) findViewById(R.id.descricao);
		descricao.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		
		dadosAgendamento = (TextView) findViewById(R.id.dados_agendamento);
		dadosAgendamento.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.CHUNKFIVE));
		
		dataValor = (TextView) findViewById(R.id.valor_data);
		dataValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		dataValor.setText("Clique aqui para selecionar data");
		dataValor.setTextColor(Color.BLUE);
		dataValor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				escolheDatePicker();
			}
		});
		
		horarioValor = (TextView) findViewById(R.id.valor_horario);
		horarioValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		horarioValor.setText("Clique aqui para selecionar horario");
		horarioValor.setTextColor(Color.BLUE);
		horarioValor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				escolheHorarioPicker();
			}
		});
		
		estabelecimentoValor = (TextView) findViewById(R.id.valor_estabelecimento);
		estabelecimentoValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		enderecoValor = (TextView) findViewById(R.id.valor_endereco);
		enderecoValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		telefoneValor = (TextView) findViewById(R.id.valor_telefone);
		telefoneValor.setTypeface(FontUtils.getFont(getApplicationContext(), FontEnum.POPCORN_MOUNTAIN));
		
		oficina = (Oficina) getIntent().getSerializableExtra("oficina");
		lembrete = (Lembrete) getIntent().getSerializableExtra("lembrete");
		
		if (oficina != null && lembrete != null){
			descricao.setText(lembrete.getTitulo());
			estabelecimentoValor.setText("Local: " + oficina.getDescricao());
			enderecoValor.setText("Endereco: " + oficina.getEndereco());
			telefoneValor.setText("Telefone: " + oficina.getTelefone());
			//dataValor.setText("Data: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			//horarioValor.setText("Horario: " + new SimpleDateFormat("HH:mm").format(new Date()));
		}
	}
	
	private void escolheHorarioPicker(){
		TimePickerDialog tpd = new TimePickerDialog(this,
		        new TimePickerDialog.OnTimeSetListener() {
		 
		            @Override
		            public void onTimeSet(TimePicker view, int hora,
		                    int minuto) {
		                horarioValor.setText("Horario: " + hora + ":" + (minuto < 10 ? "0" + minuto : minuto) + " (alterar)");
		            }
		        }, hora, minuto, true);
		tpd.show();
		escolheuHorario = true;
	}
	private void escolheDatePicker() {
		final Calendar c = Calendar.getInstance();
		ano = c.get(Calendar.YEAR);
		mes = c.get(Calendar.MONTH);
		dia = c.get(Calendar.DAY_OF_MONTH);
		 
		DatePickerDialog dpd = new DatePickerDialog(this,
		        new DatePickerDialog.OnDateSetListener() {
		 
		            @Override
		            public void onDateSet(DatePicker view, int ano,
		                    int mes, int dia) {
		            	dataValor.setText("Data: " + dia + "/" + (mes + 1) + "/" + ano + " (alterar)");
		            }
		        }, ano, mes, dia);
		dpd.show();
		escolheuData = true;
	}

	
	private void chamaServicoAgendamento() {
		try {
			String [] parametros = new String[6];
			parametros[0] = PrototipoConstants.USUARIO_LOGADO;
			parametros[1] = PrototipoConstants.PLACA;
			parametros[2] = lembrete.getTitulo();
			data = Calendar.getInstance();
			data.set(Calendar.YEAR, ano);
			data.set(Calendar.MONTH, mes);
			data.set(Calendar.DAY_OF_MONTH, dia);
			data.set(Calendar.HOUR_OF_DAY, hora);
			data.set(Calendar.MINUTE, minuto);
			
			parametros[3] = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data.getTime());
			parametros[4] = oficina.getDescricao();
			parametros[5] = oficina.getEndereco();
			new AgendamentoTask(this).execute(parametros);
		} catch (Exception e){
			Toast.makeText(getBaseContext(), "Erro ao chamar servicos:" + e, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	
	public void onAgendamentoSucess(){
		Toast.makeText(getApplicationContext(), "Agendamento realizado com sucesso!", Toast.LENGTH_SHORT).show();
		PrototipoConstants.transformaLembreteEmAgendamento(lembrete, oficina, data);
		confirmarAgendamento.setText("Voltar");
		confirmarAgendamento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ConfirmaAgendamentoActivity.this.startActivity(new Intent(ConfirmaAgendamentoActivity.this, TelaPrincipalActivity.class));
				overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
				ConfirmaAgendamentoActivity.this.finish();
			}
		});
	}
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}
}