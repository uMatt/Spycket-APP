package com.cqrit.spycket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PackageAdapter extends ArrayAdapter<String> {
    private List<String> packages;
    p

    public PackageAdapter(Context context, List<String> packages) {
        super(context, 0, packages);
        this.packages = packages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        String packageName = packages.get(position);
        textView.setText(packageName);

        return convertView;
    }
}

