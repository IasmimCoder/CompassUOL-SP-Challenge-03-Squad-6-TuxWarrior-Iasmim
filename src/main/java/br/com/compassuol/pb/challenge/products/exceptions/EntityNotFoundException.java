package br.com.compassuol.pb.challenge.products.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
		super(message);
	}

}