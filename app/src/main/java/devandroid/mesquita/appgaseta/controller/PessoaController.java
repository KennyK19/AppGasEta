package devandroid.mesquita.appgaseta.controller;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.mesquita.appgaseta.model.Pessoa;
import devandroid.mesquita.appgaseta.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVIP;
    public static final String NOME_PREFERENCES = "pref_listavip";

    public PessoaController(MainActivity mainActivity) {
        preferences =
                mainActivity.getSharedPreferences(NOME_PREFERENCES,0);
        listaVIP = preferences.edit();
    }


    public void salvar(Pessoa pessoa) {
        Log.i("MVC_Controller", "Salvo"+pessoa.toString());

        listaVIP.putString("primeiroNome",pessoa.getPrimeiroNome());
        listaVIP.putString("sobreNome",pessoa.getSobreNome());
        listaVIP.putString("TelefoneContato",pessoa.getTelefoneContato());
        listaVIP.putString("CursoDesejado",pessoa.getCursoDesejado());
        listaVIP.apply();

    }

    public Pessoa buscar(Pessoa pessoa){

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", "NA"));
        pessoa.setSobreNome(preferences.getString("sobreNome", "NA"));
        pessoa.setCursoDesejado(preferences.getString("CursoDesejado", "NA"));
        pessoa.setTelefoneContato(preferences.getString("TelefoneContato", "NA"));

        return pessoa;
    }

    public void limpar (){

        listaVIP.clear();
        listaVIP.apply();

    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
