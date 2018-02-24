import Ember from 'ember';
import Table from 'ember-restaurants/models/table';

export default Ember.Controller.extend({

  restaurantService: Ember.inject.service(),
  menuService: Ember.inject.service(),
  tableService: Ember.inject.service(),

  actions: {
    editRestaurant: function() {
      this.get('restaurantService').editRestaurant(this.get('restaurant')).then(function(data) {
        console.log('Restaurant edited succesfully');
        alert('Restaurant edited successfully, editing tables and menus')
        this.send('editTables');
        this.send('editMenuItems');
        this.transitionToRoute('admin.restaurants.index')
      }.bind(this), function(error) {}.bind(this));
    },

    editTables: function() {
      var addTables = [];
      if (this.get('addTableQueue').length > 0) {
        this.get('addTableQueue').forEach(function(element) {
          for (var i = 0; i < element.amount; i++) {
            addTables.addObject(Table.create({
              idRestaurant: element.idRestaurant,
              sittingPlaces: element.personChoice

            }));
          }
        });
      }
        this.get('tableService').adminTableItems(addTables,[],this.get('deleteTableQueue')).then(function(data) {
          alert('Tables edited succesfully')
          console.log('success');

        }, function(error) {
          console.log(error);
          alert('Editing Tables failed')
        });

    },

    editMenuItems: function(id) {
        this.get('menuService').adminMenuItems(this.get('addQueue'), this.get('menuItems'), this.get('deleteQueue')).then(function(data) {
          console.log('Menu saved successfully');
          alert('Menu successfully edited');
        }, function(error) {
          console.log(error);
          alert('error editing menu')
        });
      }
  }

});
