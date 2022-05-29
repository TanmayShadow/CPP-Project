
package com.example.videoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyContentAdapter extends BaseAdapter {

    Context context;
    String[] location;
    String[] names;
    LayoutInflater inflater;
    MyContentAdapter(Context c,String[] l,String[] n)
    {
        this.context=c;
        this.location=l;
        this.names=n;
        inflater=LayoutInflater.from(c);
    }
    @Override
    public int getCount() {
        return location.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.layout_mycontent_adapter,null);
        TextView name = view.findViewById(R.id.textView22);
        Button delete = view.findViewById(R.id.button4);

        name.setText(names[i]);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundMYSQL backgroundMYSQL = new BackgroundMYSQL(context);
                backgroundMYSQL.execute("delete",location[i]);
                backgroundMYSQL=null;
            }
        });

        return view;
    }
}
