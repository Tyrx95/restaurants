import Ember from 'ember';

  export default Ember.Route.extend({
  userService: Ember.inject.service(),
  model() {
    return this.get('userService').getUser();
  }
});
