package com.example.tp1sharedprefmvvm_lab3.ui.registro;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1sharedprefmvvm_lab3.Model.Usuario;
import com.example.tp1sharedprefmvvm_lab3.request.ApiClient;
import com.example.tp1sharedprefmvvm_lab3.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    public MutableLiveData<Usuario> user;


    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<Usuario> getUser() {
        if(user == null){
            user = new MutableLiveData<>();
        }
        return user;
    }
    //si exiten un usuario leo y muestro sus datos
    public void datosDeUser(boolean data){
        if (data){
            user.setValue(ApiClient.leer(getApplication().getApplicationContext()));
        }
    }


    public void guardar(String nombre, String apellido, String dni, String mail,String pass){

        Usuario usuario = new Usuario(Long.parseLong(dni),nombre,apellido,mail,pass);
        ApiClient.guardar(getApplication().getApplicationContext(),usuario);

        Intent intent = new Intent(getApplication().getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().getApplicationContext().startActivity(intent);

    }
}
