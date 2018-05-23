package com.wedo.OMS.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="pdf")
public class PDFController
{

    @RequestMapping(value="/**/{pdfPath}")
    public void getPdfStream(@PathVariable("pdfPath") String pdfPath,HttpServletRequest request , HttpServletResponse response)
    {
        String path = request.getRequestURL().toString();
        path = path.substring(path.indexOf("source")+7);
        try
        {
            InputStream fileInputStream =  getYCFile(path);
            response.setHeader("Content-Disposition", "attachment;fileName=test.pdf");
            response.setContentType("multipart/form-data");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public InputStream getYCFile(String urlPath) {
        InputStream inputStream = null;
        try {
            try {
                String strUrl = urlPath.trim();
                URL url=new URL(strUrl);
                //打开请求连接
                URLConnection connection = url.openConnection();
                HttpURLConnection httpURLConnection=(HttpURLConnection) connection;
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                // 取得输入流，并使用Reader读取
                inputStream = httpURLConnection.getInputStream();
                return inputStream;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                inputStream = null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputStream = null;
        }
        return inputStream;
    }
}
