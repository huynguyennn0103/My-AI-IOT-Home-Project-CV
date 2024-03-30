#pip install pyserial

import serial.tools.list_ports

def getPort(): #Take out a port that connected with microbit or other device
    ports = serial.tools.list_ports.comports()
    N = len(ports)
    commPort = "None"
    for i in range(0, N):
        port = ports[i]
        strPort = str(port)
        if "USB Serial Device" in strPort:
            splitPort = strPort.split(" ")
            commPort = (splitPort[0])
    # return commPort
    # return "COM4" 
    return "/dev/pts/16"

if getPort() != "None":
    # Open serial gateway conntection to Microbit or othere device with speed of transfer is 115200
    ser = serial.Serial( port=getPort(), baudrate=115200)
    print(ser)
mess = ""
def processData(client, data):# Ex: !1:T:39.5## (## nghia la # tren hercules) or !1:H:80##!1:T:29.5##
    data = data.replace("!", "")
    data = data.replace("#", "")
    splitData = data.split(":")
    print(splitData)
    if splitData[1] == "T":
        client.publish("cambien1", splitData[2])
    elif splitData[1] == "H":
        client.publish("cambien3", splitData[2])
    elif splitData[1] == 'L':
        client.publish("cambien2", splitData[2])

mess = ""
def readSerial(client):
    bytesToRead = ser.inWaiting()
    if (bytesToRead > 0):
        global mess
        mess = mess + ser.read(bytesToRead).decode("UTF-8")
        while ("#" in mess) and ("!" in mess):
            start = mess.find("!")
            end = mess.find("#")
            processData(client, mess[start:end + 1])
            if (end == len(mess)):
                mess = ""
            else:
                mess = mess[end+1:]

def writeData(data):
    ser.write((str(data) + "#").encode())