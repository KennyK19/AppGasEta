package devandroid.mesquita.appgaseta.controller;

import android.content.SharedPreferences;

import devandroid.mesquita.appgaseta.model.Combustivel;
import devandroid.mesquita.appgaseta.view.GasEtaActivity;

public class CombustivelController {

    SharedPreferences preferences;

    SharedPreferences.Editor dadosPreferences;

    public static final String NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity){
        preferences =
                activity.getSharedPreferences(NOME_PREFERENCES,0);
        dadosPreferences = preferences.edit();
    }

    public void salvar (Combustivel combustivel){
        dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        dadosPreferences.putFloat("preço do combustivel", (float) combustivel.getPrecoDoCombustivel());
        dadosPreferences.putString("recomendação", combustivel.getRecomendacao());
        dadosPreferences.apply();
    }

    public void limpar (){
        dadosPreferences.clear();
        dadosPreferences.apply();
    }

}
