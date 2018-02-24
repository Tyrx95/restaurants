import Ember from 'ember';

export default Ember.Route.extend({

  setupController: function(controller, models) {
  		this._super(controller, models);

  		controller.setProperties({
  			nameValue:""
  		});
  	}


});
