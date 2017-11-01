import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  getUser: function(){
    return this.get('ajaxService').request('/currentUser');
  }
});
