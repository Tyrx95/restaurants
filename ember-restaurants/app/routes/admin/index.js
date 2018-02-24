import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route';

export default AdminRoute.extend({
  adminService: Ember.inject.service(),
  model: function() {
		return Ember.RSVP.hash({
			counters: this.get('adminService').getAdminCounters()
		});
  }
});
