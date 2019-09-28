package com.example.tcp_esp8266;

import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private TextView output;
    private TextView temperatura;
    private TextView humedad;
    private TextView luz;
    private EditText entra;
    private EditText ip;
    private EditText puerto;
    String mi_ip;
    int mi_puerto;
    Socket sk;
    BufferedReader entrada;
    PrintWriter salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.TextView01);
        temperatura = (TextView) findViewById(R.id.txtTem);
        humedad = (TextView) findViewById(R.id.txtHum);
        luz = (TextView) findViewById(R.id.txtLuz);
        entra =(EditText) findViewById(R.id.editText);
        ip = (EditText) findViewById(R.id.Ip);
        puerto =(EditText) findViewById(R.id.Puerto);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build());
    }
    public void jem (View v) {
        ejecutaCliente ("ejecuta:jem.imagen_oled('jem.pbm')");
    }
    public void malaga (View v) {
        ejecutaCliente ("ejecuta:jem.imagen_oled('malaga.pbm')");
    }
    public void cenachero (View v) {
        ejecutaCliente ("ejecuta:jem.imagen_oled('cenachero.pbm')");
    }
    public void buho (View v) {
        ejecutaCliente ("ejecuta:jem.imagen_oled('buho.pbm')");
    }
    public void on (View v) {
        ejecutaCliente ("ledon");
    }
    public void off (View v) {
        ejecutaCliente ("ledoff");
    }
    public void sensor (View v) {
        ejecutaCliente ("sensores");
        String dato_sensor = output.getText().toString();
        output.setText("");
        temperatura.setText("Temperatura:"+dato_sensor.split(":")[0]);
        humedad.setText("Humedad:"+dato_sensor.split(":")[1]);
        luz.setText("Luz:"+dato_sensor.split(":")[2]);
    }
    public void enviar_mensaje (View v){
        ejecutaCliente (entra.getText().toString());
    }
    public void ejecutaCliente (String dato) {
        mi_ip = ip.getText().toString();
        mi_puerto =  Integer.parseInt(puerto.getText().toString());
        log(" socket " + ip + " " + puerto);
        try {
            sk = new Socket(mi_ip, mi_puerto);
            sk.setSoTimeout(2000);
            entrada = new BufferedReader(
                    new InputStreamReader(sk.getInputStream()));
            salida = new PrintWriter(
                    new OutputStreamWriter(sk.getOutputStream()), true);
            salida.println(dato);
            log(entrada.readLine());
        } catch (Exception e) {
            log("error: " + e.toString());
        }
    }
    private void log(String string) {
        //output.append(string + "\n");
        output.setText(string);
    }
    /*public void cerrar (View v) throws IOException {
        sk.close();
    }*/
}
