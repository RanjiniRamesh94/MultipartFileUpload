package comcast.learning.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import comcast.learning.FileUploadForm;

@RestController
public class FileUploadController {

	private static String Upload_Path = "C:\\Users\\ranjini.priya.ramesh\\Desktop\\Comcast_Learning\\SpringBoot\\MultipartFileUpload\\FileUploads\\";

	@GetMapping("/uploadFilePage")
	public ModelAndView showUpload() {

		return new ModelAndView("upload");

	}
	
	@GetMapping("/uploadMultipleFile")
	public ModelAndView showMultipleUpload() {
		
		return new ModelAndView("multipleupload");
	}

	@PostMapping("/uploadFile")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile fileToBeUploaded, HttpServletRequest httpRequest)
			throws IOException {
		System.out.println(httpRequest);

		if (fileToBeUploaded.isEmpty()) {

			return new ModelAndView("status", "message", "Please select a file");
		}

		try {

			byte[] bytes = fileToBeUploaded.getBytes();

			Path path = Paths.get(Upload_Path + fileToBeUploaded.getOriginalFilename());

			Files.write(path, bytes);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("status", "message", "File Upload Succesful");

	}
	
	@PostMapping("/MultipleFile")
	public ModelAndView multipleFileUpload(@ModelAttribute("uploadForm") FileUploadForm uploadForm) {
		
	
		
		List<MultipartFile> files = uploadForm.getFiles();
		
		System.out.println(files.size()  + "List Size");
		
		if(null != files && files.size() > 0) {
			
			for(MultipartFile fileToBeUploaded:files) {
				try {

					byte[] bytes = fileToBeUploaded.getBytes();

					Path path = Paths.get(Upload_Path + fileToBeUploaded.getOriginalFilename());

					Files.write(path, bytes);
				}

				catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			return new ModelAndView("status", "message", "Multiple File Upload Succesful");
		}
		
		else 

			return new ModelAndView("status", "message", "Please select a file");
	}

}
