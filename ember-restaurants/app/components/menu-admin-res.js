import Ember from 'ember';
import MenuItem from 'ember-restaurants/models/menu-item'

export default Ember.Component.extend({

  menuTypeOptions: ["Breakfast", "Lunch", "Dinner"],

  isEdit: function(){
    var currentRoute = this.get('templateHandler');
    return (currentRoute === 'edit');
  },


  actions: {
    addMenuItem: function() {
      var _id="";
      if(this.get('controller.restaurant.id') !== "undefined"){
        _id=this.get('controller.restaurant.id');
        console.log("id is",_id);
      }

      var menuItem = MenuItem.create({
        name: "",
        description: "",
        price: "",
        type: "",
        idRestaurant: _id
      });

      if (typeof this.get('controller.addQueue') !== "undefined") {
        this.get('controller.addQueue').addObject(menuItem);
      } else {
        this.get('controller.menuItems').addObject(menuItem);
      }

    },
    deleteMenuItem: function(item) {
      if (typeof this.get('controller.deleteQueue') !== "undefined") {
        this.get('controller.deleteQueue').addObject(item);

      }
      this.get('controller.menuItems').removeObject(item);

    },
    deleteFromAddQueue: function(item){
      this.get('controller.addQueue').removeObject(item);
    }
  }
});
