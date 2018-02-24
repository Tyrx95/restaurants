import Ember from 'ember';

export default Ember.Controller.extend({
  edit: Ember.inject.controller('admin.restaurants.edit'),
  personChoices: ["1 people", "2 people", "3 people", "4 people", "5 people", "6 people", "7 people", "8 people"],

  actions: {
    deleteTable: function(table){
      this.get('edit.deleteTableQueue').addObject(table);
      this.get('edit.tables').removeObject(table);

    },
    addTable: function(){
      var tableChoice = Ember.Object.create({
        amount:"",
        personChoice:"",
        idRestaurant: this.get('edit.restaurant.id')
      });
    this.get('edit.addTableQueue').addObject(tableChoice);
    },
    removeTable: function(table){
        this.get('edit.addTableQueue').removeObject(table);
    }
  }
});
