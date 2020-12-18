package it.rest.LibreriaRest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import it.rest.LibreriaRest.dto.ErroreDto;
import it.rest.LibreriaRest.dto.LibreriaDto;
import it.rest.LibreriaRest.exception.ExceptionPostiEsauriti;
import it.rest.LibreriaRest.exception.NonNumeroException;
import it.rest.LibreriaRest.service.LibreriaService;

@Component
@Path("/libreria")
public class LibreriaController {
	
	@Autowired
	private LibreriaService libreriaService;
	
	@POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(LibreriaDto libreriaDto) {
		try {
			if(libreriaDto.getLibri().size() > Integer.parseInt(libreriaDto.getScaffale())) {
				throw new ExceptionPostiEsauriti();
			}
			libreriaService.insert(libreriaDto);
			return Response.status(HttpStatus.OK.value()).entity(new ErroreDto("Inserimento effettuato")).build();
		} catch (ExceptionPostiEsauriti e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("Non puoi inserire più libri di quanti ne contiene la libreria")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("Inserimento andato in errore")).build();
		}
    }
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		try {
			List<LibreriaDto> librerie = libreriaService.listAll();
			return Response.status(HttpStatus.OK.value()).entity(librerie).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("Lista non caricata")).build();
		}
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") String id) {
		try {
			if(!StringUtils.isNumeric(id)) {
				throw new NonNumeroException();
			}
			LibreriaDto libreria = libreriaService.findById(Integer.parseInt(id));
			return Response.status(HttpStatus.OK.value()).entity(libreria).build();
		} catch (NonNumeroException e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("L'id inserito non è un numero")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("Elemento non caricato")).build();
		}
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response update(LibreriaDto libreriaDto) {
		try {
			if(libreriaDto.getLibri().size() > Integer.parseInt(libreriaDto.getScaffale())) {
				throw new ExceptionPostiEsauriti();
			}
			libreriaService.update(libreriaDto);
			return Response.status(HttpStatus.OK.value()).entity(new ErroreDto("Aggiornamento effettuato")).build();
		} catch (ExceptionPostiEsauriti e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("Non puoi inserire più libri di quanti ne contiene la libreria")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("Aggiornamento andato in errore")).build();
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		try {
			if(!StringUtils.isNumeric(id)) {
				throw new NonNumeroException();
			}
			libreriaService.delete(Integer.parseInt(id));
			return Response.status(HttpStatus.OK.value()).entity(new ErroreDto("Eliminazione effettuata")).build();
		} catch (NonNumeroException e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("L'id inserito non è un numero")).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).entity(new ErroreDto("Eliminazione andata in errore")).build();
		}
	}
}
