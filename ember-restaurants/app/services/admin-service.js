import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  getAdminCounters: function(){
    return this.get('ajaxService').request('admin/getAdministrationCounters')
  },

  getRestaurantCategories: function(_id) {
    return this.get('ajaxService').request('/admin/getRestaurantCategories', {
      method: 'POST',
      data: JSON.stringify({
        id: _id
      })

    });
  }
});
