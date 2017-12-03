import Ember from 'ember';

export default Ember.Service.extend({
  session: Ember.inject.service(),
  roles: Ember.computed.oneWay('session.data.authenticated.roles')
});
