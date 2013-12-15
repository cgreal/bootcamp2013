package com.debana.droid;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.debana.droid.tickets.R;

public class DebanaActivity extends Activity {

	private Button button;
	private ListView list;
	private ArrayAdapter<Ticket> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debana);
		
		button = (Button) findViewById(R.id.refreshButton);
		list = (ListView) findViewById(R.id.listView);

		adapter = new ArrayAdapter<Ticket>(this, 
			android.R.layout.simple_list_item_2, android.R.id.text1, DebanaUtil.instance.tickets) {
			  @Override
			  public View getView(int position, View convertView, ViewGroup parent) {
			    View view = super.getView(position, convertView, parent);
			    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
			    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
			    Ticket ticket = DebanaUtil.instance.tickets.get(position);
			    text1.setText(ticket.getTitle());
			    text2.setText(ticket.getDescription());
			    return view;
			  }
			};

		list.setAdapter(adapter);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DebanaUtil.instance.updateTitles(adapter);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.debana, menu);
		return true;
	}

}
