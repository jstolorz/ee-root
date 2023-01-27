package org.bluesoft.bean;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.Part;

import java.io.*;

@Model
public class UploadBean {
    private Part part;

    public Part getPart() {
        return part;
    }

    public void setPart(final Part part) {
        this.part = part;
    }

    public String uploadFile() throws IOException {
        String fileName = getFileName(part);
        String basePath= "/tmp/";

        File outputFilePath = new File(basePath + fileName);

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{

            inputStream = part.getInputStream();
            outputStream = new FileOutputStream(outputFilePath);

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,read);
            }

        }catch (IOException e){
            e.printStackTrace();
            printMessage("Error! File upload error!");

        }finally {
            if(outputStream != null){
                outputStream.close();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return null;

    }

    private void printMessage(final String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,message,null);
        FacesContext.getCurrentInstance().addMessage(null,facesMessage);
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for(String content : part.getHeader("content-disposition").split(";")){
              if(content.trim().startsWith("filename")){
                  return content.substring(content.indexOf('=') + 1).trim();
              }
        }

        return null;
    }
}
