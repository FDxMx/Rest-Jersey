package it.rest.LibreriaRest.mapper;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import it.rest.LibreriaRest.dto.LibreriaDto;
import it.rest.LibreriaRest.model.Libreria;

@Component
public class LibreriaMapper extends AbstractMapper<Libreria, LibreriaDto>{

	@Override
	public LibreriaDto convertEntityToDto(Libreria entity) {
		if (entity == null) {
			return null;
		}
		LibreriaDto libreriaDto = new LibreriaDto();
		libreriaDto.setId(String.valueOf(entity.getId()));
		libreriaDto.setGenere(entity.getGenere());
		libreriaDto.setScaffale(String.valueOf(entity.getScaffale()));
		libreriaDto.setLibri(Stream.of(entity.getTitolo().split(";")).collect(Collectors.toList()));
		return libreriaDto;
	}

	@Override
	public Libreria convertDtoToEntity(LibreriaDto dto) {
		if (dto == null) {
			return null;
		}
		Libreria libreria = new Libreria();
		if(dto.getId() != null) {
			libreria.setId(Integer.parseInt(dto.getId()));
		}
		libreria.setGenere(dto.getGenere());
		libreria.setScaffale(Integer.parseInt(dto.getScaffale()));
		String titoli = "";
		for (String titolo : dto.getLibri()) {
			titoli += titolo + "; ";
		}
		libreria.setTitolo(titoli);
		return libreria;
	}

}
