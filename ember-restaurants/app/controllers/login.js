import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    login() {
      let credentials = this.getProperties('email', 'password');
      this.get('session').authenticate('authenticator:spring-boot', credentials).then(() => {
        this.get('session').isAuthenticated=true;
        this.transitionToRoute('restaurants');
      }, (err) => {
        alert('Error obtaining token: ' + err.responseText);
      });
    }
  }
});
