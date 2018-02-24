package com.tira.restaurants.dto;

import java.util.List;

public class FilterDTO {

	private Integer itemsPerPage;
	private Integer pageNumber;
	private String searchText;
	private Integer priceRange;
	private List<String> categories;
	private Integer rating;
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public Integer getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(Integer priceRange) {
		this.priceRange = priceRange;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "FilterDTO [itemsPerPage=" + itemsPerPage + ", pageNumber=" + pageNumber + ", searchText=" + searchText
				+ ", priceRange=" + priceRange + ", categories=" + categories + ", rating=" + rating + "]";
	}
	
	
	
}
