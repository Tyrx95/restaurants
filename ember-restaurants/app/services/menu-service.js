import Ember from 'ember';

export default Ember.Service.extend({
  ajaxService: Ember.inject.service(),

  adminMenuItems: function(_addQueue, _editQueue, _deleteQueue) {
    var _data = JSON.stringify({
      addQueue: _addQueue,
      editQueue: _editQueue,
      deleteQueue: _deleteQueue
    });
    console.log('Admin Menu items', _data);
    return this.get('ajaxService').request('/admin/adminMenuItems', {
      method: 'POST',
      data: _data
    });
  },

  getRestaurantMenuForAll: function(id){
  return  Ember.RSVP.hash({
          breakfast: this.getRestaurantMenuByType(id,'Breakfast'),
          lunch: this.getRestaurantMenuByType(id,'Lunch'),
          dinner: this.getRestaurantMenuByType(id,'Dinner')
    });


  },

  getRestaurantMenuByType: function(id, _type){
    return this.get('ajaxService').request('/getRestaurantMenu',{
      method: 'POST',
      data: JSON.stringify({
        idRestaurant: id,
        type: _type
      })
    });
  }


});
