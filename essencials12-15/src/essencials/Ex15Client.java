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

public class Ex15Client {
	

		
		public static void main( String[] args )
	    {
			Socket clientsocket;
			try {
				clientsocket = new Socket("localhost", 550);
			 
				DataOutputStream msg1 = new DataOutputStream(
						clientsocket.getOutputStream());
				System.out.println("Introdueix el Nom");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader buff = new BufferedReader (isr);
				String missatge = buff.readLine();
				msg1.writeUTF("PLAY " + missatge );
				
				msg1.flush();
				boolean lis = true;
				
				while(lis){
				msg1.flush();
				DataInputStream msg2 = new DataInputStream(
						clientsocket.getInputStream());
				String mensaje2 = msg2.readUTF();
				String trozos[] = mensaje2.split(" ");
				
				if(trozos[0].equals("WAIT")){
					System.out.println(mensaje2);
					
				}else if(trozos[0].equals("VERSUS")){
					System.out.println(mensaje2);
					
				}else if(trozos[0].equals("YOUR") && trozos[1].equals("BET")){
					System.out.println("Introdueix les monedes que apostaras i les totals separades per espai");
					missatge = buff.readLine();
					msg1.flush();
					msg1.writeUTF("MY BET " + missatge);
				
				}else{
					System.out.println(mensaje2);
				}
				
				
				}
			} catch (IOException e) {
				System.err.println("Exception " + e.getMessage());
			}
		}

	}

