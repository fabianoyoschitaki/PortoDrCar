package com.portoseguro.portodrcar.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.portoseguro.portodrcar.R;
import com.portoseguro.portodrcar.activity.AgendamentoDetalheActivity;
import com.portoseguro.portodrcar.activity.LembreteDetalheActivity;
import com.portoseguro.portodrcar.adapter.AgendamentoAdapter;
import com.portoseguro.portodrcar.adapter.LembreteAdapter;
import com.portoseguro.portodrcar.constants.PrototipoConstants;
import com.portoseguro.portodrcar.enums.ImportanciaLembreteEnum;
import com.portoseguro.portodrcar.enums.TipoLembreteEnum;
import com.portoseguro.portodrcar.model.Agendamento;
import com.portoseguro.portodrcar.model.Lembrete;

public class LembretesFragment extends Fragment {
	
	//lembretes
	private ListView lembretesListView;
	private LembreteAdapter lembreteAdapter;
	private Button lembreteHeaderButton;
	
	//agendamentos
	private ListView agendamentosListView;
	private AgendamentoAdapter agendamentoAdapter;
	private Button agendamentoHeaderButton;
	
	public LembretesFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_lembretes, container, false);
        getActivity().getActionBar().setTitle("Porto Dr Car - Health CARe");
        getActivity().getActionBar().setSubtitle(PrototipoConstants.getApresentacao());
        inicializaViews(rootView);
        return rootView;
    }

	private void inicializaViews(final View view) {
		//lembretes
		this.lembreteHeaderButton = (Button) view.findViewById(R.id.lembretes_header);
		this.lembreteHeaderButton.setText(getResources().getString(R.string.lembretes) + " (" + PrototipoConstants.getLembretes().size() + ")");
		this.lembreteHeaderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (lembretesListView.getVisibility() == View.VISIBLE){
					lembretesListView.setVisibility(View.GONE);
				} else {
					lembretesListView.setVisibility(View.VISIBLE);
				}
			}
		});
		this.lembreteAdapter = new LembreteAdapter(getActivity(), R.id.lembretes_list, PrototipoConstants.getLembretes());
		this.lembretesListView = (ListView) view.findViewById(R.id.lembretes_list);
		this.lembretesListView.setAdapter(lembreteAdapter);
		/** quando clicar em um item da lista, position inicia de 0 **/
		this.lembretesListView.setOnItemClickListener(new OnItemClickListener()
		{
		    @Override 
		    public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    { 
		    	Intent myIntent = new Intent(getActivity(), LembreteDetalheActivity.class);
		    	myIntent.putExtra("lembrete", PrototipoConstants.getLembretes().get(position));
                getActivity().startActivity(myIntent); 
                getActivity().overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
		    }
		});
		
		//agendamentos
		this.agendamentoHeaderButton = (Button) view.findViewById(R.id.agendamentos_header);
		this.agendamentoHeaderButton.setText(getResources().getString(R.string.agendamentos) + " (" + PrototipoConstants.getAgendamentos().size() + ")");
		this.agendamentoHeaderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (agendamentosListView.getVisibility() == View.VISIBLE){
					agendamentosListView.setVisibility(View.GONE);
				} else {
					agendamentosListView.setVisibility(View.VISIBLE);
				}
			}
		});
		this.agendamentoAdapter = new AgendamentoAdapter(getActivity(), R.id.agendamentos_list, PrototipoConstants.getAgendamentos());
		this.agendamentosListView = (ListView) view.findViewById(R.id.agendamentos_list);
		this.agendamentosListView.setAdapter(agendamentoAdapter);
		/** quando clicar em um item da lista, position inicia de 0 **/
		this.agendamentosListView.setOnItemClickListener(new OnItemClickListener()
		{
		    @Override 
		    public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    { 
		    	Intent myIntent = new Intent(getActivity(), AgendamentoDetalheActivity.class);
		    	myIntent.putExtra("agendamento", PrototipoConstants.getAgendamentos().get(position));
                getActivity().startActivity(myIntent); 
                getActivity().overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
		    }
		});
	}
}
