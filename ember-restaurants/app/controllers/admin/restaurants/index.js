import Ember from 'ember';
import Filter from 'ember-restaurants/models/filter';

export default Ember.Controller.extend({

  restaurantService: Ember.inject.service(),

  dynamicCurrent: 1,
  filter: Filter.create({
    pageNumber: 1,
    itemsPerPage: 14,
    searchText:""
  }),

  actions: {
    pageChanged(current, previous) {
      this.set('filter.pageNumber',current);
      this.get('restaurantService').getRestaurantsByFilter(this.get('filter')).then(function(data) {
        this.set('model.searchResponse', data);
      }.bind(this));

      this.set('dynamicCurrent', current);
    },
  addRestaurant: function() {
    this.transitionToRoute("/admin/restaurants/add");
  },

  deleteRestaurant: function(restaurant) {
    this.get('restaurantService').deleteRestaurant(restaurant.id).then(function() {
      this.get('model.searchResponse.restaurants').removeObject(restaurant);
    }.bind(this), function(data) {
              alert(data.error);
    }.bind(this));
  },

  editRestaurant: function(id){
    this.transitionToRoute("/admin/restaurants/edit/"+id);
  },

  search: function(){
    this.set('filter.searchText', this.get('filter.searchText').trim());
    this.get('restaurantService').getRestaurantsByFilter(this.get('filter')).then(function(data){
       this.set('model.searchResponse.restaurants', data.restaurants);
       this.set('model.searchResponse.numberOfRestaurantPages',data.numberOfRestaurantPages);
       this.set('dynamicCurrent',1);
    }.bind(this));
  }

}
});
