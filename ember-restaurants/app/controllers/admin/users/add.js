import Ember from 'ember';

export default Ember.Controller.extend({

  userService: Ember.inject.service(),

  actions: {
    addUser: function(){
        this.get('userService').addUser(this.get('user')).then(function(data) {
          this.transitionToRoute('admin.users.index')
        }.bind(this), function(error) {
          alert(error);
        }.bind(this));

    }

  }



});
