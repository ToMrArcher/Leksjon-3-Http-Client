package com.eidjord;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class HttpClient {

    private final HashMap<String, String> headers;
    private int statusCode;
    private String body;

    public HttpClient(String host, int port, String site) throws IOException {
        headers = new HashMap<>();
        Socket socket = new Socket(host, port);
        String request = "GET /" + site + " HTTP/1.1\r\n" +
                         "Host: " + host + "\r\n" +
                         "Connection: close\r\n\r\n";
        socket.getOutputStream().write(request.getBytes());
        fintStatusCode(socket);
        findHeaders(socket);
        findBody(socket);
    }

    private String readLine(Socket socket) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while((c = socket.getInputStream().read()) != (int)'\r'){
            line.append((char)c);
        }
        socket.getInputStream().read();
        return line.toString();
    }

    private void fintStatusCode(Socket socket) throws IOException {
        String statusLine = readLine(socket);
        String[] arr = statusLine.split(" ");
        statusCode = Integer.parseInt(arr[1]);
    }

    private void findHeaders(Socket socket) throws IOException {
        String line;
        while(!(line = readLine(socket)).isEmpty()){
            String header = line.substring(0, line.indexOf(":"));
            String headerField = line.substring(line.indexOf(":") + 2);
            headers.put(header, headerField);
        }
    }

    private void findBody(Socket socket) throws IOException{
        StringBuilder bodyBuilder = new StringBuilder();
        int c;
        while((c = socket.getInputStream().read()) != -1){
            bodyBuilder.append((char)c);
        }
        body = bodyBuilder.toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public String getBody() {
        return body;
    }
}
