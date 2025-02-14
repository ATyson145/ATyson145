import os
import ssl

# Disable SSL verification
ssl._create_default_https_context = ssl._create_unverified_context

import easyocr

# Initialize the EasyOCR Reader
reader = easyocr.Reader(['en'])  # 'en' is for English

# Path to the image
image_path = "CTP/data/simple_processed.jpg"

# Perform OCR
results = reader.readtext(image_path)

# Print the results
print("Detected Text:")
for result in results:
    print(f"Text: {result[1]}, Confidence: {result[2]}")
