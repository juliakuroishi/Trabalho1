package com.example.trabalho1;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

public class Fragment3 extends Fragment {

    private Spinner spinner;
    private GridView grid1;
    private MediaPlayer mediaPlayer;

    private String[] antigoTestamento = {"Gn 1:1", "Sl 118:8"};
    private int[] musicasAntigo = {R.raw.gn_1_1, R.raw.sl_118_8};

    private String[] novoTestamento = {"Hb 11:4", "Hb 11:7", "Mt 7:11"};
    private int[] musicasNovo = {R.raw.hb_11_4, R.raw.hb_11_7, R.raw.mt_7_11};

    private ArrayAdapter<String> gridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        spinner = view.findViewById(R.id.spinner);
        grid1 = view.findViewById(R.id.grid1);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, new String[]{"Antigo Testamento", "Novo Testamento"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        atualizarGrid("Antigo Testamento");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selecionado = (String) parent.getItemAtPosition(position);
                atualizarGrid(selecionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        grid1.setOnItemClickListener((parent, view1, position, id) -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            int selectedMusicResId;
            if (spinner.getSelectedItem().equals("Antigo Testamento")) {
                selectedMusicResId = musicasAntigo[position];
            } else {
                selectedMusicResId = musicasNovo[position];
            }

            mediaPlayer = MediaPlayer.create(getContext(), selectedMusicResId);
            mediaPlayer.start();
        });

        return view;
    }

    private void atualizarGrid(String testamento) {
        if (testamento.equals("Antigo Testamento")) {
            gridAdapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_list_item_1, antigoTestamento);
        } else {
            gridAdapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_list_item_1, novoTestamento);
        }
        grid1.setAdapter(gridAdapter);
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
