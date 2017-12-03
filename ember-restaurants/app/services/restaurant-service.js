import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  restaurantsForToday: function() {
    return this.get('ajaxService').request('/allRestaurantsSortReservationsToday');
  },
  getAllCategories: function() {
    return this.get('ajaxService').request('/getAllCategories');
  },
  getRestaurantsByFilter: function(filter) {
    return this.get('ajaxService').request('/getRestaurantsByFilter', {
      method: 'POST',
      data: JSON.stringify({
        searchText: filter.searchText,
        pageNumber: filter.pageNumber,
        itemsPerPage: filter.itemsPerPage,
        categories: filter.categories,
        priceRange: filter.priceRange,
        rating: filter.rating
      })
    });
  },

  restaurantDetails: function(id) {
    return this.get('ajaxService').request('/getRestaurantDetails', {
      method: 'POST',
      data: JSON.stringify({
        Id: id
      })
    });
  },

  addRestaurant: function(restaurant) {
   var _categories = [];
   restaurant.categories.forEach(function(element){
     _categories.addObject(element.id);
   })
   var _data = JSON.stringify({
      restaurantName: restaurant.restaurantName,
      description: restaurant.description,
      priceRange: restaurant.priceRange,
      location: restaurant.location.id,
      imageFileName: restaurant.imageFileName,
      coverFileName: restaurant.coverFileName,
      categories: _categories
    });
    console.log('Restaurant ADD BOdy ',_data);
    return this.get('ajaxService').request('/admin/addRestaurant', {
      method: 'POST',
      data: _data
    });
  },

  editRestaurant: function(restaurant) {
   var _categories = [];
   restaurant.categories.forEach(function(element){
     _categories.addObject(element.id);
   })
   var _data = JSON.stringify({
      id: restaurant.id,
      restaurantName: restaurant.restaurantName,
      description: restaurant.description,
      priceRange: restaurant.priceRange,
      location: restaurant.location.id,
      imageFileName: restaurant.imageFileName,
      coverFileName: restaurant.coverFileName,
      categories: _categories
    });
    console.log('Restaurant edit Body ',_data);
    return this.get('ajaxService').request('/admin/editRestaurant', {
      method: 'POST',
      data: _data
    });
  },

  deleteRestaurant: function(id) {
    return this.get('ajaxService').request('/admin/deleteRestaurant', {
      method: 'POST',
      data: JSON.stringify({
        id: id
      })
    });
  },

  insertComment: function(_idRestaurant, _idUser, _mark, _comment){
    return this.get('ajaxService').request('insertComment',{
      method: 'POST',
      data: JSON.stringify({
        mark: _mark,
        comment: _comment,
        idRestaurant: _idRestaurant,
        idUser: _idUser
      })
    });
  }
});
