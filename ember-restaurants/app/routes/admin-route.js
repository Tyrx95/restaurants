import Ember from 'ember';

export default Ember.Route.extend({
  currentUser: Ember.inject.service(),

  beforeModel: function(transition) {
		if(!this.get('currentUser.roles').includes('ADMIN')) {
			return this.transitionTo("index");
		}
	}

});
