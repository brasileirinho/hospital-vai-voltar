package br.com.franciscochaves.hospitalvaivoltar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.buttonAdiconarPaciente = findViewById(R.id.button_adicionar_paciente);
        this.mViewHolder.buttonPesquisarPaciente = findViewById(R.id.button_pesquisa_paciente);

        this.mViewHolder.buttonAdiconarPaciente.setOnClickListener(this);
        this.mViewHolder.buttonPesquisarPaciente.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_adicionar_paciente){
            Intent intent = new Intent(this, AddPacienteActivity.class);
            startActivity(intent);
        }else if(id == R.id.button_pesquisa_paciente){
            Intent intent = new Intent(this, DetalheActivity.class);
            startActivity(intent);
        }
    }

    private static class ViewHolder {
        Button buttonAdiconarPaciente;
        Button buttonPesquisarPaciente;
    }
}
