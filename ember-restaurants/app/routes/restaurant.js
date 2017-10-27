import Ember from 'ember';

export default Ember.Route.extend({

  restaurantService: Ember.inject.service(),
  model(params) {
    return this.get('restaurantService').restaurantDetails(params.restaurant_id);
  }
});
