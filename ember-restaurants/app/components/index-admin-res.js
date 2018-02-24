import Ember from 'ember';

export default Ember.Component.extend({

  actions: {
    onPriceRangeClick: function(params){
      this.set('controller.restaurant.priceRange',params.rating);
    },
    removeItem: function(item){
      console.log('item is ', item);
      this.get('controller.restaurant.categories').forEach(function(element){
        if(item.id === element.id) this.get('controller.restaurant.categories').removeObject(element);
      }.bind(this));



    }
  }
});
