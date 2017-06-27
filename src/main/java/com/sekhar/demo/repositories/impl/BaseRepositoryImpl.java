package com.sekhar.demo.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sekhar.demo.datatables.ColumnParameter;
import com.sekhar.demo.datatables.DataTablesInput;
import com.sekhar.demo.datatables.DataTablesOutput;
import com.sekhar.demo.datatables.DataTablesSpecification;
import com.sekhar.demo.datatables.OrderParameter;
import com.sekhar.demo.entities.Base;
import com.sekhar.demo.repositories.DataTablesRepository;

/**
 * @author Sekhar
 *
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T extends Base> extends SimpleJpaRepository<T, String>
		implements DataTablesRepository<T> {

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#findAll()
	 */
	@Override
	public List<T> findAll() {
		return super.findAll((root, query, cb) -> {
			return cb.and(cb.isFalse(root.get("deleted")));
		});
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		return super.findAll((root, query, cb) -> {
			return cb.and(cb.isFalse(root.get("deleted")));
		} , pageable);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#findAll(org.springframework.data.domain.Sort)
	 */
	@Override
	public List<T> findAll(Sort sort) {
		return super.findAll((root, query, cb) -> {
			return cb.and(cb.isFalse(root.get("deleted")));
		} , sort);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#findAll(org.springframework.data.jpa.domain.Specification)
	 */
	@Override
	public List<T> findAll(Specification<T> spec) {
		return super.findAll(Specifications.where(spec).and((root, query, cb) -> {
			return cb.and(cb.isFalse(root.get("deleted")));
		}));
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return super.findAll(Specifications.where(spec).and((root, query, cb) -> {
			return cb.and(cb.isFalse(root.get("deleted")));
		}), pageable);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Sort)
	 */
	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return super.findAll(Specifications.where(spec).and((root, query, cb) -> {
			return cb.and(cb.isFalse(root.get("deleted")));
		}), sort);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#count()
	 */
	@Override
	public long count() {
		return count((root, query, cb) -> cb.and(cb.isFalse(root.get("deleted"))));
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#count(org.springframework.data.jpa.domain.Specification)
	 */
	@Override
	public long count(Specification<T> spec) {
		return super.count(Specifications.where(spec).and((root, query, cb) -> {
			return cb.and(cb.isFalse(root.get("deleted")));
		}));
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#delete(java.io.Serializable)
	 */
	@Transactional
	@Override
	public void delete(String id) {
		T t = getOne(id);
		
		if (t.getIsRestricted())
			return;
		
		t.setDeleted(true);
		saveAndFlush(t);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#delete(java.lang.Object)
	 */
	@Transactional
	@Override
	public void delete(T t) {
		if (t.getIsRestricted())
			return;
		
		t.setDeleted(true);
		saveAndFlush(t);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#delete(java.lang.Iterable)
	 */
	@Transactional
	@Override
	public void delete(java.lang.Iterable<? extends T> entities) {
		for (T t : entities) {
			if (t.getIsRestricted())
				return;
			
			t.setDeleted(true);
			saveAndFlush(t);
		}
	}

	/* (non-Javadoc)
	 * @see com.rbtsb.repositories.DataTablesRepository#dataTablesFindAll(com.rbtsb.datatables.DataTablesInput)
	 */
	@Override
	public DataTablesOutput<T> dataTablesFindAll(DataTablesInput input) {
		return dataTablesFindAll(input, null);
	}

	/* (non-Javadoc)
	 * @see com.rbtsb.repositories.DataTablesRepository#dataTablesFindAll(com.rbtsb.datatables.DataTablesInput, org.springframework.data.jpa.domain.Specification)
	 */
	@Override
	public DataTablesOutput<T> dataTablesFindAll(DataTablesInput input, Specification<T> additionalSpecification) {
		DataTablesOutput<T> output = new DataTablesOutput<T>();
		output.setDraw(input.getDraw());

		try {
			output.setRecordsTotal(count(additionalSpecification));

			Page<T> data = findAll(
					Specifications.where(new DataTablesSpecification<T>(input)).and(additionalSpecification),
					getPageable(input));

			output.setData(data.getContent());
			output.setRecordsFiltered(data.getTotalElements());

		} catch (Exception e) {
			
			System.out.println("Exception"  + e);
			
			
			output.setError(e.toString());
			output.setRecordsFiltered(0L);
		}

		return output;
	}

	/**
	 * Creates a 'LIMIT .. OFFSET .. ORDER BY ..' clause for the given
	 * {@link DataTablesInput}.
	 * 
	 * @param input
	 *            the {@link DataTablesInput} mapped from the Ajax request
	 * @return a {@link Pageable}, must not be {@literal null}.
	 */
	private Pageable getPageable(DataTablesInput input) {
		List<Order> orders = new ArrayList<Order>();
		for (OrderParameter order : input.getOrder()) {
			ColumnParameter column = input.getColumns().get(order.getColumn());
			if (column.getOrderable()) {
				String sortColumn = column.getData();
				Direction sortDirection = Direction.fromString(order.getDir());
				orders.add(new Order(sortDirection, sortColumn));
			}
		}
		Sort sort = orders.isEmpty() ? null : new Sort(orders);

		if (input.getLength() == -1) {
			input.setStart(0);
			input.setLength(Integer.MAX_VALUE);
		}
		return new PageRequest(input.getStart() / input.getLength(), input.getLength(), sort);
	}
}
