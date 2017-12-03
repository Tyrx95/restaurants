import Ember from 'ember';

export default Ember.Route.extend({
    setupController: function(controller, model){
      this._super(controller, model);

      var restaurantController= this.controllerFor('restaurant');

      controller.set('restaurant', restaurantController.get('restaurant'));
      controller.set('reservationTime', restaurantController.get('reservationTime'));
      controller.set('reservation', restaurantController.get('reservation'));
    }
});
