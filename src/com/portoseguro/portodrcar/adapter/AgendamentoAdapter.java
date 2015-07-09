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
import com.portoseguro.portodrcar.model.Agendamento;
import com.portoseguro.portodrcar.model.Lembrete;
import com.portoseguro.portodrcar.util.FontUtils;
import com.portoseguro.portodrcar.util.FontUtils.FontEnum;

public class AgendamentoAdapter extends ArrayAdapter<Agendamento>{
	
	private Activity context;
	private List<Agendamento> agendamentos;
	
	public AgendamentoAdapter(Activity context, int resource, List<Agendamento> objects) {
		super(context, resource, objects);
		this.context = context;
		this.agendamentos = objects;
	}
	
	@Override
	public int getCount() {
		return super.getCount();
	}

	@Override
	public Agendamento getItem(int position) {
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
			convertView = inflater.inflate(R.layout.agendamento_item, null);
			ViewHolder holder = new ViewHolder();
			holder.imagem = (ImageView) convertView.findViewById(R.id.imagem);
			holder.titulo = (TextView) convertView.findViewById(R.id.titulo);
			holder.descricao = (TextView) convertView.findViewById(R.id.descricao);
			holder.greater = (ImageView) convertView.findViewById(R.id.greater);
			convertView.setTag(holder);
	    }

	    // fill data
	    ViewHolder holder = (ViewHolder) convertView.getTag();
	    
	    Agendamento agendamento = getItem(position);
	    if (agendamento.getImportancia().equals(ImportanciaLembreteEnum.BAIXA)){
	    	holder.imagem.setImageResource(R.drawable.agendamento_azul);
	    } else if (agendamento.getImportancia().equals(ImportanciaLembreteEnum.MEDIA)){
	    	holder.imagem.setImageResource(R.drawable.agendamento_verde);
	    } else if (agendamento.getImportancia().equals(ImportanciaLembreteEnum.ALTA)){
	    	holder.imagem.setImageResource(R.drawable.agendamento_amarelo);
	    } else if (agendamento.getImportancia().equals(ImportanciaLembreteEnum.URGENTE)){
	    	holder.imagem.setImageResource(R.drawable.agendamento_vermelho);
	    }  
	    holder.titulo.setText(agendamento.getTitulo());
	    holder.titulo.setTypeface(FontUtils.getFont(getContext(), FontEnum.CHUNKFIVE));
	    holder.descricao.setTypeface(FontUtils.getFont(getContext(), FontEnum.POPCORN_MOUNTAIN));
		holder.descricao.setText(agendamento.getDescricao());
		holder.greater.setImageResource(R.drawable.greater);
		
	    return convertView;
	}

	public List<Agendamento> getItems() {
        return agendamentos;
    }
}
