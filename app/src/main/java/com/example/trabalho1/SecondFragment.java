package com.example.trabalho1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    private ListView listView;

    String[] nomes = {"Abraão", "Noé", "Moises"};
    int[] imagens = {
            R.drawable.abraao,
            R.drawable.noe,
            R.drawable.moises
    };

    private int selectedPosition = -1; // Nenhum item selecionado inicialmente

    public SecondFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        listView = view.findViewById(R.id.listaPersonagens);

        CustomAdapter adapter = new CustomAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            selectedPosition = position;
            adapter.notifyDataSetChanged(); // Atualiza a lista para refletir a seleção

            // Exemplo: aqui você pode tocar o áudio associado ao personagem
            // if (position == 0) {
            //     MediaPlayer.create(getContext(), R.raw.audio_abraao).start();
            // }
        });

        return view;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nomes.length;
        }

        @Override
        public Object getItem(int position) {
            return nomes[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = getLayoutInflater().inflate(R.layout.list_item, null);

            ImageView image = row.findViewById(R.id.imageView);
            TextView text = row.findViewById(R.id.textView);

            image.setImageResource(imagens[position]);
            text.setText(nomes[position]);

            if (position == selectedPosition) {
                row.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.selected_item));
            } else {
                row.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent));
            }

            return row;
        }
    }
}
