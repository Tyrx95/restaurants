import Ember from 'ember';
import Filter from 'ember-restaurants/models/filter';

export default Ember.Route.extend({
  restaurantService: Ember.inject.service(),
  locationService: Ember.inject.service(),
  model() {
    return Ember.RSVP.hash({
      restaurants: this.get('restaurantService').getRestaurantsByFilter(Filter.create(
        {pageNumber: 1, itemsPerPage: 9})),
      locations: this.get('locationService').allLocations()
    });
  }
});
