package giz_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class WebServer implements Runnable {
	private String body; //HTML body for globalTree
	private TNode root;
	private int port;
    private volatile boolean running = true;

    

	public WebServer(TNode root, int port) {
		this.root= root;
		this.body = "";
		this.port = port;
	}    

    public void terminate() {
        running = false;
    }
 
	public void run() {
		ServerSocket s;

		System.out.println(" . start up web server on port: " + port);
		try {
			// create the main server socket
			s = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println(" ! Error: " + e);
			return;
		}

		System.out.println(" . Waiting for connection");
		while(running) {
			try {
				// wait for a connection
				Socket remote = s.accept();
				// remote is now the connected socket
				System.out.println(" . Connection, sending data.");
				BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
				PrintWriter out = new PrintWriter(remote.getOutputStream());

				// read the data sent. We basically ignore it,
				// stop reading once a blank line is hit. This
				// blank line signals the end of the client HTTP
				// headers.
				String str = ".";
				while (!str.equals(""))
					str = in.readLine();

				// Send the response
				// Send the headers
			    String style = "<style>.main{width:100%;height:100%;}span:hover {position: relative;color: white;cursor: default;}h1 {margin: 0;padding: 20 20;border: 2px solid #c1eae9;}.main>div{border:0;float:left; margin-right:10px}div{display: table;padding: 5px;padding-left: 20px;border-left: #3e403f 1px solid;color: #d1fffb;background: black;}body {margin: 0;}</style>";	    
			    style += "<style>span:hover:after {content: \"\";display: block;width: 100%;height: 20px;position: absolute;top: 0;left: -10px;padding: 0 10px;border: 1px solid darkgoldenrod;}</style>";
			    style += "<style>li {border-left: 1px solid gray;border-top: 1px solid;margin: 10px;padding: 0 26px;}</style>";
			    // fill the body
			    body = "<ul>";
			    body += root;
			    body += "</ul>";
//			    for(int i = 0; i < globalForest.size(); i++){
//			    	if( globalForest.get(i).tree != null)
//			    		body += globalForest.get(i).tree.getFamilyHtml();
//			    }

			    
				out.println("HTTP/1.0 200 OK");
				out.println("Content-Type: text/html");
				out.println("Server: Bot");
				// this blank line signals the end of the headers
				out.println("");
				// Send the HTML page
			    out.println(style);
				out.println("<H1>Welcome to the Darknes Forest </H2>");
				
				out.println("<div class=\"main\">");
				out.println(body);//	pase our body
				out.println("</div>");
				
				out.flush();
				remote.close();
			} catch (Exception e) {
				System.out.println(" ! Error: " + e);
			}
		}
	}

}