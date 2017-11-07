import Ember from 'ember';

export default Ember.Route.extend({

  restaurantService: Ember.inject.service(),
  model(params) {
    return this.get('restaurantService').restaurantDetails(params.restaurant_id);
  },
  setupController: function(controller, model) {
    controller.set('model', model);
    var active = Array(model.mark).fill(0);
    var notActive = Array(5-model.mark).fill(0);
    controller.set('active',active);
    controller.set('notActive',notActive);
  },
  actions: {
  afterRate: function() {
    this.refresh();
  }
}
});
