## Overview

**Project Title**:
CTP (Cursive to Print)

**Project Description**:
CTP (Cursive to Print) is a Python-based tool that uses image preprocessing and Optical Character Recognition (OCR) to convert handwritten cursive text into machine-readable print text. It processes an input image, enhances readability using OpenCV, and then extracts text using EasyOCR.

**Project Goals**:

- Develop an automated system to recognize and convert cursive handwriting to printed text.
- Improve preprocessing techniques for better OCR accuracy.
- Create a simple and accessible tool that can be used for digitizing handwritten documents.

## Instructions for Build and Use

1. Ensure you have Python installed (if not, type "pip install opencv-python easyocr numpy).
2. Save an image of cursive handwriting in the data folder (i.e. simple.jpg).
3. Run the preprocessing script to clean and prepare the image for the OCR script.
4. Run the OCR script to extract text.

## Development Environment

To recreate the development environment, you need the following software and/or libraries with the specified versions:

- Python (version 3.8 or above)
- OpenCV (opencv-python)
- EasyOCR (easyocr)
- NumPy (numpy)

## Future Work

The following items I plan to fix, improve, and/or add to this project in the future:

- [ ] Improve OCR accuraacy by experimenting with different preprocessing techniques.
- [ ] Train a custom OCR model in order to better recognize different styles of writing.
- [ ] Add support for additional languages.
