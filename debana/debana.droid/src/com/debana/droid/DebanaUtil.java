package com.debana.droid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

public class DebanaUtil {
	public static DebanaUtil instance = new DebanaUtil();

	public List<Ticket> tickets = new ArrayList<Ticket>();

	public void updateTitles(ArrayAdapter<Ticket> list) {
		GetTicketsTask task = new GetTicketsTask();
		task.execute(list);
	}

	class GetTicketsTask extends AsyncTask<ArrayAdapter<Ticket>, Void, String> {
		
		ArrayAdapter<Ticket> adapter =null;

		@Override
		protected String doInBackground(ArrayAdapter<Ticket>... lists) {
			adapter = lists[0];
			String resultJSON = readDebanaLatest();
			DebanaUtil.instance.tickets.clear();
			try {
				JSONObject ticketBundle = new JSONObject(resultJSON);
				ticketBundle = ticketBundle.getJSONObject("ticketBundle");
				JSONArray jsonArray = ticketBundle.getJSONArray("ticket");
				Log.i(DebanaActivity.class.getName(), "Number of entries " + jsonArray.length());
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					Log.i(DebanaActivity.class.getName(), jsonObject.getString("title"));
					Ticket t = new Ticket();
					t.setTitle(jsonObject.getString("title"));
					t.setDescription(jsonObject.getString("description"));					
					DebanaUtil.instance.tickets.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return "OK";
		}
		
		@Override
		protected void onPostExecute(String result) {
			adapter.notifyDataSetChanged();
		}

		public String readDebanaLatest() {
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(
					"http://10.0.2.2:8080/debana.web/api/tickets/latest");
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					// FAILED
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return builder.toString();
		}
	}

}
