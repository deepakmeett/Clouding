package com.example.clouding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Adapter_Clouding extends RecyclerView.Adapter<Adapter_Clouding.ViewHolder> {

    List<Model_Clouding> list = new ArrayList<>();

    public void book_add(Model_Clouding s) {
        list.add(s);
    }

    @NonNull
    @Override
    public Adapter_Clouding.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Clouding.ViewHolder holder, int position) {
        holder.textView1.setText(list.get(position).getBook_one());
        holder.textView2.setText(list.get(position).getBook_two());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text5);
            textView2 = itemView.findViewById(R.id.text6);
        }
    }
}
