import cv2.cv as cv
import time
from PIL import Image
from pytesser import *
import requests
import json

IMAGE_FILE = "test.bmp"
capture = cv.CaptureFromCAM(0)
cv.SetCaptureProperty(capture,cv.CV_CAP_PROP_FRAME_WIDTH,320)
cv.SetCaptureProperty(capture,cv.CV_CAP_PROP_FRAME_HEIGHT,240)
cv.NamedWindow("camera", 1)
cv.MoveWindow("camera",10,40)

cv.SetCaptureProperty(capture,cv.CV_CAP_PROP_SATURATION,0)
terminar = False
while not terminar:
    print "Pulse una tecla para analizar la imagen"
    while True:
        img = cv.QueryFrame(capture)
        cv.ShowImage("camera", img)
        if cv.WaitKey(10) > -1:
            break
    
    cv.SaveImage(IMAGE_FILE, img)
    img2 = Image.open("test.bmp")
    words = image_to_string(img2).strip()
    print words
    print "Indique si la lectura es correcta [s/n]"
    tecla = cv.WaitKey(0) & 0xFF
    terminar = (tecla == ord('s') or tecla == ord('S'))
 
params = {'parkingId':1,'matricula':words}
data_json = json.dumps(params)
headers = {"Content-type":"application/json"}
url = 'http://localhost:8080/rest/registroMatricula'
print "Contactando con servidor..."
response = requests.post(url,data=data_json,headers=headers)
print "Respuesta de servidor: "
print response
print response.json()

cv.DestroyAllWindows()
