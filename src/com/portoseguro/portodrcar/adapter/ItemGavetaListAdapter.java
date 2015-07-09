package com.portoseguro.portodrcar.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.model.ItemGaveta;

/**
 * Classe responsável por tratar os itens da gaveta
 * controla como o item é exibido
 * 
 * @author fabiano
 *
 */
public class ItemGavetaListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ItemGaveta> itensGaveta;

	public ItemGavetaListAdapter(
			Context context,
			ArrayList<ItemGaveta> itensGaveta) {
		this.context = context;
		this.itensGaveta = itensGaveta;
	}

	@Override
	public int getCount() {
		return itensGaveta.size();
	}

	@Override
	public Object getItem(int position) {
		return itensGaveta.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.layout_item_gaveta, null);
		}

		ImageView icone = (ImageView) convertView.findViewById(R.id.icone);
		TextView titulo = (TextView) convertView.findViewById(R.id.titulo);
		TextView counter = (TextView) convertView.findViewById(R.id.counter);

		/** atualiza os valores p/ as views **/
		icone.setImageResource(itensGaveta.get(position).getIcone());
		titulo.setText(itensGaveta.get(position).getTitulo());

		/** mostra o contador **/
		// check whether it set visible or not
		if (itensGaveta.get(position).getCounterVisibility()) {
			counter.setText(itensGaveta.get(position).getCount());
		} else {
			// hide the counter view
			counter.setVisibility(View.GONE);
		}

		return convertView;
	}

}
