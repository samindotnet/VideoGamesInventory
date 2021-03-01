package com.example.videogames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnAdd,btnShow,btnUpdate,btnDelete;
    EditText txtTitle, txtDevelopers, txtID;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTitle = (EditText)findViewById(R.id.editTextTitle);
        txtDevelopers =(EditText)findViewById(R.id.editTextDevelopers);
        txtID = (EditText)findViewById(R.id.editTextID);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnShow = (Button)findViewById(R.id.btnShow);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete =(Button)findViewById(R.id.btnDelete);
        db = new DatabaseHelper(this);
        insertGame();
        updateGame();
        deleteGame();
        showGame();
    }
    public void insertGame(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean Gameadded=db.InsertData(txtTitle.getText().toString(),txtDevelopers.getText().toString());
                if(Gameadded == true){
                    Toast.makeText(MainActivity.this,"Game is added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Game is not added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void updateGame(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean GameUpdated = db.UpdateData(txtID.getText().toString(),txtTitle.getText().toString(),txtDevelopers.getText().toString());
                if(GameUpdated==true){
                    Toast.makeText(MainActivity.this,"Game is updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Game is not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void deleteGame(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer GameDeleted = db.DeleteData(txtID.getText().toString());
                if(GameDeleted >0){
                    Toast.makeText(MainActivity.this,"Game is deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Game is not deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showGame(){
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor acura = db.showallData();
                if(acura.getCount()==0){
                    showMessage("Error","No Videogame found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(acura.moveToNext()){
                    buffer.append("ID: "+acura.getString(0)+"\n");
                    buffer.append("Title: "+acura.getString(1)+"\n");
                    buffer.append("Developers: "+acura.getString(2)+"\n\n");
                }
                showMessage("Game",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}