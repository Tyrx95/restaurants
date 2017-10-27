import Ember from 'ember';

export default Ember.Route.extend({
  ajaxService: Ember.inject.service(),
  model() {
    return this.get('ajaxService').request('/allRestaurantsSortReservationsToday');
  }
});
