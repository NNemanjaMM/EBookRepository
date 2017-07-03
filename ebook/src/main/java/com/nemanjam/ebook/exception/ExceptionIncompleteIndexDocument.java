package com.nemanjam.ebook.exception;

public class ExceptionIncompleteIndexDocument extends Exception {
	
	private static final long serialVersionUID = 4302844775708028458L;

	public ExceptionIncompleteIndexDocument(){
		super("Document is incomplete. Default metadata is not present");
	}
	
	public ExceptionIncompleteIndexDocument(String message){
		super(message);
	}

}