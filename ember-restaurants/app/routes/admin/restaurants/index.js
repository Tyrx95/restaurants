import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route';
import Filter from 'ember-restaurants/models/filter';

export default AdminRoute.extend({
    restaurantService: Ember.inject.service(),
    locationService: Ember.inject.service(),

    model: function(){
      return Ember.RSVP.hash({
        searchResponse: this.get('restaurantService').getRestaurantsByFilter({
          pageNumber: 1,
          itemsPerPage: 14
        }),
        locations: this.get('locationService').allLocations()
      });
    },

    actions: {

      willTransition: function() {
        var filter = Filter.create({
          pageNumber: 1,
          itemsPerPage: 14,
          searchText: ""
        });
        this.controller.set('filter', filter);
      }
    }
});
