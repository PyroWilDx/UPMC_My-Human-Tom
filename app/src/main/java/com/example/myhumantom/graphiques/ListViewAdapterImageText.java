package com.example.myhumantom.graphiques;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhumantom.R;

//Classe pour créer ListView qui pour chaque ligne contient une image et du texte
//Ici ces ListView vont servir à afficher l'image d'une Nourriture et son nom.
public class ListViewAdapterImageText extends BaseAdapter {

    private final Context mContext;
    private final int[] imageViewList;
    private final String[] textViewList;

    public ListViewAdapterImageText(Context mContext, int[] imageViewList, String[] textViewList) {
        this.mContext = mContext;
        this.imageViewList = imageViewList;
        this.textViewList = textViewList;
    }

    private static class ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        private ViewHolder(View row) {
            imageView = row.findViewById(R.id.listview_imageview);
            textView = row.findViewById(R.id.listview_textview);
        }
    }

    @Override
    public int getCount() {
        return imageViewList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.listview_item_image_text, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageResource(this.imageViewList[position]);
        holder.textView.setText(this.textViewList[position]);

        return view;
    }
}
