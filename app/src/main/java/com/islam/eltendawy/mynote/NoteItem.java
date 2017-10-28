package com.islam.eltendawy.mynote;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.security.AccessController.getContext;

/**
 * Created by Islam on 11-Sep-17.
 */


public class NoteItem {
    public NoteItem()
    {

    }
    private String key;
    private String value;

    public NoteItem(String key,String value) {
        this.key=key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public  static NoteItem getnewitem(String value)
    {
        Locale locale=new Locale("en-US");
        Locale.setDefault(locale);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        NoteItem item=new NoteItem(format.format(new Date()),value);
        return  item;
    }
    public  static NoteItem getnewitem(Context context,String value)
    {
        Locale locale=new Locale("en-US");
        Locale.setDefault(locale);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        NoteItem item=new NoteItem(format.format(new Date()),value);
//        Toast.makeText(context,item.getValue()+"\r\n"+value,Toast.LENGTH_SHORT).show();
        return  item;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
