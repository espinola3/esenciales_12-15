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

public class Ex13Server {
			
		public static void main(String[] args) throws IOException {
		ServerSocket tcpsocket;
		boolean lis = true;
			try {
				tcpsocket = new ServerSocket(550);
				System.out.println("Esperant conexió");
			
				
				while(lis){
		
					new Ex13Thread(tcpsocket.accept()).start();
					
				}
				
		

			}catch(IOException e){
				System.err.println("Exception: " + e.getMessage());
			}
		}
	}

