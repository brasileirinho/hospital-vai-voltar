package br.com.franciscochaves.hospitalvaivoltar;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPacienteActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    private SQLiteDatabase db;
    private String nomeDB = "DBHospital";
    private String nomeTabela = "Pacientes";

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

        this.mViewHolder.salvar.setOnClickListener(this);

        db = openOrCreateDatabase(nomeDB, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ nomeTabela +
                "(Nome VARCHAR," +
                " Leito VARCHAR," +
                " PressaoArterial VARCHAR," +
                " BatimentoCardiacos VARCHAR," +
                " Temperatura VARCHAR )"
        );
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_salvar){
            if(verificarCamposPreenchido()){

                db.execSQL("INSERT INTO " + nomeTabela +
                        " VALUES ('"+mViewHolder.nome.getText()+
                        "','"+mViewHolder.leito.getText()+
                        "','"+mViewHolder.pressaoArterial.getText()+
                        "','"+mViewHolder.batimentoCardiaco.getText()+
                        "','"+mViewHolder.temperatura.getText()+
                        "');"
                );

                exixbirMessagem("Ok", "Dados Gravados com sucesso!");

                limparCampos();
            }else{
                exixbirMessagem("Erro", "Preencha todos os campos");
            }
        }
    }

    private void limparCampos() {
        mViewHolder.nome.setText("");
        mViewHolder.leito.setText("");
        mViewHolder.pressaoArterial.setText("");
        mViewHolder.batimentoCardiaco.setText("");
        mViewHolder.temperatura.setText("");
    }

    private boolean verificarCamposPreenchido() {
        if(mViewHolder.nome.getText().toString().trim().length() == 0 ||
            mViewHolder.leito.getText().toString().trim().length() == 0 ||
            mViewHolder.pressaoArterial.getText().toString().trim().length() == 0 ||
            mViewHolder.batimentoCardiaco.getText().toString().trim().length() == 0 ||
            mViewHolder.temperatura.getText().toString().trim().length() == 0){
            return false;
        }
        return true;
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
