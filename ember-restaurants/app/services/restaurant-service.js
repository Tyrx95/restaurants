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
});
