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

public class Ex15Thread extends Thread {

	private static Socket sock = null;
	private static String Nom;
	public static Client cliente = new Client(Nom, sock);
	public int lloc;
	public int at;

	
	public Ex15Thread(Socket socket) {
		super("Ex15Thread");
		this.sock = socket;
	}

	public void run() {
		synchronized (this) {
			int bet;
			DataOutputStream msg2;
			try {

				msg2 = new DataOutputStream(cliente.getOP());

				if (Ex15Thread.llista.size() == 1) {
					msg2.writeUTF("WAIT");
					System.out.println(msg2.toString());
					lloc = 0;
					at = 1;

				} else {
					msg2.writeUTF("VERSUS "
							+ Ex15Thread.llista.get(0).getNombre());
					lloc = 1;
					at = 0;
					msg2.writeUTF("WAIT BET");
				}
			} catch (IOException e) {

			}
			try {
				System.out.println("While del client: "
						+ Ex15Thread.llista.get(lloc).getNombre());
				msg2 = new DataOutputStream(Ex15Thread.llista.get(lloc).getOP());
				int i;
				DataInputStream dip = new DataInputStream(cliente.getIN());
				String entrada;
				entrada = dip.readUTF();
				System.out.println("Thread " + entrada);
				String tr[] = entrada.split(" ");
				System.out.println(entrada);
				if (tr[0].equals("MY")) {
					bet = Integer.valueOf(tr[3]);
					Ex15Thread.total = Ex15Thread.total
							+ Integer.valueOf(tr[2]);
					Ex15Thread.apostes++;

					for (i = 0; i < Ex15Thread.llista.size(); i++) {
						DataOutputStream DOP = new DataOutputStream(
								Ex15Thread.llista.get(i).getOP());
						DOP.writeUTF("BET OF "
								+ Ex15Thread.llista.get(lloc).getNombre() + " "
								+ bet);
					}
					if (Ex15Thread.apostes == 1) {
						msg2.writeUTF("WAIT BET");
						System.out.println("Thread aposta "
								+ Ex15Thread.apostes);
						DataOutputStream msg3 = new DataOutputStream(
								Ex15Thread.llista.get(at).getOP());
						msg3.writeUTF("YOUR BET");
					}
					try {
						boolean bol = true;
						while (bol) {
							if (Ex15Thread.apostes == 2) {
								msg2 = new DataOutputStream(Ex15Thread.llista
										.get(lloc).getOP());
								System.out.println(Ex15Thread.llista.get(lloc)
										.getNombre() + " " + bet);
								if (Ex15Thread.total == bet) {
									Ex15Thread.Guanyador = Ex15Thread.llista.get(
											lloc).getNombre();
									msg2.writeUTF("Winner "
											+ Ex15Thread.Guanyador);
									Thread.sleep(1000);
									bol = false;
								}

								else {
									Thread.sleep(1000);
									msg2.writeUTF("Winner "
											+ Ex15Thread.Guanyador);
									bol = false;
								}
							}
						}
					} catch (IOException e) {

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			} catch (IOException e) {

			}
		}
	}
}
