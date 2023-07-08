package br.com.compassuol.pb.challenge.products.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
		super(message);
	}

}