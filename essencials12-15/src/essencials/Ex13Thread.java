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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex13Thread extends Thread {
	
		private Socket clientsocket = null;
		
		public  Ex13Thread (Socket socket){
			super("Ex13Thread");
			this.clientsocket = socket;
		}
		
		public void run(){
			try{
				DataOutputStream msg = new DataOutputStream(
						clientsocket.getOutputStream());
				System.out.println("Client Conctat");
				SimpleDateFormat fecha = new SimpleDateFormat(
						"yyyy-MM-dd' 'HH:mm:ss");
				String date = fecha.format(new Date());
				msg.writeUTF(date);
				clientsocket.close();
				
			}catch(IOException e){
				
			}
		}

	}

