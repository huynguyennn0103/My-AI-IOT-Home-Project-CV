package com.example.demoiot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    MQTTHelper mqttHelper;
    TextView txtTemp, txtHumi, txtLight, txtAI;
    LabeledSwitch btnLED, btnPUMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTemp = findViewById(R.id.txtTemperature);
        txtHumi = findViewById(R.id.txtHumidity);
        txtLight= findViewById(R.id.txtLight);
        txtAI = findViewById(R.id.txtAi);
        btnLED = findViewById(R.id.btnLED);
        btnPUMP = findViewById(R.id.btnPUMP);

        btnLED.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                if(isOn){
                    sendDataMQTT("HuyNguyenn0103/feeds/button1", "1");
                } else{
                    sendDataMQTT("HuyNguyenn0103/feeds/button1", "0");
                }
            }
        });

        btnPUMP.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                if(isOn){
                    sendDataMQTT("HuyNguyenn0103/feeds/button2", "1");
                } else{
                    sendDataMQTT("HuyNguyenn0103/feeds/button2", "0");
                }
            }
        });

        startMQTT();
    }


    public void sendDataMQTT(String topic, String value){
        MqttMessage msg = new MqttMessage();
        msg.setId(1234);
        msg.setQos(0);
        msg.setRetained(false);

        byte[] b = value.getBytes(Charset.forName("UTF-8"));
        msg.setPayload(b);

        try {
            mqttHelper.mqttAndroidClient.publish(topic, msg);
        }catch (MqttException e){
        }
    }

    public void startMQTT(){
        mqttHelper = new MQTTHelper(this);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d("TEST", topic + "***" + message.toString());
                if(topic.contains("cambien1")){
                    txtTemp.setText(message.toString() + "℃");
                } else if (topic.contains("cambien3")) {
                    txtHumi.setText(message.toString() + "%");
                } else if (topic.contains("cambien2")){
                    txtLight.setText(message.toString() + " LX");
                } else if (topic.contains("ai")){
                    String final_ai = message.toString().replace("\n","");
                    txtAI.setText(final_ai);
                    if(final_ai.contains("Co khau trang")){
                        txtAI.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ai,0,R.drawable.maskable,0);
                    } else if (final_ai.contains("Khong khau trang")){
                        txtAI.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ai,0,R.drawable.non_maskable,0);
                    } else if (final_ai.contains("Khong co nguoi")){
                        txtAI.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ai,0,R.drawable.no_people,0);
                    }
                } else if (topic.contains("button1")){
                    if(message.toString().equals("1")){
                        btnLED.setOn(true);
                    } else {
                        btnLED.setOn(false);
                    }
                } else if (topic.contains("button2")){
                    if(message.toString().equals("1")){
                        btnPUMP.setOn(true);
                    } else {
                        btnPUMP.setOn(false);
                    }
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
}