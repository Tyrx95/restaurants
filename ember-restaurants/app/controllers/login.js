import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    login() {
      let credentials = this.getProperties('email', 'password');
      let self = this;
      this.get('session').authenticate('authenticator:spring-boot', credentials).then(() => {
        console.log('successful login');
        self.transitionToRoute('index');
      }, (err) => {
        alert('Error obtaining token: ' + err.responseText);
      });
    }
  }
});
