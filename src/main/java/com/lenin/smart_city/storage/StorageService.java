package com.lenin.smart_city.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
	
	enum TYPE {
		PROFILE, PLACES
	}

    void init();

    void store(MultipartFile file, TYPE type);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename, TYPE type);

    void deleteAll();

}
