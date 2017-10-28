package com.islam.eltendawy.mynote;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static  final int Request_Code=100;
    public static  final int Edit_Code=201;
    public static  final int Delete_Code=202;
    public static  final int Select_Code=203;
    private int CurrenNote;
    List<NoteItem>notes;
    DataSource datasource;
    ListView lv;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh();

    }

    private void refresh() {
        if(datasource==null)
            datasource=new DataSource(this);
        notes=datasource.getNotes();
        CustomListAdapter adapter=new CustomListAdapter(this,notes,datasource.getNotesKeys());
        lv= (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
        context =this;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,editor.class);
                if(datasource==null)
                    datasource=new DataSource(context);
                intent.putExtra("k",notes.get(position).getKey());
                intent.putExtra("v",notes.get(position).getValue());
                startActivityForResult(intent,Request_Code);

            }
        });
        //ContextMenu menue=new ContextMenu() ;
        //lv.createContextMenu(menue);
        EditText e= (EditText) findViewById(R.id.editText);
        registerForContextMenu(lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public  void  newNote(MenuItem item){
        Intent intent=new Intent(MainActivity.this,editor.class);
        startActivityForResult(intent,Request_Code);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      try
      {        // Check which request we're responding to

        if (requestCode == Request_Code) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (datasource == null)
                    datasource = new DataSource(this);
                if (data.getStringExtra("k") == null)
                    datasource.saveNote(NoteItem.getnewitem(this, data.getStringExtra("v")));
                else {
                    datasource.saveNote(new NoteItem(data.getStringExtra("k"), data.getStringExtra("v")));
                    Toast.makeText(context,data.getStringExtra("v"), Toast.LENGTH_SHORT).show();
                    }
            }

        }
      }catch (Exception ex)
      {

      }
    }
    @Override
    protected void onResume(){
        super.onResume();
        refresh();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        CurrenNote=(int)((AdapterView.AdapterContextMenuInfo)menuInfo).id;
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.contextmenu,menu);
        menu.add(0,Edit_Code,0,"Edit");
        menu.add(0,Delete_Code,0,"Delete");
        menu.add(0,Select_Code,0,"Select");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==Delete_Code)
        {
            if(datasource==null)
                datasource=new DataSource(this);
            datasource.removeNote(datasource.getNotesKeys().get(CurrenNote));
            refresh();
        }if(item.getItemId()==Select_Code)
        {
            
        }
        return  true;
    }
}
