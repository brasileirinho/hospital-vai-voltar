package br.com.franciscochaves.hospitalvaivoltar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.buttonAdiconarPaciente = findViewById(R.id.button_adicionar_paciente);
        this.mViewHolder.buttonPesquisarPaciente = findViewById(R.id.button_pesquisa_paciente);
    }

    private static class ViewHolder {
        Button buttonAdiconarPaciente;
        Button buttonPesquisarPaciente;
    }
}
