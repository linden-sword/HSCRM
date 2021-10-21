package com.hscrm.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCode {

    /**
     * 随机取色
     */
    public static Color getRomdomColor() {
        Random random = new Random();
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return color;
    }

    /**
     * 验证码图片绘制
     */
    public static String drawRandomImg(int width, int height, BufferedImage img) {
        //绘图对象
        Graphics2D g2 = (Graphics2D) img.getGraphics();
//        设置和绘制图片的背景
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);
//        设置内容的字体
        g2.setFont(new Font("微软雅黑", Font.BOLD, 40));
//        设置内容字符范围
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        创建字符串，保存4位随机验证码
        StringBuffer stringBuffer = new StringBuffer();

//        在图片上绘制4个字符
        int x = 10;
        String ch = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            //取1个字符
            ch = chars.charAt(random.nextInt(chars.length())) + "";
            stringBuffer.append(ch);
            //绘制字符
            g2.setColor(getRomdomColor());
            int degree = random.nextInt() % 30;
            g2.rotate(degree * Math.PI / 180, x, 45);
            g2.drawString(ch, x, 45);

            g2.rotate(-degree * Math.PI / 180, x, 45);
            x += 48;
        }
//        绘制干扰线
        for (int i = 0; i < 6; i++) {
            g2.setColor(getRomdomColor());
            g2.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
//        绘制干扰点
        for (int i = 0; i < 30; i++) {
            g2.setColor(getRomdomColor());
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            g2.fillRect(x1, y1, 2, 2);
        }
        return stringBuffer.toString();
    }
}