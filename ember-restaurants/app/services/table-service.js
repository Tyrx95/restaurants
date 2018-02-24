import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  adminTableItems: function(_addQueue, _editQueue, _deleteQueue) {
    var _data = JSON.stringify({
      addQueue: _addQueue,
      editQueue: _editQueue,
      deleteQueue: _deleteQueue
    });
    console.log('Admin Table ITEMS', _data);
    return this.get('ajaxService').request('/admin/adminTableItems', {
      method: 'POST',
      data: _data
    });
  },

  getAllRestaurantTables: function(id){
    return this.get('ajaxService').request('/admin/getAllRestaurantTables', {
      method: 'POST',
      data: JSON.stringify({
        idRestaurant: id
        })
    });
  }

});
