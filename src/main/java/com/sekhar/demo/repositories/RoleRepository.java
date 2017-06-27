package com.sekhar.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sekhar.demo.entities.Role;

/**
 * Role JPA Repository
 * 
 * @author Sekhar
 *
 */
public interface RoleRepository extends BaseRepository<Role> {
	
	/**
	 * Find Role by Name
	 * 
	 * @param name
	 * @return
	 */
	Role findByName(String name);
	
	/**
	 * Find Role By Id and Fetch All Lazy
	 * 
	 * @param id
	 * @return
	 */
	@Query("select r from Role r left join fetch r.modules m "
			+ "left join fetch r.permissions p "
			+ "left join fetch r.actions a "
			+ "where (m.id is null or (m.id is not null and m.deleted = false)) "
			+ "and (p.id is null or (p.id is not null and p.deleted = false)) "
			+ "and (a.id is null or (a.id is not null and a.deleted = false)) "
			+ "and r.id = :id and r.deleted = false")
	Role findOneAndFetchAll(@Param("id") String id);
}
