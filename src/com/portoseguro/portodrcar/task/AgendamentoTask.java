package com.portoseguro.portodrcar.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.portoseguro.portodrcar.activity.ConfirmaAgendamentoActivity;

public class AgendamentoTask extends AsyncTask<String, Void, String>{

	private static final String TAG = AgendamentoTask.class.getName();
	
	private static String URL_AMAZON_SERVICO_AGENDAMENTO = "http://54.94.132.86/Home/RegistrarAgendamento?";
	private static String PARAMETRO_NOME = "Nome";
	private static String PARAMETRO_CARRO = "Carro";
	private static String PARAMETRO_SERVICO = "Servico";
	private static String PARAMETRO_HORARIO_AGENDAMENTO = "Horario";
	private static String PARAMETRO_NOME_ESTABELECIMENTO = "NomeEstabelecimento";
	private static String PARAMETRO_ENDERECO_ESTABELECIMENTO = "EnderecoEstabelecimento";

	ProgressDialog dialog;
	ConfirmaAgendamentoActivity activity;
	
	public AgendamentoTask(ConfirmaAgendamentoActivity activity){
		this.activity = activity;
	}

	
	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(activity);
		dialog.setMessage("Realizando agendamento...");
		dialog.show();
	}

	@Override
	protected void onPostExecute(String resultadoAgendamento) {
		if (resultadoAgendamento != null) {
			activity.onAgendamentoSucess();
		}
		dialog.dismiss();
	}

	@Override
	protected String doInBackground(String... params) {
		String retorno = "";
		try {
			List<NameValuePair> chaveValor = new ArrayList<NameValuePair>(1);
			
			chaveValor.add(new BasicNameValuePair(PARAMETRO_NOME, params[0]));
			chaveValor.add(new BasicNameValuePair(PARAMETRO_CARRO, params[1]));
			chaveValor.add(new BasicNameValuePair(PARAMETRO_SERVICO, params[2]));
			chaveValor.add(new BasicNameValuePair(PARAMETRO_HORARIO_AGENDAMENTO, params[3]));
			chaveValor.add(new BasicNameValuePair(PARAMETRO_NOME_ESTABELECIMENTO, params[4]));
			chaveValor.add(new BasicNameValuePair(PARAMETRO_ENDERECO_ESTABELECIMENTO, params[5]));
			
			String parametrosString = URLEncodedUtils.format(chaveValor, "utf-8");
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(URL_AMAZON_SERVICO_AGENDAMENTO + parametrosString);
			Log.d(TAG, URL_AMAZON_SERVICO_AGENDAMENTO + parametrosString);
			HttpResponse response = httpclient.execute(httpGet);
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String linha;
			while ((linha = br.readLine()) != null) {
				sb.append(linha);
			}
			br.close();

			linha = sb.toString();

			retorno = linha;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
