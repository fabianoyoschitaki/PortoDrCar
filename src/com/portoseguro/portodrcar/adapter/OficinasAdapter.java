package com.portoseguro.portodrcar.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.model.Oficina;
import com.portoseguro.portodrcar.util.FontUtils;
import com.portoseguro.portodrcar.util.FontUtils.FontEnum;

public class OficinasAdapter extends ArrayAdapter<Oficina>{
	private Activity context;
	private List<Oficina> oficinas;
	
	public OficinasAdapter(Activity context, int resource, List<Oficina> objects) {
		super(context, resource, objects);
		this.context = context;
		this.oficinas = objects;
	}
	
	@Override
	public int getCount() {
		return super.getCount();
	}

	@Override
	public Oficina getItem(int position) {
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

	private class ViewHolder {
		ImageView imagem;
		TextView titulo;
		TextView descricao;
		Button greater;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(R.layout.oficina_item, null);
			// configure view holder
			ViewHolder holder = new ViewHolder();
			holder.imagem = (ImageView) convertView.findViewById(R.id.imagem);
			holder.titulo = (TextView) convertView.findViewById(R.id.titulo);
			holder.descricao = (TextView) convertView.findViewById(R.id.descricao);
			holder.greater = (Button) convertView.findViewById(R.id.btLinha);
			convertView.setTag(holder);
	    }

	    // fill data
	    ViewHolder holder = (ViewHolder) convertView.getTag();
	    
	    Oficina oficina = getItem(position);
	    holder.imagem.setImageResource(R.drawable.oficina);
	    holder.titulo.setText(oficina.getDescricao());
	    holder.titulo.setTypeface(FontUtils.getFont(getContext(), FontEnum.CHUNKFIVE));
	    holder.descricao.setTypeface(FontUtils.getFont(getContext(), FontEnum.POPCORN_MOUNTAIN));
		holder.descricao.setText(oficina.getEndereco());
		holder.greater = (Button) convertView.findViewById(R.id.btLinha);
		
	    return convertView;
	}

	public List<Oficina> getItems() {
        return oficinas;
    }
}
