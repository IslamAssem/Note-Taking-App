package com.islam.eltendawy.mynote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editor extends AppCompatActivity {

    String key="", value="";
    EditText editor;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    boolean on=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editor = (EditText) findViewById(R.id.editText);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.notes);

        key = getIntent().getStringExtra("k");
        value = getIntent().getStringExtra("v");
        if (value != null) {
            editor.setText(value);
        }if(value==null)
        value="";
        editor.setSelection(editor.getText().toString().length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            if (editor == null)
                editor = (EditText) findViewById(R.id.editText);
            if(editor.getText().toString()==value)finish();
            View v=getLayoutInflater().inflate(R.layout.dialog_confirm,null,false);
            ((TextView)v.findViewById(R.id.confirmText)).setText("do you want to save changes?");
            ((Button)v.findViewById(R.id.confirmbtnY)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vv) {
                    if(dialog!=null)
                    {
                        dialog.dismiss();
                        on=false;
                        Intent intent = new Intent();
                        if (editor == null)
                            editor = (EditText) findViewById(R.id.editText);
                        String v = editor.getText().toString();
                        intent.putExtra("v", v);
                        if (key != null)
                            intent.putExtra("k", key);
                        //Toast.makeText(this,"t : "+v,Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
            ((Button)v.findViewById(R.id.confirmbtnN)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dialog!=null)
                    {
                        dialog.dismiss();
                        on=false;
                        finish();
                    }
                }
            });
            builder=new AlertDialog.Builder(this);
            builder.setView(v);
            dialog=builder.create();
            dialog.show();
            on=true;
        }
        return false;
    }

    public void cancel(MenuItem item) {
        if (editor == null)
            editor = (EditText) findViewById(R.id.editText);
        if(value.equals(editor.getText().toString()))
        {
            Intent intent = new Intent();
            intent.putExtra("v","");
            intent.putExtra("k", "");
            //Toast.makeText(this,"t : "+v,Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, intent);
            finish();
            return;
        }
        View v=getLayoutInflater().inflate(R.layout.dialog_confirm,null,false);
        ((TextView)v.findViewById(R.id.confirmText)).setText("are you sure you want to discard changes?");
        ((Button)v.findViewById(R.id.confirmbtnY)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog!=null)
                {
                    dialog.dismiss();
                    on=false;
                    Intent intent = new Intent();
                    intent.putExtra("v","");
                    intent.putExtra("k", "");
                    //Toast.makeText(this,"t : "+v,Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, intent);
                    finish();
                }
            }
        });
        ((Button)v.findViewById(R.id.confirmbtnN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog!=null)
                {
                    dialog.dismiss();
                    on=false;
                }
            }
        });
        builder=new AlertDialog.Builder(this);
        builder.setView(v);
        dialog=builder.create();
        dialog.show();
        on=true;
    }

    public void save(MenuItem item) {
        Intent intent = new Intent();
        if (editor == null)
            editor = (EditText) findViewById(R.id.editText);
        String v = editor.getText().toString();
        intent.putExtra("v", v);
        if (key != null)
            intent.putExtra("k", key);
        //Toast.makeText(this,"t : "+v,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        if(dialog!=null)
            if(on)
            {
                dialog.dismiss();
                on=false;
                return;
            }
        if (editor == null)
            editor = (EditText) findViewById(R.id.editText);
        if(value.equals(editor.getText().toString()))
        {
            Intent intent = new Intent();
            intent.putExtra("v","");
            intent.putExtra("k", "");
            //Toast.makeText(this,"t : "+v,Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, intent);
            finish();
            return;
        }
        View v=getLayoutInflater().inflate(R.layout.dialog_confirm,null,false);
        ((TextView)v.findViewById(R.id.confirmText)).setText("do you want to save changes?");
        ((Button)v.findViewById(R.id.confirmbtnY)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vv) {
                if(dialog!=null)
                {
                    dialog.dismiss();
                    on=false;
                    Intent intent = new Intent();
                    if (editor == null)
                        editor = (EditText) findViewById(R.id.editText);
                    String v = editor.getText().toString();
                    intent.putExtra("v", v);
                    if (key != null)
                        intent.putExtra("k", key);
                    //Toast.makeText(this,"t : "+v,Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        ((Button)v.findViewById(R.id.confirmbtnN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog!=null)
                {
                    dialog.dismiss();
                    on=false;
                    finish();
                }
            }
        });
        builder=new AlertDialog.Builder(this);
        builder.setView(v);
        dialog=builder.create();
        dialog.show();
        on=true;
    }
}
