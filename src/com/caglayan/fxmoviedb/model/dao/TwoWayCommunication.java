package com.caglayan.fxmoviedb.model.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.caglayan.fxmoviedb.model.dto.CommunicationDto;


public class TwoWayCommunication {
	private String ip;
	private int port;

	public TwoWayCommunication() {
		this.ip = "localhost";
		this.port = 5555;
	}

	public TwoWayCommunication(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public CommunicationDto startClientTwoWayCommunication(CommunicationDto requestObject) {
		CommunicationDto answerObject;
		try (Socket socket = new Socket(this.ip, this.port);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());) {

			objectOutputStream.writeObject(requestObject); // obje gidiyo
			objectOutputStream.flush();

			if ((answerObject = (CommunicationDto) objectInputStream.readObject()) != null) {
				return answerObject;
			}

		} catch (SocketException e) {
			//printConnectionClosedThanks();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
