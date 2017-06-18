package com.widyatama.aseplulu.aseplulu_volly;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.widyatama.aseplulu.aseplulu_volly.SQLite.DatabaseHandler;
import com.widyatama.aseplulu.aseplulu_volly.SQLite.FeedEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String url = "http://jsonplaceholder.typicode.com/users";
    private ProgressDialog dialog;
    private List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private Adapter adapter;
    private DatabaseHandler dh = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_item);
        adapter=new Adapter(this,array);
        listView.setAdapter(adapter);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        //Creat volley request obj
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();
                //parsing json
                for(int i=0;i<response.length();i++){
                    try{

                        JSONObject obj=response.getJSONObject(i);
                        Item item=new Item();
                        item.setName(obj.getString("name"));
                        item.setUsername(obj.getString("username"));
                        item.setEmail(obj.getString("email"));
                        item.setWebsite(obj.getString("website"));
                        item.setPhone(obj.getString("phone"));

                        dh.insertToDb("name","username","email","website","phone");
                        dh.selectAllData();
                        //genre is json array
                        //JSONArray cmpanyArray=obj.getJSONArray("company");
                        // ArrayList<String> cmpany=new ArrayList<String>();
                        //for(int j=0;j<cmpanyArray.length();j++){
                        //    cmpany.add((String) cmpanyArray.get(j));

                        // }
                        // item.setCompany(cmpany);

                        //add to array
                        array.add(item);




                    }catch(JSONException ex){
                        ex.printStackTrace();
                    }


                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getmInstance().addToRequesQueue(jsonArrayRequest);
}

    public void hideDialog(){
        if(dialog !=null){
            dialog.dismiss();
            dialog=null;
        }

    }
}
