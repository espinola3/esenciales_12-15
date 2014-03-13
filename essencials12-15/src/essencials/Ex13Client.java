/*
 * Copyright 2014 Ernest Espinola Torrent
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package essencials;
import java.io.*;
import java.net.*;

public class Ex13Client {
	
	    public static void main( String[] args )
	    {
	    	Socket clientesocket ;
	        try{
	        	clientesocket = new Socket("localhost", 550);
	        	clientesocket.connect(clientesocket.getLocalSocketAddress());
	        	DataOutputStream msg1 = new DataOutputStream(clientesocket.getOutputStream());
	        	msg1.writeUTF("Missatge per al server");
	        	DataInputStream msg2 = new DataInputStream(clientesocket.getInputStream());
	        	String mensaje = msg2.readUTF();
	        	System.out.println(mensaje);
	        }catch(IOException e){
	        	System.err.println("Exception " + e.getMessage());
	        }
	    
	}
}
