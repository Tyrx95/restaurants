import Ember from 'ember';

export default Ember.Route.extend({

  restaurantService: Ember.inject.service(),
  menuService: Ember.inject.service(),
  model(params) {
    return Ember.RSVP.hash({
      restaurant: this.get('restaurantService').restaurantDetails(params.restaurant_id),
      breakfast: this.get('menuService').getRestaurantMenuByType(params.restaurant_id,'Breakfast'),
      lunch: this.get('menuService').getRestaurantMenuByType(params.restaurant_id,'Lunch'),
      dinner: this.get('menuService').getRestaurantMenuByType(params.restaurant_id,'Dinner'),
    });
  },
  setupController: function(controller, model) {
    controller.clear();
    controller.set('restaurant', model.restaurant);
    var active = Array(model.restaurant.mark).fill(0);
    var notActive = Array(5-model.restaurant.mark).fill(0);
    controller.set('breakfast',model.breakfast);
    controller.set('lunch',model.lunch);
    controller.set('dinner',model.dinner);
    controller.set('active',active);
    controller.set('notActive',notActive);
  },
  actions: {
  afterRate: function() {
    this.refresh();
  }
}
});
