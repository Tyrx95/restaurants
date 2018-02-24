import Ember from 'ember';
import Filter from 'ember-restaurants/models/filter';

export default Ember.Controller.extend({
  locationService: Ember.inject.service(),

  dynamicCurrent: 1,
  filter: Filter.create({
    pageNumber: 1,
    itemsPerPage: 14,
    searchText:""
  }),

  actions: {

    pageChanged(current, previous) {
      this.set('filter.pageNumber',current);
      this.get('locationService').getLocationsByFilter(this.get('filter')).then(function(data) {
        this.set('model.searchResponse', data);
      }.bind(this));

      this.set('dynamicCurrent', current);
    },
  addLocation: function() {
    this.transitionToRoute("/admin/locations/add");
  },

  deleteLocation: function(location) {
    this.get('locationService').deleteLocation(location.id).then(function() {
      this.get('model.searchResponse.locations').removeObject(location);
    }.bind(this), function(data) {
              alert(data.error);
    }.bind(this));
  },

  editLocation: function(id){
    this.transitionToRoute("/admin/locations/edit/"+id);
  },
  search: function(){
    this.set('filter.searchText', this.get('filter.searchText').trim());
    this.get('locationService').getLocationsByFilter(this.get('filter')).then(function(data){
       this.set('model.searchResponse.locations', data.locations);
       this.set('model.searchResponse.numberOfPages',data.numberOfPages);
       this.set('dynamicCurrent',1);
    }.bind(this));
  }

}



});
