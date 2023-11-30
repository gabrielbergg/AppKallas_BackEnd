package com.example.Kallas.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class ModellsMapper {
 
	private static ModelMapper mapper = new ModelMapper();
	
	public static <O, D> D parseObj(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	

	public static <O, D> List<D> parseListObj(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<>();
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}
}
