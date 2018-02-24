import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route'

export default AdminRoute.extend({

  locationService: Ember.inject.service(),

  model: function(params){
    return Ember.RSVP.hash({
      location: this.get('locationService').getLocationDetails(params.id)
    });
  }

});
