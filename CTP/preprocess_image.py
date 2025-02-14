import cv2
import os

def preprocess_image(image_path):
    """
    Preprocesses an input image for OCR.

    Args:
        image_path (str): Path to the input image.

    Returns:
        str: Path to the saved preprocessed image.
    """
    # Load the image in grayscale
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
    
    # Apply Gaussian blur to reduce noise
    blurred_image = cv2.GaussianBlur(image, (3, 3), 0)
    
    # Apply binary thresholding
    binary_image = cv2.adaptiveThreshold(
        blurred_image, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C, cv2.THRESH_BINARY_INV, 15, 5
    )

    # Apply morphological transformations to clean up noise
    kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (2, 2))
    cleaned_image = cv2.morphologyEx(binary_image, cv2.MORPH_CLOSE, kernel)


    # Apply dilation to thicken text strokes
    # dilated_image = cv2.dilate(cleaned_image, kernel, iterations=1)
    
    # Generate a new file name for the processed image
    file_name, file_ext = os.path.splitext(image_path)
    processed_image_path = f"{file_name}_processed{file_ext}"
    
    # Save the preprocessed image
    cv2.imwrite(processed_image_path, cleaned_image)
    print(f"Preprocessed image saved to: {processed_image_path}")
    
    return processed_image_path

if __name__ == "__main__":
    # Test the function
    input_path = "CTP/data/simple.jpg" 
    processed_path = preprocess_image(input_path)
