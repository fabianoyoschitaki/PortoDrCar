package com.portoseguro.portodrcar.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.enums.ImportanciaLembreteEnum;
import com.portoseguro.portodrcar.model.Lembrete;
import com.portoseguro.portodrcar.util.FontUtils;
import com.portoseguro.portodrcar.util.FontUtils.FontEnum;

public class LembreteAdapter extends ArrayAdapter<Lembrete>{
	
	private Activity context;
	private List<Lembrete> lembretes;
	
	public LembreteAdapter(Activity context, int resource, List<Lembrete> objects) {
		super(context, resource, objects);
		this.context = context;
		this.lembretes = objects;
	}
	
	@Override
	public int getCount() {
		return super.getCount();
	}

	@Override
	public Lembrete getItem(int position) {
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
		ImageView greater;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(R.layout.lembrete_item, null);
			// configure view holder
			ViewHolder holder = new ViewHolder();
			holder.imagem = (ImageView) convertView.findViewById(R.id.imagem);
			holder.titulo = (TextView) convertView.findViewById(R.id.titulo);
			holder.descricao = (TextView) convertView.findViewById(R.id.descricao);
			holder.greater = (ImageView) convertView.findViewById(R.id.greater);
			convertView.setTag(holder);
	    }

	    // fill data
	    ViewHolder holder = (ViewHolder) convertView.getTag();
	    
	    Lembrete lembrete = getItem(position);
	    if (lembrete.getImportancia().equals(ImportanciaLembreteEnum.BAIXA)){
	    	holder.imagem.setImageResource(R.drawable.tipo_lembrete_manutencao_azul);
	    } else if (lembrete.getImportancia().equals(ImportanciaLembreteEnum.MEDIA)){
	    	holder.imagem.setImageResource(R.drawable.tipo_lembrete_manutencao_verde);
	    } else if (lembrete.getImportancia().equals(ImportanciaLembreteEnum.ALTA)){
	    	holder.imagem.setImageResource(R.drawable.tipo_lembrete_manutencao_amarelo);
	    } else if (lembrete.getImportancia().equals(ImportanciaLembreteEnum.URGENTE)){
	    	holder.imagem.setImageResource(R.drawable.tipo_lembrete_manutencao_vermelho);
	    } 
	    holder.titulo.setText(lembrete.getTitulo());
	    holder.titulo.setTypeface(FontUtils.getFont(getContext(), FontEnum.CHUNKFIVE));
	    holder.descricao.setTypeface(FontUtils.getFont(getContext(), FontEnum.POPCORN_MOUNTAIN));
		holder.descricao.setText(lembrete.getDescricao());
		holder.greater.setImageResource(R.drawable.greater);
		
	    return convertView;
	}

	public List<Lembrete> getItems() {
        return lembretes;
    }
}
