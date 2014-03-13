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

public class Ex14Server {
	

		public static void main(String[] args) throws IOException {

			ServerSocket tcpsocket;
			try {
				tcpsocket = new ServerSocket(550);
				System.out.println("Port per defecte");
		
				Socket client = null;
					client = tcpsocket.accept();
					System.out.println("Accept");
				DataInputStream dip = new DataInputStream(client.getInputStream());
				String mensaje = dip.readUTF();
				System.out.println(mensaje);
				int o = Integer.valueOf(mensaje);
				switch(o){
				case 0:
					DataOutputStream msg = new DataOutputStream(
							client.getOutputStream());
					SimpleDateFormat data1 = new SimpleDateFormat(
							"yyyy-MM-dd' 'HH:mm:ss");
					String date = data1.format(new Date());
					msg.writeUTF(date);
					
					break;
					
				case 1:
					DataOutputStream msg1 = new DataOutputStream(
							client.getOutputStream());
					SimpleDateFormat data2 = new SimpleDateFormat(
							"EEE, d,  MMMMM, YYYY, HH:mm:ss");
					String date2 = data2.format(new Date());
					msg1.writeUTF(date2);
					
					break;
				}
				client.close();

		

			}catch(IOException e){
				System.err.println("Exception: " + e.getMessage());
			}
		}
	}

