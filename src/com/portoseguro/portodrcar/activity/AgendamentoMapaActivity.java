package com.portoseguro.portodrcar.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.R.anim;
import com.portoseguro.portodrcar.R.id;
import com.portoseguro.portodrcar.R.layout;
import com.portoseguro.portodrcar.adapter.OficinasAdapter;
import com.portoseguro.portodrcar.constants.PrototipoConstants;
import com.portoseguro.portodrcar.model.Lembrete;
import com.portoseguro.portodrcar.model.Oficina;

public class AgendamentoMapaActivity extends Activity {

	private WebView myWebView;
	private Bundle webViewBundle;
	private Lembrete lembrete;
	private Button clickedButton;

	private ListView oficinaslistView;
	private OficinasAdapter oficinaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agendamento_mapa);
		
		getActionBar().setTitle("Escolha uma oficina");
        getActionBar().setSubtitle(PrototipoConstants.getApresentacao());
		
		lembrete = (Lembrete) getIntent().getSerializableExtra("lembrete");
		
		class GeoWebChromeClient extends WebChromeClient {
			@Override
			public void onGeolocationPermissionsShowPrompt(String origin,
				GeolocationPermissions.Callback callback) {
				callback.invoke(origin, true, false);
			}
		}
		
		class WebViewCallback {
			Context mContext;

			/** Instantiate the interface and set the context */
			WebViewCallback(Context c) {
				mContext = c;
			}

			/** Show a toast from the web page */
			@JavascriptInterface
			public void showToast(String toast) {
				Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
			}
		}
		
		myWebView = (WebView) findViewById(R.id.webview001);

		if (webViewBundle == null) {
			Log.d("SOMETHING", "novo");
			myWebView.loadUrl("file:///android_asset/html/mapa/mapa.html");
		} else {
			Log.d("SOMETHING", "usado");
			myWebView.restoreState(webViewBundle);
		}

		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setGeolocationEnabled(true);
		myWebView.setWebChromeClient(new GeoWebChromeClient());
		myWebView.addJavascriptInterface(new WebViewCallback(this), "Android");
		
		this.oficinaAdapter = new OficinasAdapter(this, R.id.postos_list, PrototipoConstants.getOficinas());
		this.oficinaslistView = (ListView) findViewById(R.id.postos_list);
		this.oficinaslistView.setAdapter(oficinaAdapter);
		
		/** quando clicar em um item da lista, position inicia de 0 **/
		
		this.oficinaslistView.setOnItemClickListener(new OnItemClickListener()
		{
		    @Override 
		    public void onItemClick(AdapterView<?> arg0, View view,int position, long arg3)
		    { 
		    	final Oficina oficina = PrototipoConstants.getOficinas().get(position);
		    	Integer cod = oficina.getId();
		    	String desc = oficina.getDescricao();
		    	String lat = oficina.getLatitude();
		    	String lon = oficina.getLongitude();
		    	String tel = oficina.getTelefone();
		    	String end = oficina.getEndereco();
		    	myWebView.loadUrl("javascript:selecionaPosto('"+cod+"','"+desc+"','"+lat+"','"+lon+"','"+tel+"','"+end+"')");
		    	
		    	if(clickedButton != null){
		    		clickedButton.setVisibility(View.INVISIBLE);
		    	}
		    	
		    	Button btLinha = (Button) view.findViewById(R.id.btLinha);
		    	btLinha.setVisibility(View.VISIBLE);
		    	clickedButton = btLinha;

		    	btLinha.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Intent myIntent = new Intent(getApplicationContext(), ConfirmaAgendamentoActivity.class);
				    	myIntent.putExtra("oficina", oficina);
				    	myIntent.putExtra("lembrete", lembrete);
				    	startActivity(myIntent);
					}
				});
		    	
//		    	Intent myIntent = new Intent(getActivity(), LembreteDetalheActivity.class);
//		    	myIntent.putExtra("lembrete", lembretes.get(position));
//                getActivity().startActivity(myIntent); 
//                getActivity().overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
		    }
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}
}
