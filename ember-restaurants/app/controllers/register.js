import Ember from 'ember';

export default Ember.Controller.extend({
  userService: Ember.inject.service(),
  error: "",
  hasError: false,
  actions: {
    register: function(){
      let registerInfo = this.getProperties('email', 'firstName','lastName',
                  'phone','country','city','password');
      this.get('userService').register(registerInfo).then(function(result) {
        alert('Registration successful, redirecting to login');
        this.transitionToRoute('login');
      }.bind(this), function(data) {
          alert(data.payload);
      }.bind(this));
    }
  }
});
