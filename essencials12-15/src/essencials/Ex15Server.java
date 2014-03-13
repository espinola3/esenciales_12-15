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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Ex15Server {
	

		public static ArrayList<Client> lista = new ArrayList<Client>();
		public static int total;
		public static int apuestas;
		public static String Ganador = "none";
		public static boolean lis = true;

		public static void main(String[] args) throws IOException {
			ServerSocket tcpsocket;

			try {
				tcpsocket = new ServerSocket(550);
				System.out.println("Esperando conexion");

				Socket client = null;
				apuestas = 0;
				while (lis) {

					client = tcpsocket.accept();
					System.out.println("Client Conctat");
					DataInputStream msg = new DataInputStream(
							client.getInputStream());
					String mensaje = msg.readUTF();
					System.out.println("Server" + mensaje);
					String Nombre[] = mensaje.split(" ");
					Client client1 = new Client(Nombre[1], client);
					Ex15Thread.client = client1;
					lista.add(client1);
					if (lista.size() == 2) {
						DataOutputStream msg3 = new DataOutputStream(lista.get(0).getOP());
						msg3.writeUTF("VERSUS " + lista.get(1).Nom);
						msg3.writeUTF("YOUR BET");
					}
					new Ex15Thread(client).start();
				}

				client.close();

			} catch (IOException e) {
				System.err.println("Exception: " + e.getMessage());
			}
		}

	}

