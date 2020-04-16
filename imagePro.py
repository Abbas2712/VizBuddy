import cv2
import numpy as np
def imageProcessing(image):
    pass
img = cv2.imread('demo.jpg', 0)
cv2.imshow('image', img) 
k = cv2.waitKey(0) & 0xFF
  

# wait for ESC key to exit 
if k == 27:
    cv2.destroyAllWindows() 