package com.tira.restaurants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tira.restaurants.domain.Table;
import com.tira.restaurants.dto.TableDTO;
import com.tira.restaurants.repository.TableRepository;

@Service
public class TableServiceImpl implements TableService {
	
	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	RestaurantService restaurantService;

	@Override
	public List<Table> getAllRestaurantTables(Long restaurantId) {
		return tableRepository.findAllTablesByRestaurantId(restaurantId);
	}

	@Override
	public void addTables(List<TableDTO> addTablesDTO) {
		for(TableDTO tableDTO : addTablesDTO) {
			Table table = new Table(restaurantService.getOne(tableDTO.getIdRestaurant()), tableDTO.getSittingPlaces());
			tableRepository.save(table);
		}
	}

	@Override
	public void editTables(List<TableDTO> editTablesDTO) {
		for(TableDTO tableDTO : editTablesDTO) {
			Table table = tableRepository.findOne(tableDTO.getId());
			table.setSittingPlaces(tableDTO.getSittingPlaces());
			tableRepository.save(table);
		}
		
	}

	@Override
	public void deleteTables(List<TableDTO> deleteTablesDTO) {
		for(TableDTO tableDTO : deleteTablesDTO) {
			tableRepository.delete(tableDTO.getId());
		}
	}
	
	

}
