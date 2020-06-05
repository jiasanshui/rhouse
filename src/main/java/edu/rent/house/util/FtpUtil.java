package edu.rent.house.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.UUID;

@Component
public class FtpUtil {

    private static FTPClient ftpClient = new FTPClient();
    private static String REMOTE_IP = "49.234.67.243";
    private static Integer UPLOAD_PROT  = 21;
    private static String FTP_USRENAME = "ftptest";
    private static String FTP_PASSWORD = "qweewq";
    private static String REMOTE_PATH = "rentHouse";

    /**
     * 将图片上传到ftp远程服务器
     */
    public String upload(MultipartFile multipartFile) {
        //创建客户端对象
        InputStream input = null;
        try {
            //连接Ftp服务器
            ftpClient.connect(REMOTE_IP,UPLOAD_PROT);
            //登录
            ftpClient.login(FTP_USRENAME,FTP_PASSWORD);
            //判断上传路径是否存在
            if(!ftpClient.changeWorkingDirectory(REMOTE_PATH)){ //不存在则创建
                ftpClient.makeDirectory(REMOTE_PATH);
            }
            //设置上传路径
            ftpClient.changeWorkingDirectory(REMOTE_PATH);
            //指定上传类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            //获取原文件名
            String originalFilename = multipartFile.getOriginalFilename();
            //生成新的文件名
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            input = multipartFile.getInputStream();
            boolean success = ftpClient.storeFile(newFileName, input);
            if(success){
                //根据web站点配置显示图片的路径
                return newFileName;
            }
            return "upload_fail";
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (ftpClient != null) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
