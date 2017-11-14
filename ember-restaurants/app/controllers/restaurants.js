import Ember from 'ember';
import Filter from 'ember-restaurants/models/filter';

export default Ember.Controller.extend({

  dynamicCurrent: 1,
  restaurantService: Ember.inject.service(),
  filter: Filter.create({
    pageNumber: 1,
    itemsPerPage: 3,
    searchText:"",
    priceRange:0,
    rating:0
  }),
  isOpen: false,
  actions: {
    pageChanged(current, previous) {
      this.set('filter.pageNumber',current);
      this.get('restaurantService').getRestaurantsByFilter(this.get('filter')).then(function(data) {
        this.set('model.searchResponse', data);
      }.bind(this));

      this.set('dynamicCurrent', current);
    },

    toggleOpen() {
      this.toggleProperty('isOpen');
    },

    clearPriceRange: function() {
      this.set('filter.priceRange', 0);
    },

    priceRangeSelected: function(params) {
      this.set('filter.priceRange', params.rating);
    },

    clearRating: function() {
      this.set('filter.rating', 0);
    },

    ratingSelected: function(params) {
      this.set('filter.rating', params.rating);
    },

    categorySelected: function(index){
      var category = this.get('model.categories').objectAt(index);
      var currentSelected = Ember.getWithDefault(category, 'selected', false);
      Ember.set(category,'selected',!currentSelected);
      console.log(category);
    },

    search: function(){
      var selectedCategories = this.get('model.categories').filterBy('selected', true).mapBy("name");
      this.set('filter.searchText', this.get('filter.searchText').trim());
			this.set('filter.categories', selectedCategories);
      this.get('restaurantService').getRestaurantsByFilter(this.get('filter')).then(function(data){
         this.set('model.searchResponse.restaurants', data.restaurants);
         this.set('model.searchResponse.numberOfRestaurantPages',data.numberOfRestaurantPages);
  			 this.set('isOpen', false);
         this.set('dynamicCurrent',1);
      }.bind(this));
    }



  }

});
