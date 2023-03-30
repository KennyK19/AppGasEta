package devandroid.mesquita.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import java.util.List;

import devandroid.mesquita.appgaseta.database.GasEtaDB;
import devandroid.mesquita.appgaseta.model.Combustivel;
import devandroid.mesquita.appgaseta.view.GasEtaActivity;

public class CombustivelController extends GasEtaDB {

    SharedPreferences preferences;

    SharedPreferences.Editor dadosPreferences;

    public static final String NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity){
        super(activity);
        preferences =
                activity.getSharedPreferences(NOME_PREFERENCES,0);
        dadosPreferences = preferences.edit();
    }

    public void salvar (Combustivel combustivel){

        ContentValues dados = new ContentValues();

        dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        dadosPreferences.putFloat("preço do combustivel", (float) combustivel.getPrecoDoCombustivel());
        dadosPreferences.putString("recomendação", combustivel.getRecomendacao());
        dadosPreferences.apply();

        dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());


        salvarObjeto("Combustivel", dados);
    }


    public List<Combustivel> getListaDeDados() {
        return listarDados();
    }

    public void limpar (){
        dadosPreferences.clear();
        dadosPreferences.apply();
    }

}
