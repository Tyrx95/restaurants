import Ember from 'ember';
import AdminRoute from 'ember-restaurants/routes/admin-route';
import Restaurant from 'ember-restaurants/models/restaurant';

export default AdminRoute.extend({

  setupController: function(controller, model){
    controller.set('restaurant', Restaurant.create({
      restaurantName:"",
      priceRange:0,
      location:0,
      description:"",
      imageFileName:"",
      coverFileName:"",
      categories: []
    }));
    controller.set('menuItems',[]);
    controller.set('tables',[]);
  }
});
