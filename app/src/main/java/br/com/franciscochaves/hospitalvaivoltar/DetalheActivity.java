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
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    private SQLiteDatabase db;
    private String nomeDB = "DBHospital";
    private String nomeTabela = "Pacientes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        mViewHolder.buscaNome = findViewById(R.id.text_buscar_nome);
        mViewHolder.buscar = findViewById(R.id.button_buscar_paciente);
        mViewHolder.listaTodos = findViewById(R.id.button_listar_todos);
        mViewHolder.resultado = findViewById(R.id.text_resultado_pesquisa);

        mViewHolder.buscar.setOnClickListener(this);
        mViewHolder.listaTodos.setOnClickListener(this);

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
        if(id == R.id.button_buscar_paciente){
            String paciente = buscarPaciente();
            if(paciente != null){
                mViewHolder.resultado.setText(paciente);
            }
        }else if(id == R.id.button_listar_todos){
            String lista = listarPacientes();
            if(lista != null){
                mViewHolder.resultado.setText(lista);
            }else{
                exixbirMessagem("Erro", "Lista vazia");
            }
        }
    }

    private static class ViewHolder {
        EditText buscaNome;
        Button buscar;
        Button listaTodos;
        TextView resultado;
    }

    private void exixbirMessagem(String titulo, String messagem) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(messagem);
        builder.show();
    }

    private String listarPacientes(){
        Cursor c = db.rawQuery("SELECT * FROM " + nomeTabela, null);
        if(c.getCount() == 0){
            exixbirMessagem("Erro", "Lista vazia");
            return null;
        }else{
            StringBuilder lista = new StringBuilder();
            while(c.moveToNext()){
                lista.append("Nome: " + c.getString(0) +" | Leito:"+c.getString(1) +"\n");
            }
            c.close();

            return  lista.toString();
        }
    }

    private String buscarPaciente(){
        if(mViewHolder.buscaNome.getText().toString().trim().length() == 0){
            exixbirMessagem("Erro", "Digite o nome");
            return null;
        }

        Cursor c = db.rawQuery("SELECT * FROM " + nomeTabela + " WHERE Nome='"+ mViewHolder.buscaNome.getText() +"'", null);
        if(c.moveToFirst()){
            String resultado = "Nome: " + c.getString(0) +" | Leito:"+c.getString(1);
            c.close();
            return resultado;
        }else{
            exixbirMessagem("Erro", "Nome inválido");
            c.close();
            return null;
        }
    }
}
