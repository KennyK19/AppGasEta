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

import devandroid.mesquita.appgaseta.R;
import devandroid.mesquita.appgaseta.util.UtilGasEta;

public class GasEtaActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaseta);

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

                }else{
                    Toast.makeText(GasEtaActivity.this,
                            "Digite os dados corretamente, por favor !",
                            Toast.LENGTH_SHORT).show();
                }




            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editGasolina.setText("");
                editEtanol.setText("");
                tvResultado.setText("");
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
