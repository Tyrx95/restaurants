import Ember from 'ember';

export default Ember.Controller.extend({
  userService: Ember.inject.service(),
  actions: {
    editUser: function(){
        this.get('userService').editUser(this.get('model.user')).then(function(data) {
          this.transitionToRoute('admin.users.index')
        }.bind(this), function(error) {
          alert(error);
        }.bind(this));

    }
  }
});
