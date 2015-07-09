package com.portoseguro.portodrcar.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.adapter.ItemGavetaListAdapter;
import com.portoseguro.portodrcar.constants.PrototipoConstants;
import com.portoseguro.portodrcar.fragment.AgendamentosFragment;
import com.portoseguro.portodrcar.fragment.AutoDiagnosticoFragment;
import com.portoseguro.portodrcar.fragment.FaleComPortoFragment;
import com.portoseguro.portodrcar.fragment.LembretesFragment;
import com.portoseguro.portodrcar.fragment.RedePortoSeguroFragment;
import com.portoseguro.portodrcar.fragment.WhatsHotFragment;
import com.portoseguro.portodrcar.model.ItemGaveta;

public class TelaPrincipalActivity extends Activity {
	/** lista que contem os itens da gaveta **/
	private ListView listViewGaveta;
	
	/** layout principal que contém tudo **/
	private DrawerLayout drawerLayoutGaveta;

	/** lista de objetos que carrega o título,icone e contador de cada item do menu **/
	private ArrayList<ItemGaveta> itensGaveta;
	
	/** títulos e ícones **/
	private String[] titulosItensGaveta;
	private TypedArray iconesItensGaveta;
	
	/** adapter do item gaveta **/
	private ItemGavetaListAdapter adapterItemGaveta;
	
	private ActionBarDrawerToggle mDrawerToggle;

	/** título quando acionar a gaveta **/
	private CharSequence tituloGaveta;

	/** título quando fechar a gaveta **/
	private CharSequence tituloAtual;
	
	/** posicao dos itens de fragmento **/
	private int LEMBRETES = 0;
	private int AGENDAMENTOS = 1;
	private int AUTO_DIAGNOSTICO = 2;
	private int REDE_PORTO_SEGURO = 3;
	private int FALE_COM_A_PORTO = 4;
	private int X = 5;	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setTitle(PrototipoConstants.getApresentacao());
		drawerLayoutGaveta = (DrawerLayout) findViewById(R.id.layout_gaveta);
		
		tituloAtual = tituloGaveta = "Porto Doctor Car";

		titulosItensGaveta = getResources().getStringArray(R.array.titulos_itens_gaveta);
		iconesItensGaveta = getResources().obtainTypedArray(R.array.icones_itens_gaveta);

		/** inicializando os itens da gaveta com os títulos e ícones **/
		itensGaveta = new ArrayList<ItemGaveta>();
		itensGaveta.add(new ItemGaveta(titulosItensGaveta[LEMBRETES], iconesItensGaveta.getResourceId(LEMBRETES, -1)));
		itensGaveta.add(new ItemGaveta(titulosItensGaveta[AGENDAMENTOS], iconesItensGaveta.getResourceId(AGENDAMENTOS, -1)));
		itensGaveta.add(new ItemGaveta(titulosItensGaveta[AUTO_DIAGNOSTICO], iconesItensGaveta.getResourceId(AUTO_DIAGNOSTICO, -1)));
		itensGaveta.add(new ItemGaveta(titulosItensGaveta[REDE_PORTO_SEGURO], iconesItensGaveta.getResourceId(REDE_PORTO_SEGURO, -1)));
		itensGaveta.add(new ItemGaveta(titulosItensGaveta[FALE_COM_A_PORTO], iconesItensGaveta.getResourceId(FALE_COM_A_PORTO, -1)));
//		itensGaveta.add(new ItemGaveta(titulosItensGaveta[5], iconesItensGaveta.getResourceId(5, -1)));
		
		// Recycle the typed array
		iconesItensGaveta.recycle();
		
		/** criando o adapter com a lista dos itens da gaveta **/
		adapterItemGaveta = new ItemGavetaListAdapter(getApplicationContext(),itensGaveta);
		/** adicionando listener p/ caso um item da lista seja clicado e os itens da gaveta **/
		listViewGaveta = (ListView) findViewById(R.id.list_view_gaveta);
		listViewGaveta.setOnItemClickListener(new SlideMenuClickListener());
		listViewGaveta.setAdapter(adapterItemGaveta);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(
			this, 
			drawerLayoutGaveta,
			R.drawable.ic_drawer,
			R.string.app_name,
			R.string.app_name) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(tituloAtual);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(tituloGaveta);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		drawerLayoutGaveta.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			/** na primeira vez, deixa carregado o 0 **/
			atualizaFragmento(0);
		}
	}

	/**
	 * Slide menu item click listener para atualizar o item selecionado
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			atualizaFragmento(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = drawerLayoutGaveta.isDrawerOpen(listViewGaveta);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void atualizaFragmento(int position) {
		// update the main content by replacing fragments
		Fragment fragmento = null;
		switch (position) {
		case 0:
			fragmento = new LembretesFragment();
			break;
		case 1:
			fragmento = new AgendamentosFragment();
			break;
		case 2:
			fragmento = new AutoDiagnosticoFragment();
			break;
		case 3:
			fragmento = new RedePortoSeguroFragment();
			break;
		case 4:
			fragmento = new FaleComPortoFragment();
			break;
		case 5:
			fragmento = new WhatsHotFragment();
			break;

		default:
			break;
		}

		if (fragmento != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_atual, fragmento).commit();

			// atualiza o item selecionado e o título, então fecha a gaveta
			listViewGaveta.setItemChecked(position, true);
			listViewGaveta.setSelection(position);
			setTitle(titulosItensGaveta[position]);
			drawerLayoutGaveta.closeDrawer(listViewGaveta);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		tituloAtual = title;
		getActionBar().setTitle(tituloAtual);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
