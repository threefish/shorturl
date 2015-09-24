package com.cn.util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/6/311:20
 */

public class HucFileReader {
    /**
     * 根据文件名取得文件真实地址
     *
     * @param fileName
     * @return
     * @throws java.net.URISyntaxException
     */
    public static String getFilePath(String fileName) {
        String url = null;
        try {
            url = HucFileReader.class.getResource("/" + fileName).toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 根据文件名取得文件真实地址
     * @param fileName
     * @return
     */
    public static String getFilePathByClassLoader(String fileName) {
        String path = null;
        try {
            ClassLoader classLoader = HucFileReader.class.getClassLoader();
            URL url = classLoader.getResource(fileName);
            path = URLDecoder.decode(url.getFile(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 根据文件地址取得文件内容
     *
     * @param path
     * @return String
     * @throws java.io.IOException
     */
    public static String readeStringForPath(String path) {
        StringBuffer sb = new StringBuffer();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis, "utf-8");
            br = new BufferedReader(isr);
            String TxtBuff = null;
            while ((TxtBuff = br.readLine()) != null) {
                sb.append(TxtBuff);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
