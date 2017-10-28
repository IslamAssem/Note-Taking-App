package com.islam.eltendawy.mynote;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Islam on 11-Sep-17.
 */

public class DataSource {
    public static final String notesPref="notes";
    private SharedPreferences preferences;
    Context context;

    public DataSource(Context context )
    {
        preferences=context.getSharedPreferences(notesPref,Context.MODE_PRIVATE);
        this.context=context;
    }
    public List<NoteItem>getNotes()
    {
        List<NoteItem> notes=new ArrayList<NoteItem>();
        Map<String,?> notesMap=preferences.getAll();
        SortedSet<String> keys=new TreeSet<String>(notesMap.keySet());
        for (String key:keys) {
            notes.add(new NoteItem(key,(String)notesMap.get(key)));
        }

        Toast.makeText(context, String.valueOf(notesMap.size())+" notes found",Toast.LENGTH_SHORT).show();
        return  notes;
    }
    public ArrayList<String>getNotesKeys()
    {
        Map<String,?> notesMap=preferences.getAll();
        SortedSet<String> keys=new TreeSet<String>(notesMap.keySet());
        return  new ArrayList<String>(keys);
    }
    public boolean saveNote(NoteItem noteItem)
    {
        try
        {

            SharedPreferences.Editor editor=preferences.edit();

        editor.putString(noteItem.getKey(),noteItem.getValue());
        editor.commit();
        return  true;}
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    return false;
    }
    public boolean removeNote(NoteItem noteItem)
    {
        if(preferences.contains(noteItem.getKey()))
        {
            SharedPreferences.Editor editor=preferences.edit();
            editor.remove(noteItem.getKey());
            return  true;
        }
        return false;
    }
    public boolean removeNote(String noteKey)
    {
        if(preferences.contains(noteKey))
        {
            SharedPreferences.Editor editor=preferences.edit();
            editor.remove(noteKey);
            editor.commit();
            return  true;
        }
        return false;
    }
}
