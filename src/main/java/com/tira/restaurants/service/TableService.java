package com.tira.restaurants.service;

import java.util.List;

import com.tira.restaurants.domain.Table;
import com.tira.restaurants.dto.TableDTO;

public interface TableService {

	List<Table> getAllRestaurantTables(Long restaurantId);
	void addTables(List<TableDTO> addTablesDTO);
	void editTables(List<TableDTO> editTablesDTO);
	void deleteTables(List<TableDTO> deleteTablesDTO);

}
