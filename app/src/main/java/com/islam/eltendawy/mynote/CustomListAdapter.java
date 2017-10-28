package com.islam.eltendawy.mynote;

/**
 * Created by Islam on 13-Sep-17.
 */


        import android.app.Activity;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    List<NoteItem>notes;
    public CustomListAdapter(Activity context, List<NoteItem> notes,ArrayList<String>i) {
        super(context, R.layout.item, i);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.notes=notes;

    }

    public View getView(int position,View view,ViewGroup parent) {
        Log.i("i","i");
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item,null ,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView);
        //ImageButton imageView = (ImageButton) rowView.findViewById(R.id.imageButton);
       // TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        txtTitle.setText(notes.get(position).getValue());
        //imageView.setImageResource(R.drawable.ic_launcher);
        //extratxt.setText("Description "+itemname[position]);
        if(txtTitle.getText()=="")
            txtTitle.setText("v : "+notes.get(position).getValue());
        return rowView;

    };
}