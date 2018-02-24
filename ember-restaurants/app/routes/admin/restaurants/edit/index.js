import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route'

export default AdminRoute.extend({

  restaurantService:Ember.inject.service(),
  locationService:Ember.inject.service(),

  model: function(){
    return Ember.RSVP.hash({
      locations: this.get('locationService').allLocations(),
      categories: this.get('restaurantService').getAllCategories()
    });
  }
});
