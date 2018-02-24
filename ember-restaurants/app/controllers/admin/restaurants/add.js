import Ember from 'ember';
import Table from 'ember-restaurants/models/table';
import MenuItem from 'ember-restaurants/models/menu-item';

export default Ember.Controller.extend({

  restaurantService: Ember.inject.service(),
  tableService: Ember.inject.service(),
  menuService: Ember.inject.service(),

  actions: {
    addRestaurant: function() {
      this.get('restaurantService').addRestaurant(this.get('restaurant')).then(function(data) {
        console.log('Restaurant addedd succesfully');
        alert('Restaurant added successfully, adding tables and menus')
        this.send('addTables', data.id);
        this.send('addMenuItems', data.id);
        this.transitionToRoute('admin.restaurants.index')
      }.bind(this), function(error) {}.bind(this));
    },

    addTables: function(id) {
      var tables = [];
      if (this.get('tables').length > 0) {
        this.get('tables').forEach(function(element) {
          for (var i = 0; i < element.amount; i++) {
            tables.addObject(Table.create({
              idRestaurant: id,
              sittingPlaces: element.personChoice

            }));
          }
        });
        this.get('tableService').adminTableItems(tables, [], []).then(function(data) {
          alert('Tables addedd succesfully')
          console.log('success');

        }, function(error) {
          console.log(error);
          alert('Saving Tables failed')
        });
      }
    },

    addMenuItems: function(id) {
      var newMenuItems = [];
      if (this.get('menuItems').length > 0) {
        this.get('menuItems').forEach(function(element) {
          newMenuItems.addObject(MenuItem.create({
            name: element.name,
            description: element.description,
            price: element.price,
            type: element.type,
            idRestaurant: id
          }));
        });
        this.get('menuService').adminMenuItems(newMenuItems, [], []).then(function(data) {
          console.log('Menu saved successfully');
          alert('Menu successfully saved');
        }, function(error) {
          console.log(error);
          alert('error saving menu')
        });
      }
    }


  }
});
