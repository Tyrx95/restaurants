import Ember from 'ember';
import Table from 'ember-restaurants/models/table'

export default Ember.Component.extend({

  personChoices: ["1 people", "2 people", "3 people", "4 people", "5 people", "6 people", "7 people", "8 people"],

  actions: {
    addTable: function() {
      var tableChoice = Ember.Object.create({
        amount:"",
        personChoice:""
      });
    this.get('controller.tables').addObject(tableChoice);
    },
    removeTable: function(table) {
      this.get('controller.tables').removeObject(table);
    }
  }
});
