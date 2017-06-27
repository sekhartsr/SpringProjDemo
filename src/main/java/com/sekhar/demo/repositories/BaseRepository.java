package com.sekhar.demo.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import com.sekhar.demo.entities.Base;

/**
 * Override existing JPA Spring Data methods
 * 
 * @author Sekhar
 *
 * @param <T>
 */
@NoRepositoryBean
public interface BaseRepository<T extends Base>
		extends DataTablesRepository<T> {
	
}
