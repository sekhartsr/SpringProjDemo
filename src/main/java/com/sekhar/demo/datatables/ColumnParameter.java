package com.rbtsb.datatables;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * @author Sekhar
 *
 */
@Data
public class ColumnParameter {
	/**
	 * Column's data source
	 * 
	 * @see http://datatables.net/reference/option/columns.data
	 */
	@NotBlank
	private String data;

	/**
	 * Column's name
	 * 
	 * @see http://datatables.net/reference/option/columns.name
	 */
	private String name;

	/**
	 * Flag to indicate if this column is searchable (true) or not (false).
	 * 
	 * @see http://datatables.net/reference/option/columns.searchable
	 */
	@NotNull
	private Boolean searchable;

	/**
	 * Flag to indicate if this column is orderable (true) or not (false).
	 * 
	 * @see http://datatables.net/reference/option/columns.orderable
	 */
	@NotNull
	private Boolean orderable;

	/**
	 * Search value to apply to this specific column.
	 */
	@NotNull
	private SearchParameter search;

	public ColumnParameter() {
	}

	public ColumnParameter(String data, String name, Boolean searchable,
			Boolean orderable, SearchParameter search) {
		super();
		this.data = data;
		this.name = name;
		this.searchable = searchable;
		this.orderable = orderable;
		this.search = search;
	}
}
