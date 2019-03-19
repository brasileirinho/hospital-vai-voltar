package br.com.franciscochaves.hospitalvaivoltar;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddPacienteActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paciente);

        this.mViewHolder.nome = findViewById(R.id.text_nome);
        this.mViewHolder.leito = findViewById(R.id.text_leito);
        this.mViewHolder.pressaoArterial = findViewById(R.id.text_pressao_arterial);
        this.mViewHolder.batimentoCardiaco = findViewById(R.id.text_batimento_cardiaco);
        this.mViewHolder.temperatura = findViewById(R.id.text_temperatura);
        this.mViewHolder.salvar = findViewById(R.id.button_salvar);
    }

    private static class ViewHolder {
        EditText nome;
        EditText leito;
        EditText pressaoArterial;
        EditText batimentoCardiaco;
        EditText temperatura;
        Button salvar;
    }

    private void exixbirMessagem(String titulo, String messagem) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(messagem);
        builder.show();
    }
}
