package com.gabrielferreira.projeto.api.exceptions;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gabrielferreira.projeto.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.exceptions.Erro;
import com.gabrielferreira.projeto.exceptions.Erro.Campo;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	private Erro erro = new Erro();
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus httpStatus,WebRequest request){
		Erro erro = new Erro();
		
		List<Campo> campos = new ArrayList<Erro.Campo>();
		
		for(ObjectError error:ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			campos.add(new Erro.Campo(nome,msg));
		}
		
		erro.setStatus(httpStatus.value());
		erro.setTitulo("Um ou mais campos estão inválidos,Faça o preenchimento correto e tenta novamente");
		erro.setData(OffsetDateTime.now());
		erro.setCampos(campos);
		
		return super.handleExceptionInternal(ex, erro, headers, httpStatus, request);
		
	}
	
	@ExceptionHandler(EntidadeNotFoundException.class)
	public ResponseEntity<Erro> recursoNotFound(EntidadeNotFoundException e,
			HttpServletRequest req){
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setTitulo(e.getMessage());
		erro.setData((OffsetDateTime.now()));
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}

}
