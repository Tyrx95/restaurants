import Ember from 'ember';
import Filter from 'ember-restaurants/models/filter';

export default Ember.Route.extend({
  restaurantService: Ember.inject.service(),
  locationService: Ember.inject.service(),
  model() {
    return Ember.RSVP.hash({
      searchResponse: this.get('restaurantService').getRestaurantsByFilter({
        pageNumber: 1,
        itemsPerPage: 3
      }),
      locations: this.get('locationService').allLocations(),
      categories: this.get('restaurantService').getAllCategories()
    });
  },
  actions: {
    willTransition: function() {
      var filter = Filter.create({
        pageNumber: 1,
        itemsPerPage: 3,
        searchText: "",
        priceRange: 0,
        rating: 0
      });
      this.controller.set('filter', filter);
      console.log(this.controller.get('filter'));
    }
  }
});
