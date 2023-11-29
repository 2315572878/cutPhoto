package com.example.cutphoto.controller;

import cn.hutool.core.util.IdUtil;
import com.example.cutphoto.utils.JSchUtils;
import com.jcraft.jsch.ChannelSftp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @Author: tao
 * @Date: 2023/11/29 10:27
 * @Description:
 **/
@Slf4j
@AllArgsConstructor
@Api(tags = "测试")
@RestController
@RequestMapping("/api/")
public class CutPhotoController {

    @PostMapping("/up")
    @ApiOperation("截图")
    public void localFolderUpServer() throws Exception {
        // 创建 Robot 对象
        Robot robot = new Robot();

        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        System.out.println("screenWidth = " + screenWidth);
        int screenHeight = (int) screenSize.getHeight();
        System.out.println("screenHeight = " + screenHeight);

        // 创建一个与屏幕尺寸相同的 BufferedImage 对象
        BufferedImage image = robot.createScreenCapture(new Rectangle(screenSize));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        //获取jsch连接工具类
        JSchUtils jSchUtils = new JSchUtils("111.231.44.67", 35, "root", "Busiliang922330");

        //连接到远程服务器
        jSchUtils.connect();

        //获取服务器操作类
        ChannelSftp sftpChannel = jSchUtils.getSftpChannel();

        String fileName = IdUtil.getSnowflakeNextIdStr();

        sftpChannel.put(inputStream, "/home/data/" + fileName + ".png");

    }



}
