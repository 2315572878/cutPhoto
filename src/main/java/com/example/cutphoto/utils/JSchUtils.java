package com.example.cutphoto.utils;

import com.jcraft.jsch.*;

/**
 * @Author: tao
 * @Date: 2023/11/10 09:52
 * @Description:
 **/
public class JSchUtils {
    private String host;
    private int port;
    private String username;
    private String password;
    private Session session;

    private ChannelSftp channelSftp;

    private ChannelExec channelExec;


    public JSchUtils(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void connect() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }

    public void disconnect() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public ChannelSftp getSftpChannel() throws JSchException {
        channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        return channelSftp;
    }

    public ChannelExec getChannelExec(String command) throws JSchException {
        channelExec = (ChannelExec) session.openChannel("exec");
        channelExec.setCommand(command);
        channelExec.connect();
        return channelExec;
    }

}
