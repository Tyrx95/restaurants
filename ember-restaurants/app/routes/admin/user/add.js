import Ember from 'ember';
import User from 'ember-restaurants/models/user'

export default Ember.Route.extend({

  setupController: function(controller, model){
    controller.set('user', User.create({
      email:"",
      firstName:"",
      lastName:"",
      phone:"",
      country:"",
      city:"",
      password: ""
    }));
  }
});
