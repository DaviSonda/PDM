package com.zampieri.estadosbrasil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// Adapter utilizado para exibir as informações dos Estados no ListView.

public class EstadoAdapter extends BaseAdapter {
	private Context context;
	private List<Estados> estadoList;
	
	public EstadoAdapter(Context context, List<Estados> estadolist){
		this.context = context;        
		this.estadoList = estadolist;    
	}        
	
	public int getCount() {        
			return estadoList.size();    
	}    
    
	public Object getItem(int position) {
		return estadoList.get(position);    
	}     
    
	public long getItemId(int position) {        
		return position;   
	}     
  
	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o estado da posição atual
		Estados estado = estadoList.get(position);                
		// Cria uma instância do layout XML para os objetos correspondentes na View
		
		LayoutInflater inflater = (LayoutInflater) 	context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listview_estados, null);
		
		// Estado - Abreviação
		TextView textState = (TextView)view.findViewById(R.id.textState);
		textState.setText(estado.getEstado() + " - " + estado.getAbreviacao()); 
		
		// Capital        
		TextView textCapital = (TextView)view.findViewById(R.id.textCapital);
		textCapital.setText(estado.getCapital());     
		
		// Área
		TextView textArea = (TextView)view.findViewById(R.id.textArea);
		textArea.setText(String.valueOf(estado.getArea()));
		
		// Bandeira        
		ImageView img = (ImageView)view.findViewById(R.id.imageState);
		img.setImageResource(estado.getBandeira());         
		return view;
	}

}