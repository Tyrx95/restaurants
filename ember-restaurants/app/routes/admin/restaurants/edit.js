import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route';

export default AdminRoute.extend({

  restaurantService: Ember.inject.service(),
  adminService: Ember.inject.service(),
  locationService: Ember.inject.service(),
  tableService: Ember.inject.service(),
  menuService: Ember.inject.service(),

  model: function(params){
    console.log('in edit route',params);
    return Ember.RSVP.hash({
      restaurant: this.get('restaurantService').restaurantDetails(params.id),
      restaurantCategories: this.get('adminService').getRestaurantCategories(params.id),
      tables: this.get('tableService').getAllRestaurantTables(params.id),
      menu: this.get('menuService').getRestaurantMenuForAll(params.id)
    });
  },

  setupController: function(controller, model){
    controller.set('restaurant', model.restaurant);
    var categories = Ember.A();
    model.restaurantCategories.forEach(function(element){
      console.log('array element is',element);
    });
    controller.set('restaurant.categories',model.restaurantCategories);
    controller.set('tables',model.tables);
    var items = Ember.A();
    model.menu.breakfast.forEach(function(element){
      items.pushObject(element);
    });
    console.log('breakfast',model.menu.breakfast);
    model.menu.lunch.forEach(function(element){
      items.pushObject(element);
    });
    console.log('lunch',model.menu.lunch);

    model.menu.dinner.forEach(function(element){
      items.pushObject(element);
    });
    console.log('dinner',model.menu.dinner);


    controller.set('menuItems',items);
    controller.set('addQueue', []);
    controller.set('deleteQueue', []);
    controller.set('addTableQueue', []);
    controller.set('deleteTableQueue', []);

  }




});
