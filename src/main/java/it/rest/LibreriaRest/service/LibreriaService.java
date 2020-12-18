package it.rest.LibreriaRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rest.LibreriaRest.dto.LibreriaDto;
import it.rest.LibreriaRest.mapper.LibreriaMapper;
import it.rest.LibreriaRest.repository.LibreriaRepository;

@Service
public class LibreriaService {
	
	@Autowired
	private LibreriaRepository libreriaRepository;
	
	@Autowired
	private LibreriaMapper libreriaMapper;
	
	public void insert(LibreriaDto libreriaDto) throws Exception{
		libreriaRepository.save(libreriaMapper.convertDtoToEntity(libreriaDto));
	}
	
	public List<LibreriaDto> listAll() throws Exception{
		return libreriaMapper.convertEntityToDto(libreriaRepository.findAll());
	}
	
	public LibreriaDto findById(Integer id) {
		return libreriaMapper.convertEntityToDto(libreriaRepository.findById(id).get());
	}
	
	public void update(LibreriaDto libreriaDto) throws Exception{
		libreriaRepository.save(libreriaMapper.convertDtoToEntity(libreriaDto));
	}
	
	public void delete(Integer id) throws Exception{
		libreriaRepository.deleteById(id);
	}
}
