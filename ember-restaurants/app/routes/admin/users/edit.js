import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route'

export default AdminRoute.extend({

  userService: Ember.inject.service(),

  model: function(params){
    return Ember.RSVP.hash({
      user: this.get('userService').getUserDetails(params.id)
    });
  }

});
