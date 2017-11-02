import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  restaurantsForToday: function() {
    return this.get('ajaxService').request('/allRestaurantsSortReservationsToday');
  },

  restaurantDetails: function(id) {
    return this.get('ajaxService').request('/getRestaurantDetails', {
      method: 'POST',
      data: JSON.stringify({
        Id: id
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
