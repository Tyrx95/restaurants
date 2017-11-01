import Ember from 'ember';

export default Ember.Route.extend({

      restaurantService: Ember.inject.service(),
      locationService: Ember.inject.service(),

      model: function() {
        return Ember.RSVP.hash({
          restaurants: this.get('restaurantService').restaurantsForToday(),
          locations: this.get('locationService').allLocations()
        });
      },
      setupController: function(controller, models) {
        controller.set('restaurants', models.restaurants);
        controller.set('locations', models.locations);
      }
      
      });
