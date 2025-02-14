import sys
from Adafruit_IO import MQTTClient
import time
import random
import os
from dotenv import load_dotenv
from simple_ai import *
from uart import *

load_dotenv()

AIO_FEED_IDs = ["button1", "button2"]
AIO_USERNAME = "HuyNguyenn0103"
AIO_KEY = os.environ['API_KEY']

def connected(client):
    print("Ket noi thanh cong...")
    for topic in AIO_FEED_IDs:
        client.subscribe(topic)

def subscribe(client, userdata, mid, granted_qos):
    print("Subscribe thanh cong...")

def disconnected(client):
    print("Ngat ket noi...")
    sys.exit(1)

def message(client, feed_id, payload):
    print("Nhan du lieu: " + payload + " ,feed id: " + feed_id)
    # TODO: Send data to microbit or other devices
    if feed_id == "button1":
        if payload == "0":
            writeData(1)
        else:
            writeData(2)

    elif feed_id == "button2":
        if payload == "0":
            writeData(3)
        else:
            writeData(4)   


client = MQTTClient(AIO_USERNAME, AIO_KEY)
client.on_connect = connected
client.on_disconnect = disconnected
client.on_message = message
client.on_subscribe = subscribe
client.connect()
client.loop_background()

counter = 8
sensor_type = 0

counter_ai = 5
ai_result = ""
previous_ai_result = ""

while True:
   counter = counter - 1
   if counter <= 0 :
       counter = 8
       #Todo
       print("Random data is publishing...")
       if sensor_type == 0:
           print("Temperature...")
           temp = random.randint(10, 20)
           client.publish("cambien1", temp)
           sensor_type = 1
       elif sensor_type == 1:
           print("Humidity...")
           humi = random.randint(50, 70)
           client.publish("cambien2", humi)
           sensor_type = 2
       elif sensor_type == 2:
           print("Light...")
           light = random.randint(100, 500)
           client.publish("cambien3", light)
           sensor_type = 0
   counter_ai = counter_ai - 1
   if counter_ai <= 0:
       counter_ai = 5
       previous_ai_result = ai_result
       ai_result = image_detector()
       print("AI Output: ", ai_result)
       if(previous_ai_result != ai_result):
            client.publish("ai", ai_result)
   readSerial(client)
#    if cv2.waitKey(1) & 0xFF == ord('q'):
#        break
   time.sleep(1)



