package devandroid.mesquita.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.mesquita.appgaseta.R;
import devandroid.mesquita.appgaseta.controller.CombustivelController;
import devandroid.mesquita.appgaseta.model.Combustivel;
import devandroid.mesquita.appgaseta.util.UtilGasEta;

public class GasEtaActivity extends AppCompatActivity {

    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;
    CombustivelController controller;

    EditText editGasolina;
    EditText editEtanol;

    TextView tvResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;

    List<Combustivel> dados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaseta);

        controller = new CombustivelController(GasEtaActivity.this);
        dados = controller.getListaDeDados();

        Combustivel objAlteracao = dados.get(1);
        objAlteracao.setNomeDoCombustivel("**GASOLINA**");
        objAlteracao.setPrecoDoCombustivel(5.97);
        objAlteracao.setRecomendacao("**Abastecer com Gasolina**");

        //controller.alterar(objAlteracao);

        controller.deletar(23);

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);
        tvResultado = findViewById(R.id.tvResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isDadosOK = true;

                if (TextUtils.isEmpty(editGasolina.getText())){
                    editGasolina.setError("* Obrigatório");
                    editGasolina.requestFocus();
                    isDadosOK = false;
                }

                if (TextUtils.isEmpty(editEtanol.getText())){
                    editEtanol.setError("* Obrigatório");
                    editEtanol.requestFocus();
                    isDadosOK = false;
                }

                if (isDadosOK){

                precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol);

                tvResultado.setText(recomendacao);

                btnSalvar.setEnabled(true);

                }else{
                    Toast.makeText(GasEtaActivity.this,
                            "Digite os dados corretamente, por favor !",
                            Toast.LENGTH_SHORT).show();
                    btnSalvar.setEnabled(false);
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editGasolina.setText("");
                editEtanol.setText("");
                tvResultado.setText("");

                btnSalvar.setEnabled(false);

                controller.limpar();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //TODO: Desabilitar o botão salvar.

            combustivelGasolina = new Combustivel();
            combustivelEtanol = new Combustivel();

            combustivelGasolina.setNomeDoCombustivel("Gasolina");
            combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

            combustivelEtanol.setNomeDoCombustivel("Etanol");
            combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

            combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol));
            combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina,precoEtanol));


            controller.salvar(combustivelGasolina);
            controller.salvar(combustivelEtanol);



            int parada = 0;

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GasEtaActivity.this, "Muito obrigado por usar nosso aplicativo !", Toast.LENGTH_LONG).show();
                finish();
            }
        });



    }
}
